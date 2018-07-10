checkCookie();

function setCookie(cname, cvalue) {
//    var d = new Date();
//    d.setTime(d.getTime() + (exdays*24*60*60*1000));
//    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; ";
}

//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}
//清除cookie  
function swicthCookie() {  
    setCookie("GLOBOT_LANG", getCookie("GLOBOT_LANG")== 'zh-CN' ? 'ja-jp' : 'zh-CN');  
    location.reload();
}  
function checkCookie() {
    var cookie_lang = getCookie("GLOBOT_LANG");
    if (cookie_lang != "") {
        return cookie_lang;
    } else {
    	currentLang = navigator.language;   //判断除IE外其他浏览器使用语言
    	if(!currentLang){//判断IE浏览器使用语言
    	    currentLang = navigator.browserLanguage;
    	}
    	setCookie('GLOBOT_LANG',currentLang);
    	return currentLang;
    }
}