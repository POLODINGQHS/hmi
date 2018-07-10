

function toChinese(){
	alert("确认后将切换为中文语音识别");
	$.ajax({
		type: "GET",
		url:"/hmi/api/server/tochinese",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
    	}
	});
}

function toJapanese(){
	alert("确认后将切换为日文语音识别");
	$.ajax({
		type: "GET",
		url:"/hmi/api/server/tojapanese",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
    	}
	});
}

function switchLanguage(){
	$.ajax({
		type: "GET",
		url:"/hmi/api/server/switchlanguage",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
			if(data ==1){
				alert('修改成功');
				window.location.reload();
			}
    	}
	});
}

function openFace(){
	alert("确认后将启动人脸识别");
	$.ajax({
		type: "GET",
		url:"/hmi/api/server/openface",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
    	}
	});
}

function openSpeech(){
	alert("确认后将启动语音识别");
	$.ajax({
		type: "GET",
		url:"/hmi/api/server/openspeech",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
    	}
	});
}

function closeFaceSpeech(){
	alert("确认后将关闭摄像头同时关闭人脸识别");
	$.ajax({
		type: "GET",
		url:"/hmi/api/server/closefacespeech",
		async:true,
		timeout: 1000,
		error: function(XMLHttpRequest, textStatus, errorThrown){
		},
		success : function(data) {
    	}
	});
}

function restartDjango(){
	alert("确认后将重启后台服务");
	$.ajax({
		type: "GET",
		url:"/hmi/api/server/restartdjango",
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
	server();
    
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
        	$('#server_manage').html($.i18n.prop('server_manage'));
            $('.add_time').html($.i18n.prop('add_time'));
            $('.message').html($.i18n.prop('message'));
            $('.modules').html($.i18n.prop('modules'));
            $('.status').html($.i18n.prop('status'));
            $('.error').html($.i18n.prop('error'));
            $('.chinese').html($.i18n.prop('chinese'));
            $('.japanese').html($.i18n.prop('japanese'));
            $('#face_recognize').html($.i18n.prop('face_recognize'));
            $('#voice_environment').html($.i18n.prop('voice_environment'));
            $('#back_control').html($.i18n.prop('back_control'));
            $('#lang_setting').html($.i18n.prop('lang_setting'));
            $('#open_speech').html($.i18n.prop('open_speech'));
            $('#open_face').html($.i18n.prop('open_face'));
            $('#close_face_speech').html($.i18n.prop('close_face_speech'));
            $('#lang_setting').html($.i18n.prop('lang_setting'));
            $('#switch_language').html($.i18n.prop('switch_language'));
            $('.homepage').html($.i18n.prop('homepage'));
            $('#logout').html($.i18n.prop('logout'));
            $('#change_lang').html($.i18n.prop('change_lang'));
            $('#restart_django').html($.i18n.prop('restart_django'));
        }
    });
}

function server() {
    $.ajax({
        url: "/hmi/api/server/var",
        dataType: "json",
        type: "GET",
        success: function (response) {
            console.log(response);
            var id;
            if(response['FaceRecognition'] != "none"){
            	var str='<a class="btn btn-success  btn-sm" style="height:20px;padding:0" href="javascript:;">√</a>'
            	$('#faceRecToken').html(str);
            }else{
            	var str='<a class="btn btn-danger  btn-sm" style="height:20px;padding:0;width:30px" href="javascript:;">X</a>'
            	$('#faceRecToken').html(str);
            }
            
            if(response['language'] == 0){
            	$('#lang_enviorment').attr('class','chinese');
            }else if(response['language'] == 1){
            	$('#lang_enviorment').attr('class','japanese');
            }else{
            	$('#lang_enviorment').attr('class','error');
            }
            loadProperties(getCookie("GLOBOT_LANG"));
//            str += ('<tr class="table-row"><td>FaceRecognition</td><td>'+response['FaceRecognition']+'</td></tr>');
//            str += ('<tr class="table-row"><td>Chinese</td><td>'+response['Chinese']+'</td></tr>');
//            str += ('<tr class="table-row"><td>Japanese</td><td>'+response['Japanese']+'</td></tr>');
//            $("#table-body").append(str);
//            initTable();

        },
        error: function () {
            alert("0");
        }
    });
}
