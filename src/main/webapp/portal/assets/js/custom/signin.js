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
        	$('.name').html($.i18n.prop('name'));
            $('.job_number').html($.i18n.prop('job_number'));
            $('.organ').html($.i18n.prop('organize'));
            $('.date').html($.i18n.prop('date'));
            $('.entry_time').html($.i18n.prop('entry_time'));
            $('.leave_time').html($.i18n.prop('leave_time'));
            $('.state').html($.i18n.prop('state'));
            $('#employ_signin_list').html($.i18n.prop('employ_signin_list'));
            $('#signin_info').html($.i18n.prop('signin_info'));
            $('.homepage').html($.i18n.prop('homepage'));
            $('#logout').html($.i18n.prop('logout'));
            $('#change_lang').html($.i18n.prop('change_lang'));
        }
    });
}