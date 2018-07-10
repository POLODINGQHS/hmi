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
            $('.add_time').html($.i18n.prop('add_time'));
            $('.message').html($.i18n.prop('message'));
            $('.operation').html($.i18n.prop('operation'));
            $('.model').html($.i18n.prop('model'));
            $('.type').html($.i18n.prop('type'));
            $('#device_list').html($.i18n.prop('device_list'));
            $('.add_new_device').html($.i18n.prop('add_new_device'));
            $('.submit').html($.i18n.prop('submit'));
            $('.cancel').html($.i18n.prop('cancel'));
            $('.remark').html($.i18n.prop('remark'));
            $('.input').html($.i18n.prop('input'));
            $('.output').html($.i18n.prop('output'));
            $('#modify_device').html($.i18n.prop('modify_device'));
            $('#delete_device').html($.i18n.prop('delete_device'));
            $('#confirm_delete_device').html($.i18n.prop('confirm_delete_device'));
            $('.homepage').html($.i18n.prop('homepage'));
            $('#logout').html($.i18n.prop('logout'));
            $('#change_lang').html($.i18n.prop('change_lang'));
        }
    });
}

device();

