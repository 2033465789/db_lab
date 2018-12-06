function deleteGood(id)
{
	var url = "Operate";
	var data = new Object();
	data.id = id;
	data.method = "delFound";
	$.post(url, data, function(resp, status) {
		dealResponse(resp, status, "删除失败");
	});
}


function findLoster(id)
{
	var url = "Operate";
	var data = new Object();
	data.id = id;
	data.method = "findLoster";
	$.post(url, data, function(resp, status) {
		dealResponse(resp, status, "操作失败");
	});
}