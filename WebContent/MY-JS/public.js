function MSG(title, message) {
    this.title = title;
    this.message = message;
}

function showNoticeMSG(message) {
    var msg = new MSG("", message);
    $.growl.notice(msg);
}

function showWarningMSG(message) {
    var msg = new MSG("", message);
    $.growl.warning(msg);
}

function showErrorMSG(message) {
    var msg = new MSG("", message);
    $.growl.error(msg);
}

function logOut() {
    $.ajax({
        url: "LogOut",
        type: "post",
        success: function (resp, status) {
            dealResponse(resp, status, "注销失败");
        }
    });
}

function show_login() {
    // 创建悬浮组件
    var login = $("<div id='log-in' style='full-width'></div>");
    var bg = $("<div class='login-overplay col-sm-12'></div>");
    var content = $("<div class='login-content col-md-offset-3 col-md-6 col-sm-12'></div>");
    // 加载登录页面
    content.load("commons/login.html");
    // 组件拼接
    login.append(bg);
    login.append(content);
    // 送页面显示
    $(document.body).append(login);
    bg.click(function () {
        $("#log-in").fadeOut("slow", function () {
            $(this).remove()
        });
    });
}

function cardBalance(balance) {
    if (balance == 'null') {
        window.open("login.html", "_self");
    } else {
        $.growl.notice({
            title: "你的一卡通余额为",
            message: balance
        });
    }
}

function LogIn() {
    var pwd = $("#pwd").val();
    var userId = $("#userId").val();
    if (userId == null || userId.length == 0) {
        $("#userId").addClass("warning");
        return;
    }
    if (pwd == null || pwd.length == 0) {
        $("#pwd").addClass("warning");
        return;
    }
    $.ajax({
        url: "LogIn",
        type: "post",
        data: {
            "userId": userId,
            "pwd": pwd
        },
        success: function (resp) {
            if (resp == "success") {
                $("#log-in").remove();
                location.reload();
            } else if (resp != "") {
                showWarningMSG(resp);
            } else {
                showWarningMSG("登录失败");
            }
        }
    });
}

function signup() {
    location.href = "signup.html";
}

function dealResponse(resp, status, otherInfo) {
    if (resp == "success" && status == "success") {
        location.reload();
    } else if (resp != "") {
        showNoticeMSG(resp);
    } else {
        showNoticeMSG(otherInfo);
    }

}