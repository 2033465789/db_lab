$(function() {
	$('#userId').blur(function() {
		value = $(this).val();
		if (value == "")
		{
			$(this).css("border-color", "red");
			return;
		}
	});
	$('#pwd').blur(function() {
		value = $(this).val();
		if (value == "")
		{
			$(this).css("border-color", "red");
			return;
		}

	});
	$('#rpwd').blur(function() {
		value = $(this).val();
		if (value == "")
		{
			$(this).css("border-color", "red");
			return;
		}
	});
});

function getXMLHttpRequest()
{
	if (window.XMLHttpRequest)
		return new XMLHttpRequest();
	else
		return new ActiveXObject("Microsoft.XMLHTTP");
}

function register()
{
	var userId = document.getElementById("userId").value;
	var pwd = document.getElementById("pwd").value;
	var rpwd = document.getElementById("rpwd").value;
	if (pwd != rpwd)
	{
		$.growl.warning({
			title : "",
			message : "两次密码不一致"
		});
		return;
	}
	if (pwd.length < 6)
	{
		$.growl.warning({
			title : "",
			message : "密码至少6位"
		});
		return;
	}
	var http = getXMLHttpRequest();
	http.onreadystatechange = function() {
		if (http.readyState == 4 && http.status == 200)
		{
			var resp = http.responseText;
			if (resp == "success")
			{
				location.href = "main.jsp";
			} else
			{

				$.growl.warning({
					title : "",
					message : resp
				});
			}
		}
	}
	http.open("POST", "SignUp");
	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	http.send("userId=" + userId + "&" + "pwd=" + pwd);
}