var dynamicLoading = {
	css : function(path) {
		if (!path || path.length === 0) {
			throw new Error('argument "path" is required !');
		}
		var head = document.getElementsByTagName('head')[0];
		var link = document.createElement('link');
		link.href = path;
		link.rel = 'stylesheet';
		link.type = 'text/css';
		head.appendChild(link);
	},
	js : function(path) {
		if (!path || path.length === 0) {
			throw new Error('argument "path" is required !');
		}
		var head = document.getElementsByTagName('head')[0];
		var script = document.createElement('script');
		script.src = path;
		script.type = 'text/javascript';
		head.appendChild(script);
	}
}

$(function() {
	$
			.ajax({
				url : "/hmi/api/menu/struc",
				dataType : "json",
				type : "GET",
				success : function(response) {
					console.log("菜单列表请求成功");
					var pathName = window.location.pathname;
					var pageUrl = pathName.split('/').pop();
					var pageName = pageUrl.split('.')[0];
					var data = response.data;
					console.log(data);
					var obj = response;
					var parentMenus = obj.data;
					var str = '';
					if(getCookie('GLOBOT_LANG')=='zh-CN'){
						for (var i = 0; i < parentMenus.length; i++) {
							var active = false;
							var subStr = '';
							for (var j = 0; j < parentMenus[i].childMenu.length; j++) {
								var subActive = false;
								if (parentMenus[i].childMenu[j].url == pageUrl) {
									active = true;
									subActive = true;
								}
								subStr += ('<li '
										+ (subActive ? 'class="active"' : '')
										+ '><a href="'
										+ parentMenus[i].childMenu[j].url + '">'
										+ parentMenus[i].childMenu[j].name + '</a></li>');
							}
							str += ('<li class="sub-menu"><a '
									+ (active ? 'class="active" ' : '')
									+ 'href="javascript:;" ><i class="fa fa-book"></i><span>'
									+ parentMenus[i].name + '</span></a><ul class="sub">');
							str += subStr;
							str += '</ul></li>';
						}
					}else if(getCookie('GLOBOT_LANG')=='ja-jp'){
						for (var i = 0; i < parentMenus.length; i++) {
							var active = false;
							var subStr = '';
							for (var j = 0; j < parentMenus[i].childMenu.length; j++) {
								var subActive = false;
								if (parentMenus[i].childMenu[j].url == pageUrl) {
									active = true;
									subActive = true;
								}
								subStr += ('<li '
										+ (subActive ? 'class="active"' : '')
										+ '><a href="'
										+ parentMenus[i].childMenu[j].url + '">'
										+ parentMenus[i].childMenu[j].jp_name + '</a></li>');
							}
							str += ('<li class="sub-menu"><a '
									+ (active ? 'class="active" ' : '')
									+ 'href="javascript:;" ><i class="fa fa-book"></i><span>'
									+ parentMenus[i].jp_name + '</span></a><ul class="sub">');
							str += subStr;
							str += '</ul></li>';
						}
					}
					$(".sidebar-menu").append(str);
					// 动态加载 CSS 文件
					// dynamicLoading.css("test.css");

					// 动态加载 JS 文件
					// dynamicLoading.js("test.js");

					$('#nav-accordion').dcAccordion({
						eventType : 'click',
						autoClose : true,
						saveState : true,
						disableLink : true,
						speed : 'slow',
						showCount : false,
						autoExpand : true,
						// cookie: 'dcjq-accordion-1',
						classExpand : 'dcjq-current-parent'
					});

				},
				error : function() {
					alert("菜单列表请求失败");
				}
			});
});

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