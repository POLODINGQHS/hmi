/**
 * Created by Ambitous on 2017/11/29.
 */


function log() {
    $.ajax({
        url: "/hmi/api/server/log",
        dataType: "json",
        type: "GET",
        success: function (response) {
        	var str = '';
            for(var i=0;i<response.length;i++){
            	str += ('<tr class="table-row"><td style="width:20%">'+response[i]['updateTime']+'</td><td>'+response[i]['version']+'</td><td>'+response[i]['updateInfo']+'</td></tr>');
            }
            $("#table-body").append(str);
            initTable();

        },
        error: function () {
            alert("0");
        }
    });
}

function employee() {
    $.ajax({
        url: "/hmi/api/employee/list",
        dataType: "json",
        type: "GET",
        success: function (response) {
            console.log("请求成功");
            var data = response.data;
            var str = '';
            var id;
            var job_number;
            for (var i = 0; i < data.length; i++) {
                str += ('<tr class="table-row">')
                for (var item in data[i]) {
                    if(item=='id')  id = data[i][item];
                    if(item=='jobNumber')  job_number = data[i][item];
                    if (item == 'id' || item == 'modifyTime' || item == 'password' || item== 'birthday')    continue;
                    str += ('<td>' + data[i][item] + '</td>');
                }
                str += '<td><a style="height: 18px;border: 0;" data-toggle="modal" data-target="#updateModal" onclick="show_update('+id+')" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></a>'+'' +
                           '<a style="height: 18px;border: 0;" data-toggle="modal" data-target="#deleteModal" onclick="show_delete('+id+')" class="btn btn-danger btn-xs" ><i class="fa fa-trash-o "></i></a>'+' '+
                           '<a style="height: 18px;border: 0;" data-toggle="modal" data-target="#photoModal" onclick="show_upload_photo('+job_number+')" class="btn btn-success btn-xs" ><i class="fa fa-user "></i></a></td>'
                str += '</tr>';
            }
            $("#table-body").append(str);
            initTable();

        },
        error: function () {
            alert("0");
        }
    });
}
function device(){
        $.ajax({
            url:"/hmi/api/device/list",
            dataType:"json",
            type:"GET",
            success:function(response){
                console.log("请求成功");
                var data = response.data;
                var str='';
                var id;
                for(var i=0;i<data.length;i++){
                    str+=('<tr class="table-row">')
                    for(var item in data[i]){
                        if(item=='id')  id = data[i][item];
                        if(item == 'id' || item=='modifyTime')	continue;
                        str+=('<td>'+data[i][item]+'</td>');
                    }
                    str += '<td><a style="height: 18px;border: 0;" data-toggle="modal" data-target="#updateModal" onclick="device_show_update('+id+')" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></a>'+'' +
                        '<a style="height: 18px;border: 0;" data-toggle="modal" data-target="#deleteModal" onclick="device_show_delete('+id+')" class="btn btn-danger btn-xs" ><i class="fa fa-trash-o "></i></a></td>'
                    str+='</tr>';
                }

                $("#table-body").append(str);
                initTable();
            },
            error:function(){
                alert("0");
            }
        });

    }
function event(){
    $.ajax({
        url:"/hmi/api/event/list",
        dataType:"json",
        type:"GET",
        success:function(response){
            console.log("请求成功");
            var data = response.data;
            var str='';
            var id;
            for(var i=0;i<data.length;i++){
                str+=('<tr class="table-row">')
                for(var item in data[i]){
                    if(item=='id')  id = data[i][item];
                    if(item=='id'||item=='modifyTime')	continue;
                    str+=('<td>'+data[i][item]+'</td>');
                }
                str += '<td><a style="height: 18px;border: 0;" data-toggle="modal" data-target="#updateModal" onclick="event_show_update('+id+')" class="btn btn-primary btn-xs"><i class="fa fa-pencil"></i></a>'+'' +
                    '<a style="height: 18px;border: 0;" data-toggle="modal" data-target="#deleteModal" onclick="event_show_delete('+id+')" class="btn btn-danger btn-xs" ><i class="fa fa-trash-o "></i></a></td>'
                str+='</tr>';
            }
            $("#table-body").append(str);
            initTable();
        },
        error:function(){
            alert("0");
        }
    });
    $.ajax({
        url:"/hmi/api/device/mapper",
        dataType:"json",
        type:"GET",
        success:function(response){
            console.log("请求成功");
            var data = response.data;
            var str='';
            for(var i=0;i<data.length;i++){
                str+=('<option value="'+data[i].id+'">'+data[i].name+'</option>')
            }
            $("#a_device").append(str);
            $("#u_device").append(str);
            // $('#a_device').searchableSelect();
            // $('#u_device').searchableSelect();
        },
        error:function(){
            alert("0");
        }
    });
    $.ajax({
        url:"/hmi/api/event/mapper",
        dataType:"json",
        type:"GET",
        success:function(response){
            console.log("请求成功");
            var data = response.data;
            var str='';
            for(var i=0;i<data.length;i++){
                str+=('<option value="'+data[i].id+'">'+data[i].name+'</option>')
            }
            $("#a_event").append(str);
            $("#u_event").append(str);
            // $('#a_event').searchableSelect();
            // $('#u_event').searchableSelect();
        },
        error:function(){
            alert("0");
        }
    });


}
function sign_in() {
    $.ajax({
        url: "/hmi/api/signIn/record/all",
        dataType: "json",
        type: "GET",
        success: function (response) {
            console.log("请求成功");
            var data = response.data;
            var str = '';
            for (var i = 0; i < data.length; i++) {
                str += ('<tr class="table-row">')
                for (var item in data[i]) {
                    str += ('<td>' + data[i][item] + '</td>');
                }
                str += '</tr>';
            }
            $("#table-body").append(str);
            initTable();

        },
        error: function () {
            alert("0");
        }
    });
}





//=================    员工模块     =========================
function add() {
    console.info($("input[name='gender']:checked").val())
    var postData = {
        "name":$("#a-name").val(),
        "gender":$("input[name='gender']:checked").val(),
        "jobNumber":$("#a-jobnumber").val(),
        "email":$("#a-email").val(),
        "phone":$("#a-phone").val(),
        "position":$("input[name='position']:checked").val(),
        "birthday":$("#a-birth").val(),
        "org":$("#a-org").val(),
        "mes":$("#a-mes").val()
    };
    console.info(JSON.stringify(postData))
    $.ajax({
        url:"/hmi/api/employee/add",
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                window.location.href = "/hmi/portal/employee.html"
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function show_update(id) {
    console.info("点击："+id)
    $.ajax({
        url:"/hmi/api/employee/query/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            var employee = response.data
            if(response.code == 0) {
                $("#u-name").attr('placeholder',employee.name)
                console.info(employee.gender)
                if(employee.gender==false){
                    $('input[name="gender"]:radio:last').attr('checked', 'checked');
                }else {
                    $('input[name="gender"]:radio:first').attr('checked', 'checked');
                }
                $("#u-jobnumber").attr('placeholder',employee.jobNumber)
                $("#u-email").attr('placeholder',employee.email)
                $("#u-phone").attr('placeholder',employee.mobile)
                if(employee.position==1){
                    $('input[name="position"]:radio:last').attr('checked', 'checked');
                }else {
                    $('input[name="position"]:radio:first').attr('checked', 'checked');
                }
                $("#u-birth").attr('placeholder',employee.birthday)
                $("#u-org").attr('placeholder',employee.org)
                $("#u-mes").attr('placeholder',employee.memo)
                $("#hidden_id").attr('placeholder',employee.id)
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function update() {
    var id = $("#hidden_id").attr("placeholder")
    console.info(id)

    var name = $("#u-name").val()
    var jobNumber = $("#u-jobnumber").val()
    var email = $("#u-email").val()
    var phone = $("#u-phone").val()
    var birthday = $("#u-birth").val()
    var org = $("#u-org").val()
    var mes = $("#u-mes").val()

    if(name==null||name==undefined||name=="")   name = $("#u-name").attr("placeholder")
    // if(gender==false){
    //     $('input[name="gender"]:radio:last').attr('checked', 'checked');
    // }else {
    //     $('input[name="gender"]:radio:first').attr('checked', 'checked');
    // }
    if(jobNumber==null||jobNumber==undefined||jobNumber=="")   jobNumber = $("#u-jobnumber").attr("placeholder")
    if(email==null||email==undefined||email=="")   email = $("#u-email").attr("placeholder")
    if(phone==null||phone==undefined||phone=="")   phone = $("#u-phone").attr("placeholder")
    // if(position==1){
    //     $('input[name="position"]:radio:last').attr('checked', 'checked');
    // }else {
    //     $('input[name="position"]:radio:first').attr('checked', 'checked');
    // }
    if(birthday==null||birthday==undefined||birthday=="")   birthday = $("#u-birth").attr("placeholder")
    if(org==null||org==undefined||org=="")   org = $("#u-org").attr("placeholder")
    if(mes==null||mes==undefined||mes=="")   mes = $("#u-mes").attr("placeholder")


    var postData = {
        "id":id,
        "name":name,
        "gender":$("input[name='gender']:checked").val(),
        "jobNumber":jobNumber,
        "email":email,
        "phone":phone,
        "position":$("input[name='position']:checked").val(),
        "birthday":birthday,
        "org":org,
        "mes":mes
    };
    console.info(JSON.stringify(postData))
    $.ajax({
        url:"/hmi/api/employee/update",
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                console.info("更改成功！")
                window.location.href = "/hmi/portal/employee.html"
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function show_delete(id) {
    console.info("点击："+id)
    $.ajax({
        url:"/hmi/api/employee/query/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            var employee = response.data
            if(response.code == 0) {
                $("#hidden_id").attr('placeholder',employee.id)
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function delete_employee() {

    var id = $("#hidden_id").attr("placeholder")
    console.info(id)

    $.ajax({
        url:"/hmi/api/employee/delete/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                console.info("删除成功！")
                window.location.href = "/hmi/portal/employee.html"
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}










//======================  设备模块  =======================
function device_add() {
    console.info($("input[name='type']:checked").val())
    var postData = {
        "name":$("#d_name").val(),
        "model":$("#d_model").val(),
        "deviceType":$("input[name='type']:checked").val(),
        "memo":$("#d_mes").val()
    };
    console.info(JSON.stringify(postData))
    $.ajax({
        url:"/hmi/api/device/add",
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                window.location.href = "/hmi/portal/device.html"
            }else {
                alert("添加失败，原因是：" + response.msg);
            }
        }
    });
}
function device_show_update(id) {
    console.info("点击："+id)
    $.ajax({
        url:"/hmi/api/device/query/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            var device = response.data
            if(response.code == 0) {
                $("#u_name").attr('placeholder',device.name)
                $("#u_model").attr('placeholder',device.model)
                if(device.deviceType==1){
                    $('input[name="type"]:radio:last').attr('checked', 'checked');
                }else {
                    $('input[name="type"]:radio:first').attr('checked', 'checked');
                }
                $("#u_mes").attr('placeholder',device.memo)
                $("#hidden_id").attr('placeholder',device.id)
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function device_update() {
    var id = $("#hidden_id").attr("placeholder")
    console.info(id)

    var name = $("#u_name").val()
    var model = $("#u_model").val()
    // var deviceType = $("#u_type").val()
    var mes = $("#u_mes").val()

    if(name==null||name==undefined||name=="")   name = $("#u_name").attr("placeholder")
    if(model==null||model==undefined||model=="")   model = $("#u_model").attr("placeholder")
    // if(deviceType==1){
    //     $('input[name="type"]:radio:last').attr('checked', 'checked');
    // }else {
    //     $('input[name="type"]:radio:first').attr('checked', 'checked');
    // }
    if(mes==null||mes==undefined||mes=="")   mes = $("#u_mes").attr("placeholder")


    var postData = {
        "id":id,
        "name":name,
        "model":model,
        "deviceType":$("input[name='type']:checked").val(),
        "memo":mes
    };
    console.info(JSON.stringify(postData))
    $.ajax({
        url:"/hmi/api/device/update",
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                console.info("更改成功！")
                window.location.href = "/hmi/portal/device.html"
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function device_show_delete(id) {
    console.info("点击："+id)
    $.ajax({
        url:"/hmi/api/device/query/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            var employee = response.data
            if(response.code == 0) {
                $("#hidden_id").attr('placeholder',employee.id)
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function delete_device() {

    var id = $("#hidden_id").attr("placeholder")
    console.info(id)

    $.ajax({
        url:"/hmi/api/device/delete/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                console.info("删除成功！")
                window.location.href = "/hmi/portal/device.html"
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}









//======================  事件模块  =========================
function event_add() {
    console.info($("input[name='type']:checked").val())
    var postData = {
        "name":$("#name").val(),
        "eventType":$("input[name='type']:checked").val(),
        "deviceId":$("#a_device").val(),
        "relationId":$("#a_event").val(),
        "memo":$("#mes").val()
    };
    console.info(JSON.stringify(postData))
    $.ajax({
        url:"/hmi/api/event/add",
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                window.location.href = "/hmi/portal/event.html"
            }else {
                alert("添加失败，原因是：" + response.msg);
            }
        }
    });
}
function event_show_update(id) {
    console.info("点击："+id)
    $.ajax({
        url:"/hmi/api/event/query/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            var event = response.data
            if(response.code == 0) {
                console.info(event)
                $("#u_name").attr('placeholder',event.name)
                var eventType = event.eventType;
                // $('input[name="u_type"]:radio:last').attr('checked', 'checked');
                $("input[name='type'][value='"+eventType+"']").attr("checked",true);
                $('#u_device').val(event.deviceId)
                $('#u_event').val(event.relationId)
                $("#u_mes").attr('placeholder',event.memo)
                $("#hidden_id").attr('placeholder',event.id)
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function event_update() {
    var id = $("#hidden_id").attr("placeholder")
    console.info(id)

    var postData = {
        "id":id,
        "name":$("#u_name").val(),
        "eventType":$("input[name='type']:checked").val(),
        "deviceId":$("#u_device").val(),
        "relationId":$("#u_event").val(),
        "memo":$("#u_mes").val()
    };
    console.info(JSON.stringify(postData))
    $.ajax({
        url:"/hmi/api/event/update",
        contentType:'application/json;charset=UTF-8',
        traditional:true,//这使json格式的字符不会被转码
        dataType:"json",
        data:JSON.stringify(postData),
        type:"POST",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                console.info("更改成功！")
                window.location.href = "/hmi/portal/event.html"
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function event_show_delete(id) {
    console.info("点击："+id)
    $.ajax({
        url:"/hmi/api/event/query/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            var employee = response.data
            if(response.code == 0) {
                $("#hidden_id").attr('placeholder',employee.id)
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}
function delete_event() {

    var id = $("#hidden_id").attr("placeholder")
    console.info(id)

    $.ajax({
        url:"/hmi/api/event/delete/"+id,
        dataType:"json",
        type:"GET",
        success:function(response){
            console.log(response);
            if(response.code == 0) {
                console.info("删除成功！")
                window.location.href = "/hmi/portal/event.html"
            }else {
                alert("登录失败，原因是：" + response.msg);
            }
        }
    });
}


















//=========================   初始化函数   =====================
    function initTable() {
        $('.dataTables-example').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                {extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'ExampleFile'},
                {extend: 'pdf', title: 'ExampleFile'},

                {
                    extend: 'print',
                    customize: function (win) {
                        $(win.document.body).addClass('white-bg');
                        $(win.document.body).css('font-size', '10px');

                        $(win.document.body).find('table')
                            .addClass('compact')
                            .css('font-size', 'inherit');
                    }
                }
            ]

        });
        /* Init DataTables */
        var oTable = $('#editable').DataTable();

        /* Apply the jEditable handlers to the table */
        oTable.$('td').editable('../example_ajax.php', {
            "callback": function (sValue, y) {
                var aPos = oTable.fnGetPosition(this);
                oTable.fnUpdate(sValue, aPos[0], aPos[1]);
            },
            "submitdata": function (value, settings) {
                return {
                    "row_id": this.parentNode.getAttribute('id'),
                    "column": oTable.fnGetPosition(this)[2]
                };
            },

            "width": "90%",
            "height": "100%"
        });
    }



