$(function () {
    var proPageSize = 10;
    var proPageNum = 1;
    var proTotalPages = 0;
    var currentData = [];
    var proId = "";
    var selectedId = "";
    var typeList = [];
    var brandList = [];
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
            },
            brandId: {
                validators: {
                    notEmpty: {
                        message: '品牌不能为空'
                    }
                }
            },
            typeId: {
                validators: {
                    notEmpty: {
                        message: '分类不能为空'
                    }
                }
            }
        }
    });
    $("#btn_submit").click(function () {
        if (!proId) {
            var bv = $("#productForm").data('bootstrapValidator');
            bv.validate();
            if (bv.isValid()) {
                var data = JSON.stringify($("#productForm").serializeJson());
                console.log(data);
                $.ajax({
                    url: ctx + "saveFrom",
                    data: data,
                    type: "post",
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
                        if (res.code === "200") {
                            alert(res.message ? res.message : "添加成功");
                        } else {
                            alert(res.message ? res.message : "添加失败");
                        }
                        window.location.reload();
                    }
                });
            } else {
                $('#con-close-modal').modal('show');
            }
        } else {
            $('#productForm').data('bootstrapValidator', null);
            $('#productForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    productName: {
                        message: '修改失败',
                        validators: {
                            notEmpty: {
                                message: '名称不能为空'
                            },
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
            var flag = $("#productForm").data('bootstrapValidator');
            flag.validate();
            console.log(flag.isValid(), '测试修改');
            if (flag.isValid()) {
                var data = JSON.stringify($("#productForm").serializeJson());
                var obj = {
                    id: proId,
                    brandId: $("#productForm").serializeJson().brandId,
                    typeId: $("#productForm").serializeJson().typeId,
                    productName: $("#productForm").serializeJson().productName,
                    price: $("#productForm").serializeJson().price,
                    pic: $("#productForm").serializeJson().pic
                };
                $.ajax({
                    url: ctx + "updateDetail",
                    data: JSON.stringify(obj),
                    type: "post",
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
                        window.location.reload();
                        if (res.code === "200") {
                            alert(res.message ? res.message : "修改成功");
                        } else {
                            alert(res.message ? res.message : "修改失败");
                        }
                    }
                });
            } else {
                $('#con-close-modal').modal('show');
            }
        }
    });
    window.onload = function () {
        var obj = {
            pageSize: proPageSize,
            pageNum: proPageNum, //页数
            rules: []
        };
        $.ajax({
            url: ctx + "findDetail",
            data: JSON.stringify(obj),
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                proTotalPages = res.data.totalPages;
                currentData = res.data.content;
                // $("#id_table1").html(data.data.content);
                var inf = "<thead><tr>\n" +
                    "                            <th>id</th>\n" +
                    "                            <th>品牌</th>\n" +
                    "                            <th>分类</th>\n" +
                    "                            <th>名称</th>\n" +
                    "                            <th>价格</th>\n" +
                    "                            <th>图片</th>\n" +
                    "                            <th>操作</th>\n" +
                    "                        </tr></thead>";
                /*<![CDATA[*/
                res.data.content.forEach(function (item) {
                    /*]]*/
                    inf += "<tbody><tr><td>" + item.id + "</td><td>" + item.brandname + "</td><td>" + item.typeName + "</td><td>" + item.productName + "</td><td>" + item.price + "<td>" + item.price + "<td>"
                        + "<button class=\"btn btn-primary\" onclick='changePro(" + JSON.stringify(item) + ")' >修改</button><button class=\"btn btn-primary\" id='delete' onclick='delPro(" + JSON.stringify(item) + ")'>删除</button>" + "</td></tr></tbody>"
                });
                $("#table1").append(inf);
                var pageCount = proTotalPages; //取到pageCount的值(把返回数据转成object类型)
                var currentPage = proPageNum; //得到urrentPage
                var options = {
                    bootstrapMajorVersion: 3, //版本
                    currentPage: currentPage, //当前页数
                    totalPages: pageCount, //总页数
                    itemTexts: function (type, page, current) {
                        switch (type) {
                            case "first":
                                return "首页";
                            case "prev":
                                return "上一页";
                            case "next":
                                return "下一页";
                            case "last":
                                return "末页";
                            case "page":
                                return page;
                        }
                    },//点击事件，用于通过Ajax来刷新整个list列表
                    onPageClicked: function (event, originalEvent, type, page) {
                        // debugger
                        var obj = {
                            pageSize: proPageSize,
                            pageNum: page, //页数
                            rules: []
                        };
                        $.ajax({
                            url: ctx + "findDetail",
                            data: JSON.stringify(obj),
                            type: "post",
                            contentType: "application/json; charset=utf-8",
                            success: function (res) {
                                proTotalPages = res.data.totalPages;
                                // $("#id_table1").html(data.data.content);
                                var inf = "<thead><tr>\n" +
                                    "                            <th>id</th>\n" +
                                    "                            <th>品牌</th>\n" +
                                    "                            <th>分类</th>\n" +
                                    "                            <th>名称</th>\n" +
                                    "                            <th>价格</th>\n" +
                                    "                            <th>图片</th>\n" +
                                    "                            <th>操作</th>\n" +
                                    "                        </tr></thead>";
                                /*<![CDATA[*/
                                res.data.content.forEach(function (item) {
                                    /*]]*/
                                    inf += "<tbody><tr><td>" + item.id + "</td><td>" + item.brandname + "</td><td>" + item.typeName + "</td><td>" + item.productName + "</td><td>" + item.price + "<td>" + item.price + "<td>"
                                        + "<button class=\"btn btn-primary\" onclick='changePro(" + JSON.stringify(item) + ")' >修改</button><button class=\"btn btn-primary\" id='delete' onclick='delPro(" + JSON.stringify(item) + ")'>删除</button>" + "</td></tr></tbody>"
                                });
                                $("#table1").empty();
                                $("#table1").append(inf);
                            }
                        });
                    }
                };
                $("#pageLimit").bootstrapPaginator(options);
            }
        });
        $.ajax({
            url: ctx + "findBand",
            data: '',
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                console.log(res.data);
                brandList = res.data;
                for (var i = 0; i < brandList.length; i++) {
                    // console.log(funList[i].id)
                    $("#brandSelect").append("<option value='" + brandList[i].id + "'>" + brandList[i].brandname + "</option>");
                }
                ;
                //使用refresh方法更新UI以匹配新状态。
                $('#brandSelect').selectpicker('refresh');
                //render方法强制重新渲染引导程序 - 选择ui。
                $('#brandSelect').selectpicker('render');
            }
        });
        $.ajax({
            url: ctx + "findType",
            data: '',
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                console.log(res.data);
                typeList = res.data;
                for (var i = 0; i < typeList.length; i++) {
                    // console.log(funList[i].id)
                    $("#typeSelect").append("<option value='" + typeList[i].id + "'>" + typeList[i].typename + "</option>");
                }
                ;
                //使用refresh方法更新UI以匹配新状态。
                $('#typeSelect').selectpicker('refresh');
                //render方法强制重新渲染引导程序 - 选择ui。
                $('#typeSelect').selectpicker('render');
            }
        });
    };
    changePro = function (e) {
        proId = e.id;
        var obj = {
            id: e.id
        };
        $.ajax({
            url: ctx + "findById",
            data: JSON.stringify(obj),
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                $('#con-close-modal').modal('show');
                // $("#brandSelect").empty();
                // $("#typeSelect").empty();
                for (var i = 0; i < brandList.length; i++) {
                    if(brandList[i].id == res.data.brandId){
                        var id = res.data.brandId;
                        $("#brandSelect").val(res.data.brandId);
                        $('#brandSelect').selectpicker('refresh');
                        //render方法强制重新渲染引导程序 - 选择ui。
                        $('#brandSelect').selectpicker('render');
                    }
                }
                for (var i = 0; i < typeList.length; i++) {
                    if(typeList[i].id == res.data.typeId){
                        var id = res.data.typeId;
                        $("#typeSelect").val(res.data.typeId);
                        $('#typeSelect').selectpicker('refresh');
                        //render方法强制重新渲染引导程序 - 选择ui。
                        $('#typeSelect').selectpicker('render');
                    }
                }
                $("#productName").val(res.data.productName);
                $("#price").val(res.data.price);
                $("#pic").val(res.data.pic);
            }
        });
        // console.log(proId);
        // $('#con-close-modal').modal('show');
        // var inf = "<div class=\"form-group\">" + "<label class=\"col-md-12\">名称<div lass=\"col-md-12\"><input type=\"text\" placeholder=\"请输入名称\"\n" +
        //     "                                               class=\"form-control form-control-line\" name=\"productName\"\n" +
        //     "                                               id=\"productName\" value=" + e.productName + "></div></label>\n" + "<label class=\"col-md-12\">价格<div lass=\"col-md-12\"><input type=\"text\" placeholder=\"请输入价格\"\n" +
        //     "                                               class=\"form-control form-control-line\" name=\"price\"\n" +
        //     "                                               id=\"price\" value=" + e.price + "></div></label>\n" + "<label class=\"col-md-12\">图片<div lass=\"col-md-12\"><input  placeholder=\"请添加图片\"\n" +
        //     "                                               class=\"form-control form-control-line\" name=\"pic\"\n" +
        //     "                                               id=\"pic\" value=" + e.pic + "></div></label>\n" + "</div>";
        // $("#productForm").empty();
        // $("#productForm").append(inf);
    };
    delPro = function (e) {
        var id = e.id;
        console.log(id);
        //alert("确定要删除"+id+"吗？");
        var obj = {
            id: id
        };
        if (confirm('确定要删除吗？')) {
            $.ajax({
                url: ctx + "delDetail",
                data: JSON.stringify(obj),
                type: "post",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res.code === "200") {
                        alert(res.message ? res.message : "修改成功");
                    } else {
                        alert(res.message ? res.message : "修改失败");
                    }
                    window.location.reload();
                }
            });
        } else {
            alert('删除失败！')
        }
    };
    $("#btn_add").click(function (e) {
        $('#con-close-modal').modal('show');
    });
    $("#btn_close").click(function (e) {
        $('#con-close-modal').modal('hide');
        window.location.reload();
    });
    // $("#brandSelect").change(function (e) {
    //     var options = $("#brandSelect option:selected");
    //     // selectedId = options.val();
    //     alert(options.val());
    // });
});

