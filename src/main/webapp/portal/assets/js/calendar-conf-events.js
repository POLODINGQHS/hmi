var Script = function () {
	
	var token = $.cookie("GLOBOT_TOKEN");
	console.info(token);
	if(typeof(token)=="undefined")
    window.location.href = "/hmi/portal/login.html";

    /* initialize the external events
     -----------------------------------------------------------------*/

    $('#external-events div.external-event').each(function() {
        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
            title: $.trim($(this).text()) // use the element's text as the event title
        };
        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);
        // make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 999,
            revert: true,      // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });
    });
    
    var eventsTemp=[];
    var records;
    $.ajax({
        url:"/hmi/api/signIn/record/"+token,
        dataType:"json",
        type:"GET",
        success:function(response){
            records = response.data.records;
            leaves = response.data.leaves;
            var num=0;
            for(var i=0;i<records.length;i++){
            	var date=new Date(records[i].signInTime);
            	var event={};
            	event.type=1;
            	event.id=i;
            	event.title='打卡:'+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
            	event.start=new Date(date.getFullYear(), date.getMonth(), date.getDate());
            	event.end=event.start;
            	event.direction='right';
            	if(date.getDay()==6||date.getDay()==7){
            		event.backgroundColor='#FFFF00';
            	}
            	eventsTemp.push(event);
            	num=i;
            }
            for(var i=0;i<leaves.length;i++){
            	var date=new Date(leaves[i].leaveDate);
            	var event={};
            	event.type=2;
            	event.id=i+num+1;
            	if(leaves[i].type == 0){
            		event.title='请假(类型:事假)';
            	}else if(leaves[i].type == 1){
            		event.title='请假(类型:病假)';
            	}
            	
            	event.start=new Date(date.getFullYear(), date.getMonth(), date.getDate());
            	event.end=event.start;
            	event.direction='right';
            	event.statu=leaves[i].statu;
            	event.reason=leaves[i].reason;
            	if(leaves[i].statu == 0){
            		event.statu='待审批';
            	}else if(leaves[i].statu == 1){
            		event.statu='已通过';
            	}else if(leaves[i].statu == 2){
            		event.statu='未通过';
            	}
            	if(leaves[i].leaveTime == 0){
            		event.time='上午';
            	}else if(leaves[i].leaveTime == 1){
            		event.time='下午';
            	}
            	event.backgroundColor='yellow';
            	if(date.getDay()==6||date.getDay()==7){
            		event.backgroundColor='FFFF00';
            	}
            	eventsTemp.push(event);
            }
            /* initialize the calendar
            -----------------------------------------------------------------*/
            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,basicWeek,basicDay'
                },
                editable: false,
                droppable: false, // this allows things to be dropped onto the calendar !!!
                defaultView: 'month',
                drop: function(date, allDay) { // this function is called when something is dropped

                    // retrieve the dropped element's stored Event Object
                    var originalEventObject = $(this).data('eventObject');

                    // we need to copy it, so that multiple events don't have a reference to the same object
                    var copiedEventObject = $.extend({}, originalEventObject);

                    // assign it the date that was reported
                    copiedEventObject.start = date;
                    copiedEventObject.allDay = allDay;

                    // render the event on the calendar
                    // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
                    $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

                    // is the "remove after drop" checkbox checked?
                    if ($('#drop-remove').is(':checked')) {
                        // if so, remove the element from the "Draggable Events" list
                        $(this).remove();
                    }

                },
                events: eventsTemp,
                eventClick : function( event ){	
                },
                eventMouseover : function( event ) {
                	if(event.type==1){
                		console.log(event.direction);
                		$(this).popover({
                			trigger:'hover',
                			container: 'body',
                			placement : 'right', 
                			title : '<div style="text-align:center; color:black; font-size:14px;">签到图片</div>',
                			html: 'true', 
                			content : '<div id="popOverBox"><img src="'+records[event.id].signInPic+'" width="256" height="144" /></div>',  
                			animation: false
                		})
                		$(this).popover("show");
                	}
                	if(event.type==2){
                		$(this).popover({
                			trigger:'hover',
                			container: 'body',
                			placement : 'right', 
                			title : '<div style="text-align:center; color:black; font-size:14px;">请假详情</div>',
                			html: 'true', 
                			content : '<div id="popOverBox">请假时间:'+event.time+'<br/>请假原因:'+event.reason+'<br/>申请状态:'+event.statu+'</div>',  
                			animation: false
                		})
                		$(this).popover("show");
                	}
                },
                
                eventMouseout : function( event ) {
                    $(this).popover("hide");
                },
                
                
            }); 
            
            setSignInInfo(response.data.signInInfo);
            
            $('#calendar').find('.fc-button-prev,.fc-button-next,.fc-button-today').click(function(){
            	var viewName = $('#calendar').fullCalendar('getView').name;
            	if(viewName=="month"){
            		var date = Date.parse($('#calendar').fullCalendar('getDate'));
            		$.ajax({
            	        url:"/hmi/api/signIn/info/"+token,
            	        dataType:"json",
            	        data:{"date":date},
            	        type:"GET",
            	        success:function(response){
            	            var signInInfo = response.data;
            	            setSignInInfo(signInInfo)
            	        },
            	        error:function(){
            	            alert("打卡信息请求失败");
            	        }
            	    });
            	}
            })
            
        },
        
        error:function(){
        	alert("打卡记录请求失败请求失败");
        }
        });
    
}();

function setSignInInfo(signInInfo){
	$("#totalDays").text(signInInfo.totalDays);
    $("#realDays").text(signInInfo.realDays);
    $("#leaveDays").text(signInInfo.leaveDays);
    $("#absentDays").text(signInInfo.absentDays);
    $("#lateDays").text(signInInfo.lateDays);
    $("#leaveEarlyDays").text(signInInfo.leaveEarlyDays);
}
