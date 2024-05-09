function sendData(id,version,name){
    $('#id').val(id);
    $('#version').val(version);
    $('#name').text(name);
}

function saveDataInLocalStorage(action,page,id,productName,productCode) {
    localStorage.setItem("page",page+1);
    localStorage.setItem("productName",productName);
    localStorage.setItem("productCode",productCode);
    if(action === 'create' ){
        console.log("Thêm mới");
        window.location.href = '/product/create'
    }
    if(action ==='edit') {
        console.log("Chỉnh sửa");
        window.location.href = '/product/edit/' + encodeURIComponent(id);
    }
}

window.onload=function (){
    localStorage.clear();
}

$(document).ready(function () {
    $('#clear').on('click',function () {
        $('.input').val(null);
    });

});