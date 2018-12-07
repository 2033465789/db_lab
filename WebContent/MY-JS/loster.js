$(document).ready(function() {
	$("#search").click(function() {
		var searchInfo = $("#search-info").val();
		if (searchInfo.length == 0)
		{
			$.growl.warning({
				title : "",
				message : "请输入搜索信息"
			});
			return;
		}

		//从服务器加载数据
		$("#show-lost-goods").load("Loster", {
			searchInfo : searchInfo
		}, function(responseTxt, statusTxt, xhr) {
			if (statusTxt == "success")
			{
				$("#lost-good-table").addClass("table table-hover text-center");
				$("img").addClass("img-thumbnail image-rounded img-responsive show-image");
				//为图片设置鼠标监听
				$("img").mouseenter(function() {
					$(this).removeClass("show-image");
					$(this).addClass("show-bigger-image");
				});
				$("img").mouseleave(function() {
					$(this).removeClass("show-bigger-image");
					$(this).addClass("show-image");
				});

			/*$("tr").click(function()
			{
				alert("详情");
			});
			*/
			}
		});
	});
});