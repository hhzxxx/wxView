$("#item").click(function () {
    // $(".item").addClass("items");
    if($(".item").hasClass("items")){
        $(".item").removeClass("items");
    }else{
        $(".item").addClass("items");
    }
});