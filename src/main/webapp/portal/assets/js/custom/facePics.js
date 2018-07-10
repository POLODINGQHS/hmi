var token = $.cookie("GLOBOT_TOKEN");
$(function() {
	var token = $.cookie("GLOBOT_TOKEN");
	console.info(token);
	if(typeof(token)=="undefined")
    window.location.href = "/hmi/portal/login.html";
	$.ajax({
		url : "/hmi/api/picture/facePictures/"+token,
		dataType : "json",
		type : "GET",
		success : function(response) {
			var pictures = response.data;
			console.log(pictures);
			var nums=pictures.length;
			console.log("pictures:"+nums);
			var urlPrefix='';
			var html = '';
			for(var i=0;i<nums;i++){
				//再跟你想追加的代码加到一起插入div中
				html = html + '<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12 desc"><div class="project-wrapper"><div class="project"><div class="photo-wrapper"><div class="photo"><a id="a1" class="fancybox" href="'+pictures[i]+'"><img id="img1" class="img-responsive" src="'+pictures[i]+'" alt=""></a></div><div class="overlay"></div></div></div></div></div>'
//				var a=document.getElementById('a'+(i+1));
//				a.setAttribute('href',urlPrefix+pictures[i].picUrl);
//				var img=document.getElementById('img'+(i+1));
//				img.setAttribute('src',urlPrefix+pictures[i].thumbnailUrl);
				
			}
			document.getElementById("album").innerHTML = html;

		},
		error : function() {
			alert("error");
		}
	});
});


$("#myFile").fileinput({
    language : 'zh',
    uploadUrl : "/hmi/api/upload/photoLibrary",
    autoReplace : false,
    minFileCount : 1,
    maxFileCount : 5,
    enctype:'multipart/form-data',
    allowedFileExtensions : [ "jpg", "png", "gif" ],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    
}).on("fileuploaded", function(e, data) {
    var res = data.response;
    alert(res.info);
    $("#logo").attr("value", res.success);
    location.reload();
})

function openCamera(){
	$.ajax({
		url : "/hmi/api/picture/openCamera/"+token,
		dataType : "json",
		type : "GET",
		success : function(response) {
			alert(response.data);
		},
		error : function() {
			alert("error");
		}
	});
}

function beginTraining(){
	$.ajax({
		url : "/hmi/api/picture/train/"+token,
		dataType : "json",
		type : "GET",
		success : function(response) {
			alert(response.data);
		},
		error : function() {
			alert("error");
		}
	});
}

$(document).ready(function () {
    //LANGUAGE_CODE = jQuery.i18n.normaliseLanguageCode({}); //获取浏览器的语言    
	
    loadProperties(getCookie("GLOBOT_LANG"));
//	loadProperties("ja-jp");
    
})

function loadProperties(type) {
    jQuery.i18n.properties({
        name: 'strings', // 资源文件名称
        path: '/hmi/portal/i18n/', // 资源文件所在目录路径
        mode: 'map', // 模式：变量或 Map 
        language: type, // 对应的语言
        cache: false,
        encoding: 'UTF-8',
        callback: function () { // 回调方法
            $('.homepage').html($.i18n.prop('homepage'));
            $('#logout').html($.i18n.prop('logout'));
            $('#change_lang').html($.i18n.prop('change_lang'));
            $('#face_data').html($.i18n.prop('face_data'));
        }
    });
}
