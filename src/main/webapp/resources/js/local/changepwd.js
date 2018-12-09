$(function () {
    //修改平台密码的controller url
    var url = '/local/changelocalpwd';
    $('#submit').click(function () {
        var username = $('#username').val();
        var password = $('#password').val();
        var newPassword = $('#newPassword').val();
        var confirmPassword = $('#confirmPassword').val();
        if(password==newPassword){
            $.toast('原密码与新密码相同!');
            return;
        }
        if (newPassword != confirmPassword) {
            $.toast('两次输入的新密码不一致!');
            return;
        }

        var formData = new FormData();
        formData.append("username", username);
        formData.append("password", password);
        formData.append("newPassword", newPassword);

        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual) {
            $.toast('请输入验证码!');
            return;
        }
        formData.append("verifyCodeActual", verifyCodeActual);

        $.ajax({
            url: url,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.success) {
                    $.toast("提交成功!");
                    window.location.href = '/frontend/index';
                } else {
                    $.toast("提交失败!" + data.errMsg);
                    console.info(data.errMsg);
                    $('#captcha_img').click();
                }
            }
        });
    });

    $('#back').click(function () {
        window.location.href = '/shopadmin/shoplist';
    })
});