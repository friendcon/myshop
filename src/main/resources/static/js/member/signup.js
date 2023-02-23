$(document).ready(function () {


    function checkDuplicate(memberId) {
        let result;
        const url = "/member/checkId/"+memberId;
        console.log(url);
        $.ajax({
            type: "get",
            url: "/member/checkId/"+memberId,
            success: function (data) {
                alert(data.message);
                $("#isDuplicate").val(false);
                $("#idIsCorrect").val(true);
            },
            error: function (request, status, error) {
                const errorCode = request.status;
                const errorJson = JSON.parse(request.responseText);
                if(errorCode == 406) {
                    alert(errorJson.message);
                } else if(errorCode == 404) {
                    alert("아이디는 6~20자의 소문자, 숫자로 이루어져야 합니다.");
                }
                $("#isDuplicate").val(true);
                $("#idIsCorrect").val(true);
            }
        })
    }

    function signUp() {
        const signUp = {
            username : $("#inputId").val(),
            password : $("#inputPassword").val(),
            email : $("#inputEmail").val(),
            profileImgUrl : "hello",
            profileImgTumUrl : "hello",
            isIdDuplicate : $("#isDuplicate").val(),
            isIdCorrect : $("#idIsCorrect").val(),
            passwordIsCorrect : $("#passwordIsCorrect").val()
        }

        $.ajax({
            type: "post",
            url: "/member",
            data: signUp,
            success: function (data) {
                alert("회원가입 성공!")
                location.replace("/");
            },
            error: function (request, status, error) {
                const errorCode = request.status;ㄹ
                if(errorCode == 400) {
                    alert("입력 값을 확인해주세요")
                }

            }
        })
    }

    $("#signupBtn").on("click", function (e) {
        signUp();
    })

    $("#checkDupli").on("click", function (e) {
        let id = $("#inputId").val();
        checkDuplicate(id);
    }) // 아이디 중복체크

    $("#inputId").on("blur", function (e) {
        let id = $("#inputId").val();
        const idPattern = /^[a-z](?=.*?[a-z])(?=.*?[0-9])[a-z0-9]{5,20}/;

        if(!idPattern.test(id)) {
            let element = $("#idErrorBox");
            element.show();
            element.text("아이디는 6~20자의 소문자, 숫자로 이루어져야 합니다.");
        }
    }) // focus out 아이디 체크

    $("#inputId").on("focus", function (e) {
        let element = $("#idErrorBox");
        element.hide();
    }) // focus in 에러박스 숨김

    $("#inputPassword").on("blur", function (e) {
        let password = $("#inputPassword").val();
        const passwordPattern = /^[a-z](?=.*?[a-z])(?=.*?[0-9])(?=.*?[~!@#$%^&*_-])[a-z0-9~!@#$%^&*_-]{7,20}/;

        if(!passwordPattern.test(password)) {
            let element = $("#passwordErrorBox");
            element.show();
            element.text("비밀번호는 8~20자의 소문자, 숫자, 특수문자로 이루어져야 합니다.");
            $("#passwordIsCorrect").val(false);
        } else {
            $("#passwordIsCorrect").val(true);
        }
    }) // focus in 비밀번호 체크

    $("#inputPassword").on("focus", function (e) {
        let element = $("#passwordErrorBox");
        element.hide();
    }) // focus out 비밀번호 에러박스 숨김

    $("#inputEmail").on("blur", function (e) {
        let email = $("#inputEmail").val();
        const emailPattern = /[a-z0-9]+@[a-z]+.[a-z]{2,3}/;
        if(!emailPattern.test(email)) {
            let element = $("#emailErrorBox");
            element.show();
            element.text("이메일 형식에 맞게 입력해주세요.");
            $("#emailIsCorrect").val(false);
        } else {
            $("#emailIsCorrect").val(true)
        }
    })

    $("#inputEmail").on("focus", function(e) {
        let element = $("#emailErrorBox");
        element.hide();
    })


})