$(document).ready(function () {
    let data = JSON.parse(localStorage.getItem('myArray')) || [];
    render(data);
    let temp = JSON.parse(localStorage.getItem('cellErrors')) || [];
    if (temp.length !== 0) {
        handleCellErrors(temp);
    }

});
function validateOrder (quantity, productCode, customerPhone){
    let errorList = [];
    if (quantity <= 0 ){
        errorList.push("quantity");
    }
    if(productCode === ""){
        errorList.push("productCode");
    }
    if(customerPhone === ""){
        errorList.push("customerPhone");
    }
    return errorList;
   
} 

function taoIDMoi() {
    return Math.random().toString(36).substr(2, 9); // Trả về một chuỗi ngẫu nhiên
}

function compareObjectValue (oldObject, newObject){
    return oldObject.id === newObject.id && oldObject.productCode === newObject.productCode
        && oldObject.productName === newObject.productName && oldObject.quantity === newObject.quantity
        && oldObject.customerPhone === newObject.customerPhone && oldObject.customerName === newObject.customerName;
}

function checkChangeValue(id, rowData){
    let currentArray = JSON.parse(localStorage.getItem('myArray')) || [];
    let recordHidden = $('#myTable').find('tr.hidden[id="' + id + '"]');
    let oldValue = {
        id : id,
        productCode: recordHidden.find('.productCodeOldValue').text(),
        productName: recordHidden.find('.productNameOldValue').text(),
        quantity: recordHidden.find('.quantityOldValue').text().trim(),
        customerName: recordHidden.find('.customerNameOldValue').text(),
        customerPhone: recordHidden.find('.customerPhoneOldValue').text()
    };
    if (!compareObjectValue(oldValue,rowData)){
        let existingIndex = currentArray.findIndex(item => item.id === id);
        if (existingIndex !== -1) {
            currentArray[existingIndex] = rowData;
        }else {
            currentArray.push(rowData);
        }
    }else {
        let existingIndex = currentArray.findIndex(item => item.id === id);
        if (existingIndex !== -1){
            currentArray.splice(existingIndex,1)
        }

    }

    if (currentArray.length === 0){
        localStorage.removeItem('myArray');
        localStorage.removeItem('cellErrors');
    }else{
        localStorage.setItem('myArray', JSON.stringify(currentArray));
    }


}

$(document).ready(function () {

    $('#clear').on('click',function () {
        $('.input').val(null);
    });
    

    $('#reset').on('click',function () {
        localStorage.clear();
        location.reload();
    });


    $('#addRow').on('click',function () {
        let newRowID = taoIDMoi();
        $('#myTable').append('<tr id='+newRowID + ' >' +
            '                     <td></td>\n' +
            '                     <td></td>\n' +
            '                     <td class="productCode"   contenteditable="true" ></td>\n' +
            '                     <td class="productName"   contenteditable="true"></td>\n' +
            '                     <td></td>\n' +
            '                     <td class="quantity"      contenteditable="true"></td>\n' +
            '                     <td class="customerName"  contenteditable="true"></td>\n' +
            '                     <td class="customerPhone" contenteditable="true"></td>\n' +
            '                     <td ></td>\n' +
            '                     <td></td>\n' +
            '                     <td></td>\n' +    
            /*   '                     <td th:if = "${roleName == \'ROLE_ADMIN\'}"></td>\n' +
              '                     <td th:if = "${roleName == \'ROLE_ADMIN\'}"></td>' + */
            '                </tr>')

    });

    $('#add').on('click',function (){
        let url = $(this).data('pages');
        $('#myTable td').css('background-color','white')
        let temp = localStorage.getItem('myArray') || [];
        if(temp.length === 0){
            Swal.fire({
                title: 'Không có dữ liệu thay đổi',
                icon: 'info',
                confirmButtonColor: 'blue'
            });
        }else {
            let validateMap = new Map();
            let errorPages = [];
            let dataArray = JSON.parse(temp);
            dataArray.forEach(function(value) {
                let errorList = validateOrder(value.quantity, value.productCode, value.customerPhone);
                if(errorList.length !== 0){
                    validateMap.set(value.id,errorList)
                    errorPages.push(parseInt(value.page));
                }
            })
            console.log(validateMap);
            if(validateMap.size === 0){
                $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/api/orders/save",
                data: temp,
                success : function(response){
                 if (response){
                        Swal.fire({
                            title: 'Cập nhật dữ liệu thành công',
                            icon: 'success',
                            confirmButtonColor: 'blue',
                        }).then((result) => {
                            if (result.value) {
                                localStorage.clear();
                                location.reload();
                            }
                        });
                  }
                  else{
                    Swal.fire({
                            title: 'Không thể thao tác.',
                            icon: 'error',
                            confirmButtonColor: 'red'
                    }).then((result) => {
                            if (result.value) {
                                localStorage.clear();
                                location.reload();
                            }
                    })            
                  }
                }
                ,
                error:function(xhr, status, error) {
                    console.error("lỗi khi gửi dữ liệu :" + error);
                    Swal.fire({
                        title: 'Dữ liệu chưa được cập nhật. Vui lòng thử lại.',
                        icon: 'error',
                        confirmButtonColor: 'red'
                    }).then((result) => {
                        if (result.value) {
                            localStorage.clear();
                            location.reload();
                        }
                    });
                }
             })
            }
            else{
              console.log(errorPages);
              let cellError = Object.fromEntries(validateMap);
              console.log(JSON.stringify(cellError));
              localStorage.setItem('cellErrors',JSON.stringify(cellError));
                 for (let key in cellError){
                            if (cellError.hasOwnProperty(key)){
                                if (key === "isLogin"){
                                    Swal.fire({
                                        title: 'Không thể thao tác.',
                                        icon: 'error',
                                        confirmButtonColor: 'red'
                                    }).then((result) => {
                                        if (result.value) {
                                            localStorage.clear();
                                            location.reload();
                                        }
                                    });
                                    break;
                                }else{
                                    let valueList = cellError[key];
                                    valueList.forEach(function (value) {
                                        let table = $('#myTable');
                                        table.find('tr[id="' + key + '"]').find('td.'+value).css('background-color','red');
                                    })
                                     Swal.fire({
                                        title: 'Dữ liệu không hợp lệ, vui lòng kiểm tra dữ liệu ở những ô màu đỏ.',
                                        icon: 'error',
                                        confirmButtonColor: 'red'
                                    }).then((result) => {
                                        if (result.value) {
                                           let pageError = Math.min(...errorPages);
                                           let urlError = url.replace('pageError', +pageError + 1);
                                           window.location.href =urlError;
                                        }
                                    });
                                }   
                             
                            }
                        }
            }
            
            
            
            
           /*$.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/api/orders/save",
                data: temp,
                success : function(response){
                    if (JSON.stringify(response) === "{}"){
                        Swal.fire({
                            title: 'Cập nhật dữ liệu thành công',
                            icon: 'success',
                            confirmButtonColor: 'blue',
                        }).then((result) => {
                            if (result.value) {
                                localStorage.clear();
                                location.reload();
                            }
                        });
                    }else {
                        localStorage.setItem('cellErrors',JSON.stringify(response))
                        for (let key in response){
                            if (response.hasOwnProperty(key)){
                                if (key === "isLogin"){
                                    Swal.fire({
                                        title: 'Không thể thao tác.',
                                        icon: 'error',
                                        confirmButtonColor: 'red'
                                    }).then((result) => {
                                        if (result.value) {
                                            localStorage.clear();
                                            location.reload();
                                        }
                                    });
                                    break;
                                }else{
                                    let valueList = response[key];
                                    console.log(key);
                                    console.log(valueList);
                                    valueList.forEach(function (value) {
                                        let table = $('#myTable');
                                        table.find('tr[id="' + key + '"]').find('td.'+value).css('background-color','red');
                                    })
                                     Swal.fire({
                                        title: 'Dữ liệu không hợp lệ, vui lòng kiểm tra dữ liệu ở những ô màu đỏ.',
                                        icon: 'error',
                                        confirmButtonColor: 'red'
                                    });
                                }   
                             
                            }
                        }
                    
                    }
                },
                error:function(xhr, status, error) {
                    console.error("lỗi khi gửi dữ liệu :" + error);
                    Swal.fire({
                        title: 'Dữ liệu chưa được cập nhật. Vui lòng thử lại.',
                        icon: 'error',
                        confirmButtonColor: 'red'
                    }).then((result) => {
                        if (result.value) {
                            localStorage.clear();
                            location.reload();
                        }
                    });
                }
            })*/
        }

    });

    $('#myTable').on('focusout','td.productCode[contenteditable="true"]',function () {
        let productCode = $(this).text();
        let row = $(this).closest("tr");
        let productNameCell = row.find('.productName');
        let valueProductCode = $(this);
        $.ajax({
            contentType :"application/json",
            method : "GET",
            url : `http://localhost:8081/api/product/getProductName?productCode=`+encodeURIComponent(productCode),
            success : function(data) {
                if (data === "") {
                    productNameCell.text('')
                    valueProductCode.text('')
                    Swal.fire({
                        title: 'Không tìm được dữ liệu',
                        icon: 'error',
                        confirmButtonColor: 'red'
                    });
                }else if (data === "errorLogin"){
                    location.reload();
                    localStorage.clear();
                }
                else {
                    productNameCell.text(data)
                }
                if(data !== "errorLogin"){
                    let id = row.attr('id');
                    let rowData = {
                         id: id,
                        productCode: row.find('.productCode').text(),
                        productName: row.find('.productName').text(),
                        quantity: row.find('.quantity').text(),
                        customerName: row.find('.customerName').text(),
                        customerPhone: row.find('.customerPhone').text(),
                        version : row.find('.hidden').text(),
                        page : row.find('.page').text()
                    };
                    console.log(rowData);
                    checkChangeValue(id,rowData);
                }
               
            },
            error:function(xhr, textStatus, errorThrow){
                console.log("Error",errorThrow)
            }
        })
    });

    $('#myTable').on('focusout','td.productName[contenteditable="true"]',function () {
        let productName = $(this).text();
        let row = $(this).closest("tr");
        let productCodeCell = row.find("td:eq(2)");
        let valueProductName = $(this);
        $.ajax({
            contentType :"application/json",
            method : "GET",
            url : `http://localhost:8081/api/product/getProductCode?productName=`+encodeURIComponent(productName),
            success : function(data) {
                if (data === "") {
                    productCodeCell.text('')
                    valueProductName.text('')
                    Swal.fire({
                        title: 'Không tìm được dữ liệu',
                        icon: 'error',
                        confirmButtonColor: 'red'
                    });
                }
                else if (data === "errorLogin"){
                    location.reload();
                    localStorage.clear();
                }
                else {
                    productCodeCell.text(data)
                }
                if(data !== "errorLogin"){
                    let id = row.attr('id');
                    let rowData = {
                        id: id,
                        productCode: row.find('.productCode').text(),
                        productName: row.find('.productName').text(),
                        quantity: row.find('.quantity').text(),
                        customerName: row.find('.customerName').text(),
                        customerPhone: row.find('.customerPhone').text(),
                        version : row.find('.hidden').text(),
                        page : row.find('.page').text()
                    };
                    checkChangeValue(id,rowData);
                }
            },
            error:function(xhr, textStatus, errorThrow){
                console.log("Error",errorThrow)
            }
        })
    });

    $('#myTable').on('focusout','td.customerName[contenteditable="true"]',function () {
        let customerName = $(this).text();
        let row = $(this).closest("tr");
        let customerPhoneCell = row.find("td:eq(7)");
        let valueCurrentCustomerName = $(this);
        $.ajax({
            contentType :"application/json",
            method : "GET",
            url: `http://localhost:8081/api/customer/getCustomerPhone?customerName=`+encodeURIComponent(customerName),
            success:function (data) {
                if (data === ""){
                    valueCurrentCustomerName.text('')
                    customerPhoneCell.text('');
                    Swal.fire({
                        title: 'Không tìm được dữ liệu',
                        icon: 'error',
                        confirmButtonColor: 'red'
                    });
                }
                else if (data === "errorLogin"){
                    location.reload();
                    localStorage.clear();
                }else {
                    customerPhoneCell.text(data);
                }
                if(data !== "errorLogin"){
                    let id = row.attr('id');
                    let rowData = {
                        id: id,
                        productCode: row.find('.productCode').text(),
                        productName: row.find('.productName').text(),
                        quantity: row.find('.quantity').text(),
                        customerName: row.find('.customerName').text(),
                        customerPhone: row.find('.customerPhone').text(),
                        version : row.find('.hidden').text(),
                        page : row.find('.page').text()
                    };
                    console.log(rowData);
                    checkChangeValue(id,rowData)
                }
                
               
            },
            error:function (xhr,textStatus,errorThrow) {
                console.log("Error",errorThrow)
            }

        })
    });

    $('#myTable').on('focusout','td.customerPhone[contenteditable="true"]',function () {
        // Thực hiện yêu cầu Ajax khi ô phoneNumber mất focus
        let customerPhone = $(this).text();
        let row = $(this).closest("tr");
        let customerName = row.find("td:eq(6)");
        let valueCurrentCustomerPhone = $(this);
        $.ajax({
            contentType :"application/json",
            method : "GET",
            url : `http://localhost:8081/api/customer/getCustomerName?customerPhone=`+encodeURIComponent(customerPhone),
            success:function (data) {
                if (data === ""){
                    valueCurrentCustomerPhone.text('');
                    customerName.text('');
                    Swal.fire({
                        title: 'Không tìm được dữ liệu',
                        icon: 'error',
                        confirmButtonColor: 'red'
                    });
                }else if (data === "errorLogin"){
                    location.reload();
                    localStorage.clear();
                }else {
                    customerName.text(data);
                }
                if(data !== "errorLogin" ){
                    let id = row.attr('id');
                    let rowData = {
                        id: id,
                        productCode: row.find('.productCode').text(),
                        productName: row.find('.productName').text(),
                        quantity: row.find('.quantity').text(),
                        customerName: row.find('.customerName').text(),
                        customerPhone: row.find('.customerPhone').text(),
                        version : row.find('.hidden').text(),
                        page : row.find('.page').text()
                    };
                    checkChangeValue(id,rowData);
                }
               
            },
            error:function (xhr,textStatus,errorThrow) {
                console.log("Error",errorThrow)
            }

        })
    });

    $('#myTable').on('focusout','td.quantity[contenteditable="true"]',function () {
        let row = $(this).closest("tr");
        console.log(row)
        let id = row.attr('id');
        let rowData = {
            id: id,
            productCode: row.find('.productCode').text(),
            productName: row.find('.productName').text(),
            quantity: row.find('.quantity').text().trim(),
            customerName: row.find('.customerName').text(),
            customerPhone: row.find('.customerPhone').text(),
            version : row.find('.hidden').text(),
            page : row.find('.page').text()
        };
        checkChangeValue(id,rowData);
    })
   
});

function render(data) {
    let tbody = $('#myTable');
    data.forEach(function (row) {
        if (isNaN(Number(`${row.id}`))) {
            console.log(isNaN(Number(`${row.id}`)));
            tbody.append(`<tr id=${row.id}>
									<td></td>
									<td></td>
									<td class="productCode" contenteditable="true">${row.productCode}</td>
									<td class="productName" contenteditable="true">${row.productName}</td>
									<td></td>
									<td class="quantity" contenteditable="true">${row.quantity}</td>
									<td class="customerName" contenteditable="true">${row.customerName}</td>
									<td class="customerPhone"  contenteditable="true">${row.customerPhone}</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								  </tr>`)
        } else {
            let id = `${row.id}`;
            tbody.find('tr[id="' + id + '"]').find('.productCode').text(`${row.productCode}`);
            tbody.find('tr[id="' + id + '"]').find('.productName').text(`${row.productName}`);
            tbody.find('tr[id="' + id + '"]').find('.quantity').text(`${row.quantity}`);
            tbody.find('tr[id="' + id + '"]').find('.customerName').text(`${row.customerName}`);
            tbody.find('tr[id="' + id + '"]').find('.customerPhone').text(`${row.customerPhone}`);
        }
    })
}

function handleCellErrors(response) {
    for (let key in response){
        if (response.hasOwnProperty(key)){
            let  valueList = response[key];
            console.log(key);
            console.log(valueList);
            valueList.forEach(function (value) {
                let table = $('#myTable');
                table.find('tr[id="' + key + '"]').find('td.'+value).css('background-color','red');
            })
        }
    }
}