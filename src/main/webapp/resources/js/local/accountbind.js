$(function () {
    //绑定帐号的controller url
    var bindUrl = '/local/bindlocalauth';

    $('#submit').click(function () {
        //获取输入的帐号和密码
        var username = $('#username').val();
        var password = $('#password').val();
        var verifyCodeActual = $('#j_captcha').val();
        var needVerify = false;
        if (!verifyCodeActual) {
            $.toast('请输入验证码!');
            return;
        }
        //访问后台,绑定账号
        $.ajax({
            url: bindUrl,
            async: false,
            cache: false,
            type: 'POST',
            dataType: 'json',
            data: {
                username: username,
                password: password,
                verifyCodeActual: verifyCodeActual
            },
            success: function (data) {
                if (data.success) {
                    $.toast("绑定成功!");
                    window.location.href = '/frontend/index';

                } else {
                    $.toast("提交失败!" + data.errMsg);
                    $('#captcha_img').click();
                }
            }
        });
    });
});