function doInit() {
    var token = $.cookie("GLOBOT_TOKEN");
    console.info(token);
    if(typeof(token)=="undefined")
        window.location.href = "/hmi/portal/login.html";
    $.ajax({
        url:"/hmi/api/auth/token/"+token,
        dataType:"json",
        type:"GET",
        success:function (response) {
            if(response.code == 0){
                var employee = response.data;
                console.info(employee);
                $("#login_name").text(employee.name);
            }else {
                window.location.href = "/hmi/portal/login.html";
            }
        }
    })
}
doInit();