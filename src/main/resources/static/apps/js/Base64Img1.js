//插入图片，转化成base64

var dropbox = document.getElementById("dropbox");
var inputarea = document.getElementById("inputarea");
var preview = document.getElementById("preview");
var resImg = document.getElementById("resImg");
var banner = document.getElementById("banner");
var previewUl = document.getElementById("previewUl");
$("#cleanImg").click(function() {
    $("#previewUl").empty();
    $("#resImg").empty();
    // $("#dropbox").show();
});

function getPic(pic){
    if(pic!=null && pic!=""){
        var picList = pic.split(",");
        picList.forEach(function (value) {
            var li = document.createElement("li");
            var img = document.createElement("img");
            var a = document.createElement("a");
            a.setAttribute("href","javascript:");
            a.innerHTML="删除";
            li.append(img);
            li.append(a);
            img.src = value;
            previewUl.appendChild(li);
        })
    }
}

function setPic(){
    var pics = previewUl.children;
    var str = '';
    if(pics.length>0){
        for(var i=0;i<pics.length;i++){
            str += pics[i].children[0].src;
            if(i<pics.length-1){
                str += ",";
            }
        }
    }
    return str;
}

// function selectfile(file) {//var afile = $('#picture_upload')[0].files[0];
//     var aaid  = document.getElementById("id").value;
//     var uuid  = document.getElementById("uuid").value;
//     var afile = document.getElementById('picture_upload').files[0];
//     console.log(afile+", id: "+aaid+", "+uuid); //$('#selectfilename'+aaid).innerHTML = afile.name;
//
//     //上传
//     if (afile!=null) {
//         var formData = new FormData();
//         formData.append('file', afile);
//         formData.append('id',   aaid);
//         formData.append('uuid', uuid);
//
//         console.log(JSON.stringify(formData));
//         $.ajax({
//             url: ctx + '/InstallCompany/upimg',
//             type: 'POST',
//             cache: false,
//             data: formData,

/**
 * 点击输入
 */
inputarea.addEventListener('change', function(event) {
    var x =previewUl.children;
    if(x.length==12){
        return;
    }
    var files = inputarea.files;
    handle("filelists", files);
    }, false);

/**
 * 拖拽实现
 */
// dropbox.addEventListener("dragenter", function(e){
//     e.stopPropagation();
//     e.preventDefault();
// }, false);
//
// dropbox.addEventListener("dragover", function(e){
//     e.stopPropagation();
//     e.preventDefault();
// }, false);
//
// dropbox.addEventListener("drop", function(e){
//     e.stopPropagation();
//     e.preventDefault();
//
//     var dt = e.dataTransfer;
//     var files = dt.files;
//
//     handle("filelists", files);
// }, false);
//
// /**
//  * 复制粘贴
//  */
// dropbox.addEventListener('paste', function(event){
//     // 添加到事件对象中的访问系统剪贴板的接口
//     var clipboardData = event.clipboardData, items, item, types;
//     if(clipboardData){
//         items = clipboardData.items;
//         if(!items){
//             return;
//         }
//         item = items[0];
//         // 保存在剪贴板中的数据类型
//         types = clipboardData.types || [];
//         for(var i=0; i < types.length; i++){
//             if( types[i] === 'Files'){
//                 item = items[i];
//                 break;
//             }
//         }
//         // 判断是否为图片数据
//         if(item && item.kind === 'file' && item.type.match(/^image\//i)){
//             // 读取该图片
//             var blob = item.getAsFile();
//             handle("blob", blob);
//         }
//     }
// });

$('#preview').delegate( 'a' , 'click' , function( ){
    var li = $(this).parent();
    li.remove();
});

function handle(type, data){
    // 处理结果
    var handleResult = function(file){
        var li = document.createElement("li");
        var img = document.createElement("img");
        var a = document.createElement("a");
        a.innerHTML="删除";
        li.append(img);
        li.append(a);
        img.file = file;

        var data=new FormData();
        data.append("upload",file);
        var postUrl =  '/oapi/pub/sys/upfile4ck';
        previewUl.appendChild(li);

        $.ajax({
            url:  postUrl,
            type: 'POST',
            cache: false,
            data: data,
            processData: false,
            contentType: false,
            complete: function() {
            }, success: function(result) {  //console.log(JSON.stringify(result));
                if (result.uploaded!=1) {
                } else {
                    $("#resImg").val(result.url);
                    img.src=result.url;
                    previewUl.appendChild(li);
                    console.log(result.url);
                }
            }, error: function(result, b) {
            }
        });

        inputarea.value = '';

/*        var ready=new FileReader();
        /!*开始读取指定的Blob对象或File对象中的内容. 当读取操作完成时,readyState属性的值会成为DONE,如果设置了onloadend事件处理程序,则调用之.同时,result属性中将包含一个data: URL格式的字符串以表示所读取文件的内容.*!/
        ready.readAsDataURL(file);
        ready.onload =function(){
            var re=this.result;
            var img = new Image();
            img.src = re;
            var that = img;
            // 默认按比例压缩
            var w = that.width,
                h = that.height,
                scale = w / h;
            w = 180;
            h = w / scale;
            var quality = 0.5;  // 默认图片质量为0.7
            //生成canvas
            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext('2d');
            // 创建属性节点
            var anw = document.createAttribute("width");
            anw.nodeValue = w;
            var anh = document.createAttribute("height");
            anh.nodeValue = h;
            canvas.setAttributeNode(anw);
            canvas.setAttributeNode(anh);
            ctx.drawImage(that, 0, 0, w, h);
            // 图像质量
            // quality值越小，所绘制出的图像越模糊
            var base64 = canvas.toDataURL('image/jpeg', quality );
        };

        var reader = new FileReader();
        reader.onload = (function(aImg) {
            return function(e) {
                aImg.src = e.target.result;
                //resImg.innerHTML = e.target.result;
                // $("#dropbox").hide();   //多张

            };
        })(img);

        reader.readAsDataURL(file);*/
    };

    if (type === "filelists") {
        var files = data;
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var imageType = /^image\//;
            if (!imageType.test(file.type)) {
                continue;
            }
            handleResult(file);
        }
    } else if (type === "blob") {
        handleResult(data);
    }
}