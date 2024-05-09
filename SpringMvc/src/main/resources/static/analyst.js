$(document).ready(function () {
    $(document).on("click","#customerNoOrder", function() {
        let url = $(this).data('pages');
        console.log(url);
        $.ajax({
            url : url,
            type: 'GET',
            success : function(response){
                console.log(response);
                $('#customerTable tbody').empty();
                if(response.customerList.length === 0){
                    $('#customerTable tbody').html('<tr><td colspan="4">Không có dữ liệu</td></tr>')
                    if(response.messageError !== ''){
                        Swal.fire({
                            title: response.messageError,
                            icon: 'error',
                            confirmButtonColor: 'red'
                        });
                    }
                }else {
                    $.each(response.customerList, function(index,item) {
                        let rowHTML = '<tr>'+
                            '<td>' +(index + 1  + (response.page * response.limit))+'</td>' +
                            '<td>' +item['name'] + '</td>' +
                            '<td>' +item['phone']+ '</td>' +
                            '<td>' +item['address'] + '</td>' +
                            '</tr>';
                        $('#customerTable tbody').append(rowHTML);
                    })
                }
                $('#customerPage').empty();
                if (response.customerList.length > 0){
                    let pageNavigation = '<nav aria-label="Page navigation example" >' +
                        '<ul class="pagination" style="display: flex;justify-content: center">';
                    if(response.page > 0){
                        pageNavigation += '<li class="page-item">' +
                            ' 	          <button id="customerNoOrder" class="page-link" data-pages="/api/analyst/customernoorder?page='+response.page+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'">Previous</button>' +
                            '          </li>';
                    }
                    if (response.showStartEllipsis){
                        pageNavigation += '<li class="page-item">' +
                            '				<span class="page-link">...</span >\n' +
                            '          </li>';
                    }for (let pageNumber = response.startPage; pageNumber <= response.endPage;pageNumber++){
                        if (pageNumber - 1 === response.page){
                            pageNavigation += '<li class="page-item active">' +
                                '             <span  class="page-link">'+pageNumber+' </span>' +
                                '          </li>'
                        }else {
                            pageNavigation += '<li class="page-item">' +
                                '				<button id="customerNoOrder" class="page-link"  data-pages="/api/analyst/customernoorder?page='+pageNumber+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'">'+pageNumber+'</button>' +
                                '          </li>'
                        }
                    }
                    if (response.showEndEllipsis){
                        pageNavigation += '<li class="page-item">' +
                            '             <span  class="page-link">...</span>' +
                            '          </li>'
                    }
                    if (response.page + 1 !== response.totalPage){
                        pageNavigation +='<li class="page-item">' +
                            '            <button id="customerNoOrder" class="page-link" data-pages="/api/analyst/customernoorder?page='+(response.page + 2)+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'">Next</button>' +
                            '         </li>'
                    }
                    $('#customerPage').html(pageNavigation);
                }
            },
            error : function(xhr, status, error){
                console.error(error);
            }
        });
    });

    $(document).on("click","#productBestSeller",function () {
        let url = $(this).data('pages');
        console.log(url);
        $.ajax({
            url : url,
            type: 'GET',
            success : function (response) {
                console.log(response);
                $('#productBestSellerTable tbody').empty();
                if (response.productList.length === 0){
                    $('#productBestSellerTable tbody').html('<tr><td colspan="3">Không có dữ liệu</td></tr>');
                    if(response.messageError !== ''){
                        Swal.fire({
                            title: response.messageError,
                            icon: 'error',
                            confirmButtonColor: 'red'
                        });
                    }
                }else{
                    $.each(response.productList,function (index,item) {
                        let rowHTML = '<tr>'+
                            '<td>' +(index + 1  + (response.page * response.limit))+'</td>' +
                            '<td>' +item['code'] + '</td>' +
                            '<td>' +item['name']+ '</td>' +
                            '<td>' +item['quantity'] + '</td>' +
                            '</tr>';
                        $('#productBestSellerTable tbody').append(rowHTML);
                    })
                }
                $('#productBestSellerPage').empty();
                if (response.productList.length > 0){
                    let pageNavigation = '<nav aria-label="Page navigation example" >' +
                        '<ul class="pagination" style="display: flex;justify-content: center">';
                    if(response.page > 0){
                        pageNavigation += '<li class="page-item">' +
                            ' 	          <button id="productBestSeller" class="page-link" data-pages="/api/analyst/productBestSeller?page='+response.page+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'">Previous</button>' +
                            '          </li>';
                    }
                    if (response.showStartEllipsis){
                        pageNavigation += '<li class="page-item">' +
                            '				<span class="page-link">...</span >\n' +
                            '          </li>';
                    }for (let pageNumber = response.startPage; pageNumber <= response.endPage;pageNumber++){
                        if (pageNumber - 1 === response.page){
                            pageNavigation += '<li class="page-item active">' +
                                '             <span  class="page-link">'+pageNumber+' </span>' +
                                '          </li>'
                        }else {
                            pageNavigation += '<li class="page-item">' +
                                '				<button id="productBestSeller" class="page-link"  data-pages="/api/analyst/productBestSeller?page='+pageNumber+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'" >'+pageNumber+'</button>' +
                                '          </li>'
                        }
                    }
                    if (response.showEndEllipsis){
                        pageNavigation += '<li class="page-item">' +
                            '             <span  class="page-link">...</span>' +
                            '          </li>'
                    }
                    if (response.page + 1 !== response.totalPage){
                        pageNavigation +='<li class="page-item">' +
                            '            <button id="productBestSeller" class="page-link" data-pages="/api/analyst/productBestSeller?page='+(response.page + 2)+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'">Next</button>' +
                            '         </li>'
                    }
                    $('#productBestSellerPage').html(pageNavigation);
                }
            },
            error : function(xhr, status, error){
                console.error(error);
            }
        })
    });
    $(document).on("click","#productNoSell",function () {
        console.log("Đã vào product no seller");
        let url = $(this).data('pages');
        console.log(url);
        $.ajax({
            url : url,
            type: 'GET',
            success : function (response) {
                console.log(response);
                $('#productNoSellTable tbody').empty();
                if (response.productList.length === 0){
                    $('#productNoSellTable tbody').html('<tr><td colspan="3">Không có dữ liệu</td></tr>');
                    if(response.messageError !== ''){
                        Swal.fire({
                            title: response.messageError,
                            icon: 'error',
                            confirmButtonColor: 'red'
                        });
                    }
                }else{
                    $.each(response.productList,function (index,item) {
                        let rowHTML = '<tr>'+
                            '<td>' +(index + 1  + (response.page * response.limit))+'</td>' +
                            '<td>' +item['code'] + '</td>' +
                            '<td>' +item['name']+ '</td>' +
                            '</tr>';
                        $('#productNoSellTable tbody').append(rowHTML);
                    })
                }
                $('#productNoSellPage').empty();
                if (response.productList.length > 0){
                    let pageNavigation = '<nav aria-label="Page navigation example" >' +
                        '<ul class="pagination" style="display: flex;justify-content: center">';
                    if(response.page > 0){
                        pageNavigation += '<li class="page-item">' +
                            ' 	          <button id="productNoSell" class="page-link" data-pages="/api/analyst/productNoSell?page='+response.page+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'">Previous</button>' +
                            '          </li>';
                    }
                    if (response.showStartEllipsis){
                        pageNavigation += '<li class="page-item">' +
                            '				<span class="page-link">...</span >\n' +
                            '          </li>';
                    }for (let pageNumber = response.startPage; pageNumber <= response.endPage;pageNumber++){
                        if (pageNumber - 1 === response.page){
                            pageNavigation += '<li class="page-item active">' +
                                '             <span  class="page-link">'+pageNumber+' </span>' +
                                '          </li>'
                        }else {
                            pageNavigation += '<li class="page-item">' +
                                '				<button id="productNoSell" class="page-link"  data-pages="/api/analyst/productNoSell?page='+pageNumber+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'" >'+pageNumber+'</button>' +
                                '          </li>'
                        }
                    }
                    if (response.showEndEllipsis){
                        pageNavigation += '<li class="page-item">' +
                            '             <span  class="page-link">...</span>' +
                            '          </li>'
                    }
                    if (response.page + 1 !== response.totalPage){
                        pageNavigation +='<li class="page-item">' +
                            '            <button id="productNoSell" class="page-link" data-pages="/api/analyst/productNoSell?page='+(response.page + 2)+'&orderDayBegin='+response.orderDayBegin+'&orderDayEnd='+response.orderDayEnd +'">Next</button>' +
                            '         </li>'
                    }
                    $('#productNoSellPage').html(pageNavigation);
                }
            },
            error : function(xhr, status, error){
                console.error(error);
            }
        })
    });
});