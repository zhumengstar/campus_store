$(function () {
    var shopId = getQueryString('shopId');
    var shopInfoUrl = '/shopadmin/getshopmanagementinfo?shopId=' + shopId;

    $.getJSON(shopInfoUrl,
        function (data) {
            if (data.redirect) {
                window.location.href = data.url;
            } else {
                if (data.shopId != undefined && data.shopId != null) {
                    shopId = data.shopId;
                }
                // $('#shopId').attr('href', '/shopadmin/productcategorymanagement?shopId=' + shopId);
                $('#shopInfo').attr('href', '/shopadmin/shopoperation?shopId=' + shopId);
            }
        });
});