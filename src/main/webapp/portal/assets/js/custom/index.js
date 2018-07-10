function openSpeech(){
	alert("启动语音识别");
	$.ajax({
		type: "GET",
		url:"http://127.0.0.1:8082/speech",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
    	}
	});
}

function openFace(){
	alert("启动人脸识别");
	$.ajax({
		type: "GET",
		url:"http://127.0.0.1:8082/face/?control=open",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
    	}
	});
}

function closeFaceSpeech(){
	alert("关闭摄像头同时关闭人脸识别");
	$.ajax({
		type: "GET",
		url:"http://127.0.0.1:8082/face/?control=close",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
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
        }
    });
}

