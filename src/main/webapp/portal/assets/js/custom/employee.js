employee();
jobNumber = null;
$("#a-birth").datetimepicker({
    format: "yyyy-mm-dd",
    autoclose:true,
    startView:4,
    minView:2,
});
$("#u-birth").datetimepicker({
    format: "yyyy-mm-dd",
    autoclose:true,
    startView:4,
    minView:2,
});
$("#myFile").fileinput({
    language : 'zh',
    uploadUrl : "/hmi/api/upload/photoLibrary",
    autoReplace : false,
    minFileCount : 1,
    maxFileCount : 1,
    enctype:'multipart/form-data',
    allowedFileExtensions : [ "jpg", "png", "gif"],
    browseClass : "btn btn-primary", //按钮样式
    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
    uploadExtraData:function(){
        return {'job_number':111};
    }
}).on("fileuploaded", function(e, data) {
    alert(data['info']);
    //$("#logo").attr("value", res.success);
}).on("fileerror", function(e, data) {
    alert("failed");
})

//$("#uploadPhoto").fileinput({
//    language : 'zh',
//    uploadUrl : "/hmi/api/upload/headPhoto",
//    autoReplace : false,
//    minFileCount : 1,
//    maxFileCount : 5,
//    enctype:'multipart/form-data',
//    allowedFileExtensions : [ "jpg", "png", "gif"],
//    browseClass : "btn btn-primary", //按钮样式
//    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
//    uploadExtraData:function(){
//        return {'job_number':111};
//    }
//}).on("fileuploaded", function(e, data) {
//    alert(data['info']);
//    //$("#logo").attr("value", res.success);
//}).on("fileerror", function(e, data) {
//    alert("failed");
//})



function show_upload_photo(job_number){
	jobNumber = job_number;
	document.getElementById("album").innerHTML = '';
	showPhotos(job_number);
	$("#uploadPhoto").fileinput({
	    language : 'zh',
	    uploadUrl : "/hmi/api/upload/photoLibrary",
	    autoReplace : false,
	    minFileCount : 1,
	    maxFileCount : 5,
	    enctype:'multipart/form-data',
	    allowedFileExtensions : [ "jpg", "png", "gif"],
	    browseClass : "btn btn-primary", //按钮样式
	    previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
	    uploadExtraData:function(){
            return {'job_number':job_number};
        }
	}).on("fileuploaded", function(e, data) {
		showPhotos(job_number);
	    var res = data.response;
	    alert(res.success);
	    
	})
}

function showPhotos(job_number){
	var token = $.cookie("GLOBOT_TOKEN");
	console.info(token);
	if(typeof(token)=="undefined")
    window.location.href = "/hmi/portal/login.html";
	$.ajax({
		url : "/hmi/api/picture/facePictures/"+job_number,
		dataType : "json",
		type : "GET",
		async:true, 
		success : function(response) {
			var pictures = response.data;
			pictures = JSON.parse(pictures); 
			console.log(pictures);
			var nums=pictures.length;
			console.log("pictures:"+nums);
			var urlPrefix='';
			var html = '';
			for(var i=0;i<nums;i++){
				//再跟你想追加的代码加到一起插入div中
				html = html + '<div id="pic'+pictures[i]['id']+'" class="col-lg-3 col-md-4 col-sm-4 col-xs-12 desc" style="margin-top:4px;"><div class="project-wrapper"><div class="project"><div class="-wrapper"><div class=" fancybox-type-image"><img id="img1" ondblclick="deletePhoto('+pictures[i]['id']+')" class="img-responsive" src="'+pictures[i]['url']+'" alt=""></div><div class="overlay"></div></div></div></div></div>'
//				var a=document.getElementById('a'+(i+1));
//				a.setAttribute('href',urlPrefix+pictures[i].picUrl);
//				var img=document.getElementById('img'+(i+1));
//				img.setAttribute('src',urlPrefix+pictures[i].thumbnailUrl);
				
			}
			document.getElementById("album").innerHTML = html;

		},
		error : function() {
			alert("error");
		}
	});
}

function deletePhoto(id){
	if(confirm("确定要删除该照片吗？")){
		$.ajax({
			url : "/hmi/api/picture/deletefacePictures/"+id,
			dataType : "json",
			type : "GET",
			success : function(response) {
				if(response.data == "1"){
					$("#pic"+id).remove();
					alert("删除成功");
				}else{
					alert("删除成功");
				}
			},
			error : function() {
				alert("error");
			}
		});
	}
	
}

function beginTraining(){
	console.log(jobNumber);
	$.ajax({
		url : "/hmi/api/picture/train/"+jobNumber,
		dataType : "json",
		type : "GET",
		success : function(response) {
			alert(response.data);
		},
		error : function() {
			alert("error");
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
            $('#employeeInfoList').html($.i18n.prop('employ_info_list'));
            $('.addNewEmployee').html($.i18n.prop('add_employee'));
            $('.name').html($.i18n.prop('name'));
            $('.gender').html($.i18n.prop('gender'));
            $('.jobNumber').html($.i18n.prop('job_number'));
            $('.male').html($.i18n.prop('male'));
            $('.female').html($.i18n.prop('female'));
            $('.email').html($.i18n.prop('email'));
            $('.phone').html($.i18n.prop('phone'));
            $('.position').html($.i18n.prop('position'));
            $('.message').html($.i18n.prop('message'));
            $('.status').html($.i18n.prop('status'));
            $('.organ').html($.i18n.prop('organize'));
            $('.operation').html($.i18n.prop('operation'));
            $('.add_time').html($.i18n.prop('add_time'));
            $('.admin').html($.i18n.prop('admin'));
            $('.employee').html($.i18n.prop('employee'));
            $('.birth').html($.i18n.prop('birthday')+':');
            $('.organize').html($.i18n.prop('organize'));
            $('.remark').html($.i18n.prop('remark'));
            $('.homepage').html($.i18n.prop('homepage'));
            $('.modify_employee').html($.i18n.prop('modify_employee'));
            $('.delete_emloyee').html($.i18n.prop('delete_emloyee'));
            $('.comfirm_delete_employee').html($.i18n.prop('comfirm_delete_employee'));
            
            $('.file-drop-zone-title').html($.i18n.prop('select_tip'));
            $('.file-caption').html($.i18n.prop('select_file'));
            $('.file-drop-zone-title').html($.i18n.prop('select_tip'));
            $('.submit').html($.i18n.prop('submit'));
            $('.cancel').html($.i18n.prop('cancel'));
            $('.hidden-xs').html($.i18n.prop('choose'));
            $('#logout').html($.i18n.prop('logout'));
            $('#change_lang').html($.i18n.prop('change_lang'));
            $('#begin_training').html($.i18n.prop('begin_training'));
        }
    });
}