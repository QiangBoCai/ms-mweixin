<!DOCTYPE html>
<html lang="zh">
<head>
	<link rel="stylesheet" href="bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap-table.css">
    <#include "${managerViewPath}/include/macro.ftl"/>
    <#include "${managerViewPath}/include/meta.ftl"/> 
    <style>
    	body .modal-dialog{
    		width:450px;
    	}
    	body .form-horizontal .form-group{margin:0}
    	textarea{resize: none;};
    	.ms-content--body-title{
			color: rgb(102, 102, 102);
    		line-height: 45px;
    		width: 100%;
    		z-index: 1500;
    		position: fixed;
    		right: 0px;
    		top: 45px;
   			text-align: right;
    		border-bottom: 1px solid rgb(211, 215, 219);
    		background: rgb(255, 255, 255);
    		padding: 0px 10px;
		}
    </style>
</head>
<body>
    <@ms.content>
        <@ms.contentBody>
            <!--title对应板块名称-->
            <@ms.contentNav title="微信用户">
            	<@ms.button class="btn btn-success"  id="synchronousPeople"  value="一键同步"/>
            </@ms.contentNav>
            <@ms.contentPanel>
            	<@ms.panelNav empty=true>												
				</@ms.panelNav>	
                <!--表格标题--> 
                <table data-toggle="table">
    				<thead>
        				<tr>
            				<th class="text-center">用户头像</th>
            				<th class="text-center">用户昵称</th>
            				<th class="text-center">用户所在地</th>
            				<th class="text-center">用户电话</th>
            				<th class="text-center">操作</th>
        				</tr>
    				</thead>
    				<tbody>
					<tr class="text_blank">
						<td colspan="1330px" style="text-align:center">
							<@ms.nodata content="列表下空空如也!"/>
						</td>
					</tr>
					
    				</tbody>
				</table>      
                <!--列表分页-->
                <#if page?has_content>
                    <ul class="pagination" style="float:right;">
  						<li class="prev <#if (page.pageNo+1)==1> disabled" ><a<#else> "><a href="${page.previousUrl}" </#if> target="_self"><i class="fa fa-angle-left">上一页</i></a></li>
  						<#if ((page.pageCount) <= 10)>
							<#list 1..page.pageCount as i>
								<li <#if page.pageNo+1==i>class="active" ><a<#else> ><a href='${page.linkUrl}pageNo=${i}'</#if>  target="_self">${i}</a></li>
							</#list>
						<#else>
							<#list page.pageNo+1..page.pageNo+10 as i>
								<li <#if page.pageNo+1==i>class="active" ><a<#else> ><a href='${page.linkUrl}pageNo=${i}'</#if>  target="_self">${i}</a></li>
							</#list>
						</#if>
						<li class="next <#if page.pageNo+1 == page.pageCount>disabled" ><a<#else> "><a href="${page.nextUrl}"</#if>  target="_self"><i class="fa fa-angle-right">下一页</i></a></li>  						
					</ul>
                </#if>                                  
            </@ms.contentPanel>
        </@ms.contentBody>
    </@ms.content>
    <@ms.modal  modalName="messageModel" title="发送消息" >
		<@ms.modalBody>
			<@ms.form name="textForm" id="textForm" isvalidation=true  action="" method="post"  >
				<@ms.textarea rows="7" style="height:300px;" maxlength="600" size="600"  placeholder="请输入600个字符以内的文本素材！" name="messageContent"/>				
            	<input type="hidden" name="weixinPeopleOpenId"/>
			</@ms.form>
		</@ms.modalBody>
		<@ms.modalButton>
			<!--模态框按钮组-->
			<@ms.button  value="发送"  id="sendMessageButton"  />
		</@ms.modalButton>
	</@ms.modal>
    <script>
        $(function(){
        	//计算当前用户的状态
            /**
            * 计算相差小时的函数，通用
            * sDate1:开始日期
            * sDate2:结束日期
            * 返回:相差的天数
            */
      /*      function  dateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
                var  aDate,  oDate1,  oDate2,  iDays  
                aDate  =  sDate1.split("-") ;
                oDate1  =  new  Date()    //转换为12-18-2006格式  
                oDate1.setFullYear(aDate[0]);
                oDate1.setMonth(eval(aDate[1]-1));
                oDate1.setDate(aDate[2]);
                iDays  =  parseInt(Math.abs(oDate1  -  sDate2) /  1000  /  60  /  60  /24)    //把相差的毫秒数转换为天数  
                return  iDays ;
             }
             //获取系统当前时间
             function current(){ 
                var d=new Date(),str=''; 
                str +=d.getFullYear()+'-'; //获取当前年份 
                str +=d.getMonth()+1+'-'; //获取当前月份（0——11） 
                str +=d.getDate(); 
                str +=d.getHours()+'-'; 
                str +=d.getMinutes()+'-'; 
                str +=d.getSeconds(); 
                return str; 
            } */
            var listPeople = ${peopleList};		
			for(var i = 0;i<listPeople.length;i++){
			/*	var peopleTime =  listPeople[i].peopleDateTime;		
				//判断是否为静默用户
				if(dateDiff(current(),peopleTime)>=2){
					state = "静默用户";
				}else{
					state = "活跃用户";
				}*/
				if(listPeople[i].peoplePhone == undefined){
					listPeople[i].peoplePhone = "暂无";
				}
				//追加内容
				<#if peopleList?has_content>
					$(".text_blank").hide();
					$("tbody").append('<tr>'+
		        		'<td class="text-center"><img src="'+listPeople[i].weixinPeopleHeadimgUrl+'" style="border-radius: 12px;width:25px;height:25px;"></td>'+
		        		'<td style="width:20%">'+listPeople[i].peopleUserNickName+'</td>'+
		        		'<td style="width:20%">'+listPeople[i].weixinPeopleProvince+'/'+listPeople[i].weixinPeopleCity+'</td>'+
			           	'<td style="width:20%">'+listPeople[i].peoplePhone+'</td>'+
			            '<td class="text-center"><button style="line-height: 9px"  type="button" class="btn btn-success col-md sendMessage" data-id="'+listPeople[i].weixinPeopleOpenId+'">发送消息</button></td>'+ 
			        '</tr>');
			        //点击发送信息按钮，弹出消息框
					$(".sendMessage").click(function(){		
						var weixinOpenId = $(this).attr("data-id");
						$("input[name='weixinPeopleOpenId']").val(weixinOpenId);
						$(".messageModel").modal();
					});
            	</#if>				
			}
        })
        
        //同步微信公众号的用户到数据库中
        $("#synchronousPeople").click(function(){
        	$.ajax({
        		type:"post",
        		dataType: "json",
        		url:"${managerPath}/weixin/weixinPeople/importPeople.do",
        		beforeSend:function(){
        			$("#synchronousPeople").text("同步中..");
        			$(".sendMessage").attr("disabled","true");
               		$("#synchronousPeople").attr("disabled","true");
        		},
        		success: function(msg){
                    if(msg.result == true){
                    	alert("同步成功!")                     
                    }else{
                        alert("同步失败!")
                    }
                    location.reload();
               }
        	})
        })
        
        //点击发送按钮开始发送消息，只支持文本发送
        $("body").delegate("#sendMessageButton","click",function(){
            var content = $.trim($("textarea[name='messageContent']").val());
            if(content == "" || content == undefined){
            	alert("发送内容不能为空!");
            	return;
            }else if(content.length>300){
            	alert("内容过长!");
            	return;
            }
            $.ajax({
               type: "post",
               dataType: "json",
               url:  "${base}${baseManager}/weixin/message/"+$("input[name='weixinPeopleOpenId']").val()+"/sendText.do",
               data: "content=" + content,
               beforeSend:function(){
               		$("#sendMessageButton").text("发送中..");
               		$("#sendMessageButton").attr("disabled",true);
               },
               success: function(msg){
                    if(msg.result == true){
                    	alert("发送成功!")                     
                    }else{
                        alert("发送失败!")
                    }
                    $(".messageModel").modal("hide");
                    $("#sendMessageButton").text("发送");
               		$("#sendMessageButton").attr("disabled",false);
               }
            });
        });
    </script>
</body>
</html>

