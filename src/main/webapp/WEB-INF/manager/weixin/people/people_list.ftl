<@ms.html5> 
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
                <table id="orderTable"
					data-show-refresh="true"
	        		data-show-columns="true"
	        		data-show-export="true"
					data-method="post" 
					data-detail-formatter="detailFormatter" 
					data-pagination="true"
					data-page-size="1"
					data-side-pagination="server">
				</table>                                     
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
</@ms.html5>
<script>
	$(function(){
		 var listPeople;
            $.ajax({		
		   		type:"GET",
				url:"${managerPath}/weixin/weixinPeople/list.do",
		    
		   	 	success:function(data) { 
		    	 listPeople = data.rows;
		    	
				for(var i = 0;i<listPeople.length;i++){
					j = i;
				/*var peopleTime =  listPeople[i].peopleDateTime;		
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
					<#if listPeople?has_content>
						$(".text_blank").hide();
			        	//点击发送信息按钮，弹出消息框
						$(".sendMessage").click(function(){		
							var weixinOpenId = $(this).attr("data-id");
							$("input[name='weixinPeopleOpenId']").val(weixinOpenId);
							$(".messageModel").modal();
						});
            		</#if>				
					}
				}
			});
			//对应bootstrap-table框架的控制
			var j = 0;  //接下数据
			$("#orderTable").bootstrapTable({
        		url:"${managerPath}/weixin/weixinPeople/list.do",
        		contentType : "application/x-www-form-urlencoded",
        		queryParams:function(params) {
					return  $.param(params)+"&pageSize="+ params.limit+"&pageNo="+(params.offset+1);
				},
			    columns: [{
			    	align:'center',
			        field: 'weixinPeopleHeadimgUrl',
			        title: '用户头像',
			        formatter:function(value,row,index){return "<img src=" + value + " style='border-radius: 12px;width:25px;height:25px;'>"}
			    }, {
			    	align:'center',
			        field: 'peopleUserNickName',
			       	 title: '用户昵称'
			    },{
			    	align:'center',
			        field: 'weixinPeopleProvince',
			        title: '用户所在地'
			    }, {
			    	align:'center',
			        field: 'peoplePhone',
			        title: '用户电话'
			        },
			        {
			        align:'center',
			        field: 'weixinPeopleCity',
			        title: '操作',
			        formatter:function(value,row,index){return "<button style='line-height: 9px;'  type='button' class='btn btn-success col-md sendMessage' data-id=" + listPeople[j].weixinPeopleOpenId + ">发送消息</button>"}
			    }]
        	});                 
		})   
 </script>
 <script>
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


