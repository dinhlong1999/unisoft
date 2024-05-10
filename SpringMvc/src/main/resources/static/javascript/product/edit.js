let page = localStorage.getItem("page");
let productName = localStorage.getItem("productName");
let productCode = localStorage.getItem("productCode");

function compareObject(oldValueObject,newValueObject) {
    return oldValueObject.code === newValueObject.code
        && oldValueObject.name === newValueObject.name
        && oldValueObject.priceSell === newValueObject.priceSell
        && oldValueObject.priceBuy === newValueObject.priceBuy
}

$(document).ready(function (){
    $('#submit').on('click',function(event){
        $('#page').val(page);
        $('#productName').val(productName);
        $('#productCode').val(productCode);
        let codeProductOld = $('#codeProductOld').val();
        let nameProductOld = $('#nameProductOld').val();
        let priceSellOld = $('#priceSellOld').val();
        let priceBuyOld = $('#priceBuyOld').val();
        let oldObject = {
            code: codeProductOld,
            name: nameProductOld,
            priceSell:priceSellOld,
            priceBuy:priceBuyOld
        }
        console.log(oldObject);
        let codeProductNew = $('#codeProduct').val();
        let nameProductNew = $('#nameProduct').val();
        let priceSellNew = $('#priceSell').val();
        let priceBuyNew = $('#priceBuy').val();
        let newObject = {
            code: codeProductNew,
            name: nameProductNew,
            priceSell: priceSellNew,
            priceBuy: priceBuyNew
        }
        console.log(newObject);
        if (compareObject(oldObject,newObject)){
            alert("Không có dữ liệu thay đổi")
            event.preventDefault();
        }else {
            console.log("Đã vào");
            $(this).closest("form").submit();
            console.log($(this).closest("form"));
        }

    });
    $('#btn-back').on('click',function () {
        window.location.href = '/product/list?page='+(page)+'&productName='+productName+'&productCode=' + productCode;
    })
})

