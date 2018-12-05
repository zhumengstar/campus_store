$(function () {
    $('#log-out').click(function () {
        //清除session
        $.ajax({
            url: "/local/logout",
            type: 'POST',
            async: false,
            cache: false,
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    //清除成功后退出到登录界面
                    window.location.href = '/frontend/index';
                    return false;
                }
            },
            error: function (data, error) {
                alert(error);
            }
        });
    });
});
