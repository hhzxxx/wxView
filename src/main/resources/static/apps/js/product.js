$(function () {
    var proPageSize = 10;
    var proPageNum = 1;
    var proTotalPages = 0;
    $('#productForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            productName: {
                message: '添加失败',
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    }
                }
            },
            price: {
                validators: {
                    notEmpty: {
                        message: '价格不能为空'
                    }
                }
            }
        }
    });
    $("#btn_submit").click(function () {
        var bv = $("#productForm").data('bootstrapValidator');
        bv.validate();
        if (bv.isValid()) {
            var data = JSON.stringify($("#productForm").serializeJson());
            console.log(data)
            $.ajax({
                url: ctx + "saveFrom",
                data: data,
                type: "post",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    window.location.reload()
                    // if (res.code === "99") {
                    //     alert(res.message ? res.message : "登陆失败");
                    // } else if (res.code === "200") {
                    //     window.location.href = ctx + 'index';
                    // } else {
                    //     alert(res.message ? res.message : "未知错误");
                    // }
                }
            });
        }
    });
    <!--        $("#btn_add").click(function (){-->
    <!--            $("#myModalLabel").text("新增");-->
    <!--            $('#con-close-modal').modal();-->
    <!--        })-->
    $("#changePageNext").click(function () {
        // debugger
        if(proTotalPages != proPageNum){
            proPageNum = proPageNum + 1;
        }else{
            return
        }
        console.log(proPageNum)
        var obj = {
            pageSize:proPageSize,
            pageNum: proPageNum, //页数
            rules: []
        };
        console.log(obj,123456);
        $.ajax({
            url: ctx + "findDetail",
            data: JSON.stringify(obj),
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                proTotalPages = res.data.totalPages;
                console.log(res);
                // $("#id_table1").html(data.data.content);
                var inf = "<thead><tr>\n" +
                    "                            <th>id</th>\n" +
                    "                            <th>名称</th>\n" +
                    "                            <th>价格</th>\n" +
                    "                            <th>图片</th>\n" +
                    "                            <th>操作</th>\n" +
                    "                        </tr></thead>";
                /*<![CDATA[*/
                res.data.content.forEach(function (item) {
                    /*]]*/
                    inf += "<tbody><tr><td>" + item.id + "</td><td>" + item.productName + "</td><td>" + item.price + "</td></tr></tbody>"
                });

                $("#table1").empty();
                $("#table1").append(inf);
            }
        });
    });
    $("#changePagePre").click(function () {
        if(proPageNum!= 1) {
            proPageNum = proPageNum - 1;
        }else{
            return
        }
        var obj = {
            pageSize:proPageSize,
            pageNum: proPageNum, //页数
            rules: []
        };
        console.log(obj,123456);
        $.ajax({
            url: ctx + "findDetail",
            data: JSON.stringify(obj),
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                proTotalPages = res.data.totalPages;
                console.log(res);
                // $("#id_table1").html(data.data.content);
                var inf = "<thead><tr>\n" +
                    "                            <th>id</th>\n" +
                    "                            <th>名称</th>\n" +
                    "                            <th>价格</th>\n" +
                    "                            <th>图片</th>\n" +
                    "                            <th>操作</th>\n" +
                    "                        </tr></thead>";
                /*<![CDATA[*/
                res.data.content.forEach(function (item) {
                    /*]]*/
                    inf += "<tbody><tr><td>" + item.id + "</td><td>" + item.productName + "</td><td>" + item.price + "</td></tr></tbody>"
                });
                $("#table1").empty();
                $("#table1").append(inf);
            }
        });
    });
    window.onload = function(){
        var obj = {
            pageSize:proPageSize,
            pageNum: proPageNum, //页数
            rules: []
        };
        console.log(obj,123456);
        $.ajax({
            url: ctx + "findDetail",
            data: JSON.stringify(obj),
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                proTotalPages = res.data.totalPages;
                console.log(res);
                // $("#id_table1").html(data.data.content);
                var inf = "<thead><tr>\n" +
                    "                            <th>id</th>\n" +
                    "                            <th>名称</th>\n" +
                    "                            <th>价格</th>\n" +
                    "                            <th>图片</th>\n" +
                    "                            <th>操作</th>\n" +
                    "                        </tr></thead>";
                /*<![CDATA[*/
                res.data.content.forEach(function (item) {
                    /*]]*/
                    inf += "<tbody><tr><td>" + item.id + "</td><td>" + item.productName + "</td><td>" + item.price + "</td></tr></tbody>"
                });
                $("#table1").append(inf);
            }
        });
    }
});

