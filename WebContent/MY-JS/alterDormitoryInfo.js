function alter()
{
	var correct = true;
	$(":text").each(function()
	{
		if ($(this).val() == "")
		{
			$(this).addClass("warning");
			correct = false;
			return;
		}
	});
	if (!correct)
	{
		return;
	}

	$.ajax({
		url : "AlterDormitoryInfo",
		type : "post",
		data : {
			dormAddr : $("#dormAddr").val(),
			dormWhich : $("#dormWhich").val(),
			dormTag : $("#dormTag").val()
		},
		success : function(resp, status)
		{
			if (resp == "success" && status == "success")
			{
				$.growl.notice({
					title : " ",
					message : "修改成功"
				});
			} else
			{
				$.growl.notice({
					title : " ",
					message : resp
				});
			}
		},
		error : function()
		{

		}
	});
}
function chooseAddr(e)
{
	$("#dormAddr").html(e.innerHTML+"<b class=\"caret\"></b>").val(e.innerHTML);
}
function chooseWhich(e)
{
	$("#dormWhich").html(e.innerHTML+"<b class=\"caret\"></b>").val(e.innerHTML);
}
$(function()
{
	$(":text").keyup(function()
	{
		if ($(this).val() != "")
		{
			$(this).removeClass("warning");
		}
	});
});