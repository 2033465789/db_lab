// 网页加载完成后

//上传网站的formData对象
var formData = new FormData();
$(function() {
	// 事件监听
	$("[role='item']").mouseenter(function() {
		$(this).addClass("mouse-enter");
	}).mouseleave(function() {
		$(this).removeClass("mouse-enter");
	});
	// 监听是否选择图片
	$("#web-img").change(function() {
		if ($(this).val().length > 0) {
			formData.append("fileName", this.files[0]);
			$(this).removeClass("warning");
		} else {
			$(this).addClass("warning");
			formData.append("fileName", null);
		}
	});

	$("a[data-operate]").click(function() {
		var operate = $(this).data("operate");
		$("#" + operate).removeClass("hide");
	});
});

// 加载要显示的常用网址网址消息
function loadWebsiteInfo(aim_url, img_url, web_name, web_desc) {
	// 内容生成
	var item = $("<div class='col-md-3 col-sm-12 website-container img-thumbnail'></div>");
	var imginfo = $("<div col-md-12 col-sm-12'><img src='" + img_url
			+ "' class='img-thumbnail image-rounded img-responsive' /></div>");
	var name = $("<div class='col-md-12 col-sm-12'><span class='text-center'><h2>"
			+ web_name + "</h2></span></div>");
	var desc = $("<div class='webDesc col-md-12 col-sm-12'><span class='margin-top-bottom-10px'>"
			+ web_desc + "</span></div>");
	var goBtn = $("</div class='col-md-12 col-sm-12  text-center'><button type='button' class='margin-top-bottom-10px btn btn-info to-bottom'>立即前往</button></div>");
	goBtn.click(function() {
		window.open(aim_url, "_blank");
	});
	// 元素拼接
	item.append(imginfo);
	item.append(name);
	item.append(desc);
	item.append(goBtn);

	// 事件监听
	item.mouseenter(function() {
		$(this).addClass("mouse-enter");
	});

	item.mouseleave(function() {
		$(this).removeClass("mouse-enter");
	});

	// 加载上页面
	$("#website-info").append(item);
}

// 所有操作的取消
function cancel(op) {
	$("#" + op).addClass("hide");
}

function addWebsite() {
	var name = $("#web-name").val().trim();
	var desc = $("#web-desc").val().trim();
	var url = $("#web-url").val().trim();
	var img = $("#web-img").val().trim();

	if (name == "" || desc == "" || url == ""
			|| formData.get('fileName') == null) {
		$.growl.notice({
			title : "",
			message : "请将信息填写完整"
		});
		return;
	} else {
		formData.append("webName", name);
		formData.append("webDesc", desc);
		formData.append("webURL", url);
	}
	$.ajax({
		url : "AddWebsite",
		type : "post",
		data : formData,
		contentType : false,
		processData : false,
		mimeType : "multipart/form-data",
		success : function(data) {
			$.growl.notice({
				title : "",
				message : data
			});
			$("#add").addClass("hide");
		}
	});
}

function deleteWebsite() {
	var name = $("#delete-website-name").val().trim();
	var url = "DeleteWebsite?webName=" + name;
	$.get(url, function(resp, status) {
		if (status == "success" && resp == "success") {
			$.growl.notice({
				title : "",
				message : "删除成功"
			});
			$("#delete").addClass("hide");
			location.reload();
		} else {
			$.growl.notice({
				title : "",
				message : resp
			});
		}
	});
}