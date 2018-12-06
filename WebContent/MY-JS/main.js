$(function()
{
    
    $(".module-container").addClass("img-thumbnail img-responsive");
    
    $(".module-container").mouseenter(function()
    {
        $(this).addClass("module-mouse-enter");
    });
    
    $(".module-container").mouseleave(function()
    {
        $(this).removeClass("module-mouse-enter");
    });
    
    $("#recently-lost-goods").load("RecentlyGoods", function(responseTxt, statusTxt, xhr)
    {
        if (statusTxt == "success")
        {
            $("#recently-good-table").addClass("table table-hover text-center");
            $("img[data-recently]").addClass("img-thumbnail image-rounded img-responsive show-image");
            // 为图片设置鼠标监听
            $("img[data-recently]").mouseenter(function()
            {
                $(this).removeClass("show-image");
                $(this).addClass("show-bigger-image");
            });
            $("img[data-recently]").mouseleave(function()
            {
                $(this).removeClass("show-bigger-image");
                $(this).addClass("show-image");
            });
        } else
        {
            $.growl.notice({
                title : "",
                message : responseTxt
            });
        }
    });
});