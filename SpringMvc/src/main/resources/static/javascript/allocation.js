$(document).ready(function () {

    $('#addRow').on('click',function () {
        let newIndex = $('#myTable tbody tr').length;
        console.log(newIndex);
        $('#myTable tbody').append('<tr> ' +
            '                <td></td>  ' +
            '                <td>   '+
            '                   <input name="allocationList['+newIndex +'].productCode" class="productCode" type="text" placeholder="Mã sản phẩm" > ' +
            '                </td> '+
            '                <td> ' +
            '                   <input name="allocationList['+newIndex +'].productName" class="productName" type="text" placeholder="Tên sản phẩm" style="width: 70%"> ' +
            '                </td> '	+
            '                <td> ' +
            '                	<input name="allocationList['+newIndex +'].quantity" type="number"  placeholder="Số lượng"  style="width: 38%"> ' +
            '                </td>'	+
            '               </tr>')

    });




    $('#myTable tbody').on('focusout','td input.productCode',function () {
        let productCode = $(this).val();
        console.log(productCode);
        let row = $(this).closest("tr");
        let productName = row.find("input.productName");
        let valueProductCode = $(this);
        $.ajax({
            contentType :"application/json",
            method : "GET",
            url : `http://localhost:8081/api/product/getProductName?productCode=`+encodeURIComponent(productCode),
            success : function(data) {
                console.log(data);
                if (data === "") {
                    productName.val('')
                    valueProductCode.val('')
                    Swal.fire({
                        title: 'Không tìm thấy dữ liệu.',
                        icon: 'error',
                        confirmButtonColor: 'red'
                    });
                }
                else {
                    productName.val(data)
                }
            },
            error:function(xhr, textStatus, errorThrow){
                console.log("Error",errorThrow)
            }
        })
    });

    $('#myTable tbody').on('focusout','input.productName',function () {
        let productName = $(this).val();
        let row = $(this).closest("tr");
        let productCodeCell = row.find("input.productCode");
        let valueProductName = $(this);
        $.ajax({
            contentType :"application/json",
            method : "GET",
            url : `http://localhost:8081/api/product/getProductCode?productName=`+encodeURIComponent(productName),
            success : function(data) {
                console.log(data)
                if (data === "") {
                    productCodeCell.val('')
                    valueProductName.val('')
                    Swal.fire({
                        title: 'Không tìm thấy dữ liệu.',
                        icon: 'error',
                        confirmButtonColor: 'red'
                    });
                }
                else {
                    productCodeCell.val(data)
                }
            },
            error:function(xhr, textStatus, errorThrow){
                console.log("Error",errorThrow)
            }
        })
    });

});





