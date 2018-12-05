$(function () {
//判断session
    $.ajax({
        url: "/get/getsession",
        type: 'GET',
        async: false,
        cache: false,
        success: function (data) {
            if (data.success) {
                $(".no-user").hide();
            } else {
                $(".user").hide();
            }
        }
    });
});
