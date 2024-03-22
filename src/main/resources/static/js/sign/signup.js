"use strict";
$(function($) {
    $('#signupBtn').click(() => {
        const signupFormData = $('#signupForm').serializeArray();

        const $id = $('#id');
        if($id.val() == null || $id.val() === undefined || $id.val() === '' ) {
            errorMsg($id, 'Fill in the blanks')
            return;
        }
        $id.parent().removeClass('error');

        /** 이메일양식 검증 */
        const $email = $('#email');
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if(!emailRegex.test($email.val())) {
            errorMsg($email, 'Not an email format.')
            return;
        }
        $email.parent().removeClass('error');

        /** 비밀번호 검증1 -양식 */
        const $password = $('#password');
        const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*?&]{8,}$/;
        if(!passwordRegex.test($password.val())) {
            errorMsg($password, 'Minimum 8 characters, At least one letter and one number')
            return;
        }
        $password.parent().removeClass('error');

        /** 비밀번호 검증2 - 같은지 */
        const $confirmPassword = $('#confirmPassword');
        if($password.val() !== $confirmPassword.val()) {
            errorMsg($confirmPassword, 'Different from the entered password.')
            return;
        }
        $confirmPassword.parent().removeClass('error');


        new Promise((checkIdDone) => {
            /** 아이디 확인 */
            $.ajax({
                url: '/sign/rest/check/id',
                type: 'POST',
                data: JSON.stringify({checkString : $id.val()}),
                dataType: 'json',
                contentType: 'application/json',
                success: function(res) {
                    /* false가 떨어져야 아이디가 없는 것 */
                    if(!res.DATA.isIdExist) {
                        checkIdDone();
                    } else {
                        errorMsg($id, 'id already exists');
                    }
                },
                error: function(e) {
                    alert('Error: ' + e);
                }
            });
        }).then(() => {
            new Promise((checkEmailDone) => {
                /** 이메일 확인 */
                $.ajax({
                    url: '/sign/rest/check/email',
                    type: 'POST',
                    data: JSON.stringify({checkString : $email.val()}),
                    dataType: 'json',
                    contentType: 'application/json',
                    success: function(res) {
                        /* false가 떨어져야 이메일이 없는 것 */
                        if(!res.DATA.isEmailExist) {
                            checkEmailDone();
                        } else {
                            errorMsg($email, 'email already exists');
                        }
                    },
                    error: function(e) {
                        alert('Error: ' + e);
                    }
                });
            }).then(() => {
                /** 가입 */
                const param = {}
                for(let itm of signupFormData) {
                    param[itm.name] = itm.value;
                }
                $.ajax({
                    url: '/sign/rest/signup',
                    type: 'POST',
                    data: JSON.stringify(param),
                    dataType: 'json',
                    contentType: 'application/json',
                    success: function(res) {
                        /* false가 떨어져야 아이디가 없는 것 */
                        if(res.RESULT === 'SUCCESS') {
                            alert("You have successfully signed up.");
                            window.location.replace("/login");
                        }
                    },
                    error: function(e) {
                        alert('Error: ' + e);
                    }
                });
            })
        })

    })
})