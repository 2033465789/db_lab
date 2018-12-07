$(function () {
    //绑定点击事件
    $("#close-dbconn").click(function () {
        $.ajax({
            url: "CloseConnection",
            type: "post",
            success: function (resp) {
                var op;
                if (resp == "success") {
                    op = {
                        title: "",
                        message: "操作成功"
                    };
                } else {
                    op = {
                        title: "",
                        message: resp
                    };
                }
                $.growl.notice(op);
            }
        });
    });

    $("#open-dbconn").click(function () {
        $.post("OpenConnection", function (resp, status) {
            if (status == "success") {
                var op;
                if (resp == "success") {
                    op = {
                        title: "",
                        message: "操作成功"
                    };
                } else {
                    op = {
                        title: "",
                        message: resp
                    };
                }
                $.growl.notice(op);
            } else {
                $.growl.notice({
                    title: "",
                    message: "服务器响应失败"
                });
            }
        });
    });

    $("#flush-cache").click(function () {
        $.ajax({
            url: "FlushCache",
            type: "post",
            success: function (resp) {
                if (resp == "success") {
                    var op = {
                        title: "",
                        message: "操作成功"
                    };
                } else {
                    var op = {
                        title: "",
                        message: resp
                    };
                }
                $.growl.notice(op);
            }
        });
    });
});