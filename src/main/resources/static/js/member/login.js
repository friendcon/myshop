$(document).ready(function () {

    $("#inputId").on("focus", function () {
        const element = $("#idErrorBox");
        element.hide();
    })

    $("#inputId").on("blur", function () {
        const element = $("#idErrorBox");
        const inputId = $("#inputId").val().trim();
        if(inputId === '') {
            element.show();
            element.text("공백을 제외한 아이디를 입력해주세요.");
        }
    })

    $("#inputPassword").on("focus", function () {
        const element = $("#passwordErrorBox");
        element.hide();
    })

    $("#inputPassword").on("blur", function () {
        const element = $("#passwordErrorBox");
        const inputId = $("#inputPassword").val().trim();
        if(inputId === '') {
            element.show();
            element.text("공백을 제외한 비밀번호를 입력해주세요.");
        }
    })
})