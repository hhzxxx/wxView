
function login() {
    window.location.href = '/bysj/index';
}
$("#btn_login").click(function () {
    if(deal()===true) {
        var data = JSON.stringify($("#registerForm").serializeJson());
        $.ajax({
            url: ctx +"login",
            data: data,
            type:"post",
            contentType : "application/json; charset=utf-8",
            success: function (res) {
                if(res.code==="99"){
                    alert(res.message?res.message:"登陆失败");
                }else if(res.code==="200"){
                    window.location.href = ctx+ 'index';
                }else {
                    alert(res.message?res.message:"未知错误");
                }
            }
        });
    }
})

$("#btn_register").click(function () {
    if(deal()===true){
        var name = $.trim($("#name").val());
        if (name === "") {
            alert("用户名不能为空，请重新输入！");
            return false;
        }else {
            var data = JSON.stringify($("#registerForm").serializeJson());
            $.ajax({
                url: ctx +"register",
                data: data,
                type:"post",
                contentType : "application/json; charset=utf-8",
                success: function (res) {
                    if(res.code==="99"){
                        alert(res.message?res.message:"注册失败");
                    }else if(res.code==="200"){
                        alert(res.message?res.message:"注册成功");
                    }else {
                        alert(res.message?res.message:"未知错误");
                    }
                }
            });
        }
    }
})

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
