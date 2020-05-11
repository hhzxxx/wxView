$(function () {
    $('#submitForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '*用户名不能为空'
                    }
                }
            },
            account: {
                validators: {
                    notEmpty: {
                        message: '*账号不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '*密码不能为空'
                    },
                    stringLength: {
                        min : 6,
                        message: '*密码最少6位'
                    }
                }
            },
            password1: {
                validators: {
                    notEmpty: {
                        message: '*密码不能为空'
                    },
                    identical: {
                        field: 'password',
                        message: '*两次输入密码不一致'
                    }
                }
            }
        }
    });

    $("#btn_submit").click(function () {
        var bv = $("#submitForm").data('bootstrapValidator');
        bv.validate();
        if (bv.isValid()) {
            var password = $("#password").val();
            var password1 = $("#password1").val();
            if(password.length>6){
                if(password!==password1){
                    alert("请确认新密码！")
                    return;
                }
            }
            var data = {
                name:$("#name").val(),
                remark:$("#remark").val(),
                password:password,
                id:$("#id").val()
            };
            $.ajax({
                url: ctx + "Profile/save",
                data: JSON.stringify(data),
                type: "post",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res.code === "200") {
                        alert(res.message ? res.message : "修改成功,请重新登陆！");
                        window.location.href = ctx+"exit";
                    } else {
                        alert(res.message ? res.message : "修改失败");
                    }
                }
            });
        } else {
            $('#con-close-modal').modal('show');
        }
    })

    var init = function(){
        $.ajax({
            url: ctx + "Profile/get",
            type: "get",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                if (res.code === "200") {
                    $("#name").val(res.data.name);
                    $("#account").val(res.data.account);
                    $("#remark").val(res.data.remark?res.data.remark:"");
                    $("#id").val(res.data.id)
                } else {
                    alert(res.message ? res.message : "");
                }
            }
        });
    }

    window.onload = function () {
        init();
    }
})