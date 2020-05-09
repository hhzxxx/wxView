$("#item").click(function () {
    // $(".item").addClass("items");
    if($(".item").hasClass("items")){
        $(".item").removeClass("items");
    }else{
        $(".item").addClass("items");
    }
});
window.onload = function () {
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