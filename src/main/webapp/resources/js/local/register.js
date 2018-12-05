$(function () {
    //注册账号url
    var url = '/local/registeraccount';
    $('#submit').click(function () {
        var sname = $('#sname').val();
        var username = $('#username').val();
        var password = $('#password').val();
        var confirmPassword = $('#confirmPassword').val();
        if (!username && !password && !confirmPassword) {
            $.toast('请输入账户信息!');
            return;
        }
        if (!password && !confirmPassword) {
            $.toast('请输入密码!');
            return;
        }
        if (password != confirmPassword) {
            $.toast('两次输入的密码不一致!');
            return;
        }
        var localauth = {};
        var personInfo = {};
        personInfo.sname = sname;
        localauth.personInfo = personInfo;
        localauth.userName = username;
        localauth.password = password;


        var formData = new FormData();

        formData.append("localAuthStr", JSON.stringify(localauth));


        var profileImg = $('#profile-img')[0].files[0];
        if (profileImg == null) {
            $.toast('请上传图片!');
            return false;
        }

        formData.append("profileImg", profileImg);


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
                    $.toast("注册成功!");
                    window.location.href = '/frontend/index';
                } else {
                    $.toast("注册失败~" + data.errMsg);
                    console.info(data.errMsg);
                    $('#captcha_img').click();
                }
            }
        });
    });
    $('#back').click(function () {
        window.location.href = '/frontend/index';
    })
});