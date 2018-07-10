$(function(){
    var token = $.cookie("GLOBOT_TOKEN");
    console.info(token);
    if(typeof(token)=="undefined")
        window.location.href = "/hmi/portal/login.html";
    $.ajax({
        url:"/hmi/api/auth/token/"+token,
        dataType:"json",
        type:"GET",
        success:function(response){
            console.log("个人信息请求成功");
            var employee = response.data;
            $(".name").append(employee.name);
            $(".jobNumber").append(employee.jobNumber);
            $(".position").append(employee.position);
            $(".email").append(employee.email);
            $(".mobile").append(employee.mobile);
            var birthday=new Date(employee.birthday);
            var birthStr=birthday.getFullYear()+'-'+(birthday.getMonth()+1)+'-'+birthday.getDate();
            $(".birthday").append(birthStr);

        },
        error:function(){
            alert("个人信息请求失败");
        }
    });
});
