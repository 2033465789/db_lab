function deleteShared(id)
{
	var url = "Operate";
	var data = new Object();
	data.id = id;
	data.method = "delShared";
	$.post(url, data, function(resp, status) {
		dealResponse(resp, status, "删除失败");
	});
}