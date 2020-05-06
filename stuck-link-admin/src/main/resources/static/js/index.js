$(function () {
    $("#content").height($(".content-wrapper").outerHeight() - $(".content-header").outerHeight() - 30 - 60);
});

function clickMenu(dom) {
    $("#content").empty();
    $("#sidebar li").removeClass("active");
    $(dom).parents("li").addClass("active");
    var wrapper = $(".content-wrapper").outerHeight();
    var header = $(".content-header").outerHeight();
    var url = $(dom).data("url");

    var iframe = $('<iframe scrolling="yes" frameborder="0" style="width:100%;height: 100%;overflow:visible;background:#fff;" src="' + url + '"></iframe>');

    var title = $(dom).text();
    $("#content-header h1").html(title);
    $("#content-header .breadcrumb").empty().append('<li><i class="fa fa-dashboard"></i> 监控展示</li>').append('<li class="active">' + title + '</li>');
    $("#content").append(iframe);
}