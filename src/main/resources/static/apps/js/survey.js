$(function () {
    $(".selectpicker").selectpicker({
        noneSelectedText : '请选择'//默认显示内容
    });

    var typeList = [];
    var brandList = [];
    var data = {
        brandIds: [],
        typeIds: []
    };

    var init=function(){
        $.ajax({
            url: ctx + "hobbySurveyPage/findBand",
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
            url: ctx + "hobbySurveyPage/findType",
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
    $("#brandSelect").change(function (e) {
        data.brandIds = [];
        $("#brandSelect option:selected").each(function(){
            data.brandIds.push($(this).val());
        });
        product()
    });
    $("#typeSelect").change(function (e) {
        data.typeIds = [];
        $("#typeSelect option:selected").each(function(){
            data.typeIds.push($(this).val());
        });
        product()
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
                        var inf = "<div  class=\"item\" id='item_"+item.id+"' onclick='checkProduct(" + item.id + ")'>" +
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
