$(document).ready(function () {
    //鼠标点击切换
    $('.container a').click(function () {
        $(this).addClass('active').siblings().removeClass('active')
    });

    $(".ui.dropdown").dropdown({
        on:'hover' ,//鼠标悬浮显示，默认值是click
        transition:'swing right', //设置动画效果
        allowAdditions:true //允许添加新的菜单项
    });
});