let page = localStorage.getItem("page");
let productName = localStorage.getItem("productName");
let productCode = localStorage.getItem("productCode");


$(document).ready(function () {
    $('#btn-back').on('click',function () {
        window.location.href = '/product/list?page='+(page)+'&productName='+productName+'&productCode=' + productCode;
    });
    $('#btn-submit').on('click',function () {
        $('#page').val(page);
        $('#productName').val(productName);
        $('#productCode').val(productCode);
    });
})