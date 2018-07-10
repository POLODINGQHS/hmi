var token = $.cookie("GLOBOT_TOKEN");
console.info(token);
if(typeof(token)=="undefined")
	window.location.href = "/hmi/portal/login.html";

function submitLeave(){
	console.log($("#leave_reason").val());
    var postData = {
        "leaveDate"	:	$("#leave_date").val(),
        "leaveTime"	:	$("#leave_time").val(),
        "type"		:	$("#leave_type").val(),
        "reason"	:	$("#leave_reason").val()
    };
    console.info(JSON.stringify(postData))
    $.ajax({
        url:"/hmi/api/signIn/record/applyleave/" +token,
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            console.log(response);
            if(response.code == 1) {
            	alert("申请成功，请等待审批。")
            	window.location.reload();
            }else {
                alert("申请失败");
            }
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
        	$('.day').html($.i18n.prop('day'));
            $('#due_to_month').html($.i18n.prop('due_to_month'));
            $('#punch_record').html($.i18n.prop('punch_record'));
            $('#current_available').html($.i18n.prop('current_available'));
            $('.leave').html($.i18n.prop('leave'));
            $('#completion').html($.i18n.prop('completion'));
            $('#late').html($.i18n.prop('late'));
            $('#leave_early').html($.i18n.prop('leave_early'));
            $('#leave_date_span').html($.i18n.prop('leave_date'));
            $('#leave_time_span').html($.i18n.prop('leave_time'));
            $('#leave_reason_span').html($.i18n.prop('leave_reason'));
            $('#leave_type_span').html($.i18n.prop('leave_type'));
            $('.submit').html($.i18n.prop('submit'));
            $('.cancel').html($.i18n.prop('cancel'));
            $('.homepage').html($.i18n.prop('homepage'));
            $('#logout').html($.i18n.prop('logout'));
            $('#change_lang').html($.i18n.prop('change_lang'));
        }
    });
}