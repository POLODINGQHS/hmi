log();
$("#a-time").datetimepicker({
    format: "yyyy-mm-dd",
    autoclose:true,
    startView:4,
    minView:2,
});
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
        	$('.update_time').html($.i18n.prop('update_time'));
            $('.version_num').html($.i18n.prop('version_number'));
            $('.update_info').html($.i18n.prop('update_info'));
            $('#update_log').html($.i18n.prop('version_log'));
            $('#logout').html($.i18n.prop('logout'));
            $('#change_lang').html($.i18n.prop('change_lang'));
            $('.homepage').html($.i18n.prop('homepage'));
        }
    });
}

function addLog(){
    var postData = {
        "updateTime":$("#a-time").val(),
        "version":$("#a-version").val(),
        "updateInfo":$("#a-info").val()
    };
    $.ajax({
        url:"/hmi/api/server/addlog",
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            if(response == 1){
            	alert("更新成功");
            	window.location.reload();
            }else{
            	alert("更新失败");
            	window.location.reload();
            }
        }
    });
}
