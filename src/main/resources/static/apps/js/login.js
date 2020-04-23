function login() {
    window.location.href = '/bysj/index';
}

$(function () {
    $('#loginForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            loginAccount: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '账号不能为空'
                    }
                }
            },
            loginPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }
        }
    });
    $("#btn_login").click(function () {
        var bv = $("#loginForm").data('bootstrapValidator');
        bv.validate();
        console.log(bv.isValid(), 123)
        if (bv.isValid()) {
            // var data = JSON.stringify($("#loginForm").serializeJson());
            var data  = $("#loginForm").serializeJson();
            // function getObjFirst(obj){
            //     for(var i in obj) return obj[i];
            // }
            // console.log(getObjFirst($("#loginForm").serializeJson()))
            var obj={
                account:data.loginAccount,
                password:data.loginPassword,
                name:data.loginName
            };
            console.log(obj);
            $.ajax({
                url: ctx + "login",
                data: JSON.stringify(obj),
                type: "post",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res.code === "99") {
                        alert(res.message ? res.message : "登陆失败");
                    } else if (res.code === "200") {
                        window.location.href = ctx + 'index';
                    } else {
                        alert(res.message ? res.message : "未知错误");
                    }
                }
            });
        }
    });
    $('#registerForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            account: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '账号不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }
        }
    });
    $("#btn_register").click(function () {
        var bv = $("#registerForm").data('bootstrapValidator');
        bv.validate();
        if (bv.validate()) {
            var data = JSON.stringify($("#registerForm").serializeJson());
            $.ajax({
                url: ctx + "register",
                data: data,
                type: "post",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res.code === "99") {
                        alert(res.message ? res.message : "注册失败");
                    } else if (res.code === "200") {
                        alert(res.message ? res.message : "注册成功");
                    } else {
                        alert(res.message ? res.message : "未知错误");
                    }
                }
            });
        }
    });
});
$(function () {
    //获取点击事件的对象
    // debugger
    $(".nav li").click(function () {
        //获取要显示或隐藏的对象
        var divShow = $(".content").children('.list');
        //判断当前对象是否被选中，如果没选中的话进入if循环
        if (!$(this).hasClass('selected')) {
            //获取当前对象的索引
            var index = $(this).index();
            //当前对象添加选中样式并且其同胞移除选中样式；
            $(this).addClass('selected').siblings('li').removeClass('selected');
            //索引对应的div块显示
            $(divShow[index]).show();
            //索引对应的div块的同胞隐藏
            $(divShow[index]).siblings('.list').hide();
        }
    });
});


function deal() {
    var account = $.trim($("#account").val());
    var password = $.trim($("#password").val());

    if (account === "") {
        alert("用户登录帐号不能为空，请重新输入！");
        return false;
    } else if (password === "") {
        alert("密码不能为空，请重新输入！");
        return false;
    } else if (password.length < 6) {
        alert("密码长度不能小于6，请重新输入！");
        return false;
    }
    return true;
}
