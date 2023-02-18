$(document).ready(function () {
    function checkDuplicate(memberId) {
        var result;
        var url = "/member/checkId/"+memberId;
        console.log(url);
        $.ajax({
            type: "get",
            url: "/member/checkId/"+memberId,
            success: function (data) {
                alert(data.message);
                $("#isDuplicate").val(true);
            },
            error: function (request, status, error) {
                var errorCode = request.status;
                var errorJson = JSON.parse(request.responseText);
                if(errorCode == 406) {
                    alert(errorJson.message);
                }
                $("#isDuplicate").val(false);
            }
        })
    }

    $("#checkDupli").on("click", function (e) {
        var id = $("#inputId").val();
        console.log(id);
        checkDuplicate(id);
    })
})