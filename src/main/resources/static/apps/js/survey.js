$(function () {
    $(".selectpicker").selectpicker({
        noneSelectedText : '请选择'//默认显示内容
    });

    var data = {
        brandIds: [],
        typeIds: []
    };
    var obj = {
        typelist1 : [],
        brandlist1 : [],
        tastelist1 : [],
        templist1 : [],
        productlist1 : [],
        remark : "",
        openid : ""
    }

    $('#productForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            remark: {
                validators: {
                    stringLength: {
                        max : 250,
                        message: '*最多250个字'
                    }
                }
            }
        }
    });

    var init=function(){
        $.ajax({
            url: ctx + "hobbySurveyPage/findBand",
            data: '',
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                for (var i = 0; i < res.data.length; i++) {
                    $("#brandSelect").append("<option value='" + res.data[i].id + "'>" + res.data[i].brandname + "</option>");
                }
                ;
                //使用refresh方法更新UI以匹配新状态。
                $('#brandSelect').selectpicker('refresh');
                //render方法强制重新渲染引导程序 - 选择ui。
                $('#brandSelect').selectpicker('render');
            }
        });
        $.ajax({
            url: ctx + "hobbySurveyPage/findType",
            data: '',
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                for (var i = 0; i < res.data.length; i++) {
                    $("#typeSelect").append("<option value='" + res.data[i].id + "'>" + res.data[i].typename + "</option>");
                }
                ;
                //使用refresh方法更新UI以匹配新状态。
                $('#typeSelect').selectpicker('refresh');
                //render方法强制重新渲染引导程序 - 选择ui。
                $('#typeSelect').selectpicker('render');
            }
        });
        $.ajax({
            url: ctx + "hobbySurveyPage/findTaste",
            data: '',
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                for (var i = 0; i < res.data.length; i++) {
                    $("#tasteSelect").append("<option value='" + res.data[i].id + "'>" + res.data[i].tastename + "</option>");
                }
                ;
                //使用refresh方法更新UI以匹配新状态。
                $('#tasteSelect').selectpicker('refresh');
                //render方法强制重新渲染引导程序 - 选择ui。
                $('#tasteSelect').selectpicker('render');
            }
        });
        $.ajax({
            url: ctx + "hobbySurveyPage/findTemp",
            data: '',
            type: "post",
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                for (var i = 0; i < res.data.length; i++) {
                    $("#tempSelect").append("<option value='" + res.data[i].id + "'>" + res.data[i].temperaturename + "</option>");
                }
                ;
                //使用refresh方法更新UI以匹配新状态。
                $('#tempSelect').selectpicker('refresh');
                //render方法强制重新渲染引导程序 - 选择ui。
                $('#tempSelect').selectpicker('render');
            }
        });
    };
    $("#brandSelect").change(function (e) {
        data.brandIds = [];
        obj.brandlist1 = [];
        $("#brandSelect option:selected").each(function(){
            data.brandIds.push($(this).val());
            obj.brandlist1.push($(this).val());

        });
        product()
    });
    $("#typeSelect").change(function (e) {
        data.typeIds = [];
        obj.typelist1 = [];
        $("#typeSelect option:selected").each(function(){
            data.typeIds.push($(this).val());
            obj.typelist1.push($(this).val());
        });
        product()
    });
    $("#tasteSelect").change(function (e) {
        obj.tastelist1 = [];
        $("#tasteSelect option:selected").each(function(){
            obj.tastelist1.push($(this).val());
        });
    });
    $("#tempSelect").change(function (e) {
        obj.templist1 = [];
        $("#tempSelect option:selected").each(function(){
            obj.templist1.push($(this).val());
        });
    });

    function product() {
        $("#productItem").empty();
        if(data.brandIds.length>0){
            if(data.typeIds.length === 0) data.typeIds = null;
            $.ajax({
                url: ctx + "hobbySurveyPage/findProduct",
                data: JSON.stringify(data),
                type: "post",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    res.data.forEach(function (item){
                        var inf = "<div  class=\"item\" id='item_"+item.id+"' data-id='"+item.id+"' onclick='checkProduct(" + item.id + ")'>" +
                            "<img src=" + item.pic + ">" +
                            "<p class=\"name\">" +
                            "<span>"+ item.productName+"</span>"+
                            "</p>"+
                            "<span class=\"price\">"+ item.price+"</span>"+
                            "</div>"
                        $("#productItem").append(inf);
                    })
                }
            });
            if(data.typeIds == null) data.typeIds = [];
        }
    }
    checkProduct = function (e) {
        if ($("#item_"+e).hasClass("items")) {
            $("#item_"+e).removeClass("items");
        } else {
            $("#item_"+e).addClass("items");
        }
    };

    $("#btn_submit").click(function () {
        var bv = $("#productForm").data('bootstrapValidator');
        bv.validate();
        if (bv.isValid()) {
            obj.remark = $("#remark").val();
            obj.productlist1 = [];
            var list = $("#productItem").find('div[class^="item items"]');
            if(list.length>0){
                for(var i=0;i<list.length;i++){
                    obj.productlist1.push(list[i].dataset.id);
                }
            }
            obj.openid = getUrlParam("openid");
            $.ajax({
                url: ctx + "hobbySurveyPage/save",
                data: JSON.stringify(obj),
                type: "post",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    if (res.code === "200") {

                    } else {
                    }
                }
            });
        }
    })

    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");

        var url =decodeURI(decodeURI(window.location.search)); //获取url中"?"符后的字串，使用了两次decodeRUI解码

        var r = url.substr(1).match(reg); //匹配目标参数
        if(r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    window.onload = function () {
        init();
    }
});
