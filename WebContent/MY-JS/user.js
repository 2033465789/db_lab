$(function()
{
    $("[data-user]").mouseenter(function()
    {
        $(this).addClass("shadow");
    });
    
    $("[data-user]").mouseleave(function()
    {
        $(this).removeClass("shadow");
    });
});