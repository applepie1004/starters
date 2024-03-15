"use strict";
$(function($) {
    $('#signupBtn').click(() => {
        const signupFormData = $('#signupForm').serializeArray();

        if($('#id').val() == null || $('#id').val() == undefined ||$('#id').val() == '' ) {
            errorMsg($('#id'), 'Fill in the blanks')
            return;
        }
        $('#id').parent().removeClass('error');

        /** 이메일양식 검증 */
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if(!emailRegex.test($('#email').val())) {
            errorMsg($('#email'), 'Not an email format.')
            return;
        }
        $('#email').parent().removeClass('error');

        /** 비밀번호 검증1 -양식 */
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*?&]{8,}$/;
        if(!passwordRegex.test($('#password').val())) {
            errorMsg($('#password'), 'Minimum 8 characters, At least one letter and one number')
            return;
        }
        $('#password').parent().removeClass('error');

        /** 비밀번호 검증2 - 같은지 */
        if($('#password').val() != $('#confirmPassword').val()) {
            errorMsg($('#confirmPassword'), 'Different from the entered password.')
            return;
        }
        $('#confirmPassword').parent().removeClass('error');


        new Promise((checkIdDone) => {
            /** 아이디 확인 */
            checkIdDone();
        }).then(() => {
            new Promise((checkEmailDone) => {
                /** 이메일 확인 */
                checkEmailDone();
            }).then(() => {
                /** 가입 */
                const param = {}
                for(let itm of signupFormData) {
                    param[itm.name] = itm.value;
                }
                console.log(param)
            })
        })

    })
})