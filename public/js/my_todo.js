/**
 * Created by Stankoech on 12/2/2017.
 */


$(function () {
    // function deleteCategory(id){
    //     alert(id);
    // }

    $('#btnDeljj').on('click',function () {

        swal({      title: "Sure you want to Delete this item?",
                text:  "Pressing Yes will delete your data permanently",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-primary",
                confirmButtonText: "Yes!",
                cancelButtonText: "Cancel!",
                closeOnConfirm: false,
                closeOnCancel: false
            },
            function(isConfirm){
                if (isConfirm){
                    $.ajax({
                        type: "POST",
                        url: '/deletecat',
                        dataType: 'json',
                        data: $('form[name=frmReg]').serialize(),
                        success: function (response) {
                            console.log(response);
                            if (response.code==='200'){
                                swal({
                                    title: response.title,
                                    text: response.message,
                                    type: "success"
                                });
                                window.location = '/';
                            }
                            else{
                                swal({
                                    title: response.title,
                                    text: response.message,
                                    type: "error"
                                });
                            }
                        },
                        error: function (error) {
                            console.log(error) ;
                        }
                    });
                }
                else {
                    swal("Cancelled", "Go back and edit your details :)", "error");
                }
            });

    });
});


