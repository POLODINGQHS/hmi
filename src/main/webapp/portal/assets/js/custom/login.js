$.backstretch("assets/img/login-bg.jpg", {speed: 500});

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
            $('#loginButton').html($.i18n.prop('login_button'));
            $('#loginWelcome').html($.i18n.prop('login_welcome'));
            $('#changeLangA').html($.i18n.prop('change_lang'));
            $("#username").attr("placeholder",$.i18n.prop('job_number'));
            $("#password").attr("placeholder",$.i18n.prop('password'));
        }
    });
}

function submit(){
    var postData = {
        "username":$("#username").val(),
        "password":$("#password").val()
    };
    console.log(postData.username);
    console.info(JSON.stringify(postData))
    $.ajax({
        url:"/hmi/api/auth/login",
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                window.location.href = "/hmi/portal/index.html"
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}/**
 * Created by Ambitous on 2018/1/10.
 */
