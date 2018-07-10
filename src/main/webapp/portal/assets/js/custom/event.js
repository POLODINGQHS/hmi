/**
 * Created by Ambitous on 2018/1/10.
 */
event();

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
            $('#event_list').html($.i18n.prop('event_list'));
            $('.add_new_event').html($.i18n.prop('add_new_event'));
            $('.name').html($.i18n.prop('name'));
            $('.add_time').html($.i18n.prop('add_time'));
            $('.message').html($.i18n.prop('message'));
            $('.operation').html($.i18n.prop('operation'));
            $('.event_type').html($.i18n.prop('event_type'));
            $('.event_name').html($.i18n.prop('event_name'));
            $('.event_image').html($.i18n.prop('event_image'));
            $('.event_dialog').html($.i18n.prop('event_dialog'));
            $('.event_action').html($.i18n.prop('event_action'));
            $('.asso_device').html($.i18n.prop('asso_device'));
            $('.asso_event').html($.i18n.prop('asso_event'));
            $('.event_remark').html($.i18n.prop('event_remark'));
            $('#modify_event').html($.i18n.prop('modify_event'));
            $('#delete_event').html($.i18n.prop('delete_event'));
            $('#confirm_delete_event').html($.i18n.prop('confirm_delete_event'));
            $('.submit').html($.i18n.prop('submit'));
            $('.cancel').html($.i18n.prop('cancel'));
            $('.homepage').html($.i18n.prop('homepage'));
            $('#logout').html($.i18n.prop('logout'));
            $('#change_lang').html($.i18n.prop('change_lang'));
        }
    });
}