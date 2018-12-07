$(document).ready(function() { // 上传数据

	var formData = new FormData();

	$("#good-image").change(function() {
		var reader = new FileReader();
		reader.readAsDataURL(this.files[0]);
		if ($(this).val().length > 0) {
			formData.append("imageName", this.files[0]);
			$(this).removeClass("warning");
		} else {
			$(this).addClass("warning");
			formData.append("imageName", null);
		}

		$("#image-path").val(this.files[0].name);

		reader.onload = function(e) {
			$("#image-show").addClass("show-image");
			$("#image-show").attr("src", this.result);
		}
	});

	// 动态绑定事件

	// 提交按钮点击事件
	$("#submit").click(function() {
		var OK = true;
		// 检查数据格式
		$("[placeholder]").each(function() {
			if (this.value.length == 0) {
				$(this).addClass("warning");
				// 添加监听
				$(this).keyup(function() {
					if ($(this).val().length > 0) {
						$(this).removeClass("warning");
					} else {
						$(this).addClass("warning");
					}
				});
				OK = false;
				return;
			}
		});

		// 检查是否选择文件
		if (formData.get('imageName') == null) {
			$.growl.warning({
				title : "",
				message : "请选择要上传的文件"
			});
			return;
		}
		if (!OK) {
			$.growl.notice({
				title : "",
				message : "请填写所有信息"
			});
			return;
		}

		formData.append("numberInfo", $("#number-info").val().trim());
		formData.append("losterName", $("#loster-name").val().trim());
		formData.append("goodDesc", $("#good-desc").val().trim());
		formData.append("foundDddr", $("#found-addr").val().trim());
		formData.append("finderName", $("#finder-name").val().trim());
		formData.append("finderPhone", $("#finder-phone").val().trim());
		formData.append("finderQQorWX", $("#finder-QQorWX").val().trim());
		$.ajax({
			url : "Finder",
			type : "post",
			data : formData,
			contentType : false,
			processData : false,
			mimeType : "multipart/form-data",
			success : function(data) {
				if (data == "offline") {
					show_login();
					return;
				}
				showNoticeMSG("上传成功");
				window.setTimeout(function() {
					location.reload();
				}, 500);

			}
		});
	});

	// 联系方式选择按钮事件
	$("#choice-qq").click(function() {
		$("#connect-way").html("Q Q 账号<b class='caret'></b>");
	});

	$("#choice-wx").click(function() {
		$("#connect-way").html("微信账号<b class='caret'></b>");
	});
});