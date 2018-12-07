var uploading = false;
$(function() {
	$("#progress").hide();
	$("#uploading").hide();
	$("a[href^='sharedResource']").attr("download", "");

	var formData = new FormData();

	// 添加监听
	$("#upload-file").change(function() {
		if ($(this).val().length > 0) {
			formData.append("fileName", this.files[0]);
			$("#file-path").val(this.files[0].name);
			$(this).removeClass("warning");
		} else {
			$(this).addClass("warning");
			formData.append("fileName", null);
		}
	});

	// 获取上传文件类型
	$("[data-type]").click(function() {
		formData.append("fileType", $(this).data("type"));
		$("[data-type]").removeClass("selected");
		$(this).addClass("selected");
	});

	// 评论
	$("#make-comment").click(function() {
		var content = $("#comment-content").val();
		if (content == "") {
			showNoticeMSG("评论不能为空");
			return;
		}
		var id = $("[data-file]").data("file");
		var data = {
			commentContent : content,
			fileId : id
		};

		$.post("MakeComment", data, function(resp, status) {
			if (resp == "offline") {
				show_login();
			} else if (resp == "success") {
				location.reload();
				window.setInterval(function() {
					showNoticeMSG("评论成功");
				}, 500);
			} else // 出错，显示出错信息
			{
				showNoticeMSG("评论失败");
			}
		});
	});

	// 查看 文件详情
	$("[data-file]").click(function() {
		var fileId = $(this).data("file");
		window.open("ShowFileDetails?id=" + fileId, "_blank");
	});

	$("#submit").click(function() {

		if (uploading) {
			showNoticeMSG("正在上传文件");
			return;
		}

		// 合法型判断
		if (formData.get('fileType') == null) {
			showWarningMSG("请选择要上传的文件类型");
			return;
		}

		var fileDesc = $("#fileDesc").val();

		if (fileDesc.length < 10) {
			showWarningMSG("文字描述不得少于10个字符");
			return;
		} else {
			formData.append("fileDesc", fileDesc);
		}

		// 检查是否选择了上传文件
		if (formData.get('fileName') == null) {
			showWarningMSG("请选择要上传的文件");
			return;
		}

		var interval = -1;
		var progress = 0;
		$.ajax({
			url : "UploadResource",
			type : "post",
			data : formData,
			contentType : false,
			processData : false,
			mimeType : "multipart/form-data",
			success : function(data) {
				progress = 100;
				$("#progress-bar").width(progress + "%");
				window.setTimeout(function() {
					$("#uploading").hide();
					$("#progress").hide();
					$("#progress-bar").width(0 + "%");
					uploading = false;
					window.clearInterval(interval);
					if (data == "success") {
						showNoticeMSG("上传成功");
					}
					window.setTimeout(function() {
						location.reload();
					}, 1000)
				}, 800);
			},
			beforeSend : function(xhr) {
				$("#progress-bar").width(progress + "%");
				uploading = true;
				$("#uploading").show();
				$("#progress").show();
				$("#progress").animate({
					width : "100%"
				});
				interval = window.setInterval(function() {
					progress = progress + Math.random() * 5;
					$("#progress-bar").width(progress + "%");
				}, 500);
			}
		});
	});
});

// 监听下载按钮点击事件
function search() {
	var searchInfo = $("#search-info").val();
	if (searchInfo.length == 0) {
		showWarningMSG("请输入搜索信息");
		return;
	}
	location.href = "DownloadResource?searchInfo=" + searchInfo;
}