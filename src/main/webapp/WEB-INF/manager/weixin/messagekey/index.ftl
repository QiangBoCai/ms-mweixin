<@ms.html5>
	<@ms.panel>	
		<!--title对应板块名称-->
		<@ms.contentNav title="关键字回复">
			<@ms.panelNav empty=false>
				<!--列表操作按钮，添加和删除-->
				<@ms.panelNavBtnGroup>
					<@ms.panelNavBtnAdd id="addButton" value="" />
					<@ms.panelNavBtnDel id="delButton" value="" />
				</@ms.panelNavBtnGroup>													
			</@ms.panelNav>
		</@ms.contentNav>
		<!--表格标题-->	
		<table id="messagekeyListTable"
			data-show-refresh="true"
	        data-show-columns="true"
	        data-show-export="true"
			data-method="post" 
			data-detail-formatter="detailFormatter" 
			data-pagination="true"
			data-side-pagination="server">
		</table>	
	</@ms.panel>
	<@ms.modal  modalName="delMessagekeyModal" title="删除关键字回复" >
		<@ms.modalBody>
			删除关键字
		</@ms.modalBody>
		<@ms.modalButton>
			<!--模态框按钮组-->
			<@ms.button  value="确认删除？"  id="deleteMessagekey"  />
		</@ms.modalButton>
	</@ms.modal>											
</@ms.html5>
<script>
	$(function(){
		$("#messagekeyListTable").bootstrapTable({
        	url:"${managerPath}/weixin/messagekey/list.do",
        	contentType : "application/x-www-form-urlencoded",
        	queryParamsType : "undefined",
        	queryParams:function(params) {
				return  $.param(params);
			},
			columns: [{checkbox:'true'},
			{
				align:'center',
			   	field: 'passiveMessageKey',
			    title: '关键词'
			    
			}, {
			    align:'center',
			    field: 'passiveMessageId',
			    title: '回复内容',
			    formatter:function(value,row,index){
			    	if(row.newsEntity.newsType == 0){
			    		return "<a style='cursor: pointer;'  class='editMessage' data-original-title='修改' data-id=" + row.passiveMessageId + ">" + row.newsEntity.newsMasterArticle.basicTitle + "</a>";
			    	}
			    	else if(row.newsEntity.newsType == 1){
			    		return "<a style='cursor: pointer;'  class='editMessage' data-original-title='修改' data-id=" + row.passiveMessageId + ">主图文标题：" + row.newsEntity.newsMasterArticle.basicTitle + "</a>";
			    	}
			    	else if(row.newsEntity.newsType == 2){
			    		return "<a style='cursor: pointer;'  class='editMessage'   data-original-title='修改' data-id=" + row.passiveMessageId + ">" + row.newsEntity.newsContent + "</a>";
			    	}
			    	else if(row.newsEntity.newsType == 3){
			    		return "图片素材";
			    	}
			    	else{
			    		return "未知";
			    	}
			   }
			},{
			    align:'center',
			    field: 'newsType',
			    title: '素材类型',
			    formatter:function(value,row,index){
			    	if(row.newsEntity.newsType == 0){
			    		return "单图文素材";
			    	}
			    	else if(row.newsEntity.newsType == 1){
			    		return "多图文素材";
			    	}
			    	else if(row.newsEntity.newsType == 2){
			    		return "文本素材";
			    	}
			   }
			    
			}]
        });
    })
	//新增关键字回复
	$("#addButton").click(function(){
		location.href = base+"${baseManager}/weixin/messagekey/add.do"; 
	});
	//编辑关键字回复
	$("body").delegate(".editMessage","click",function(){
		location.href = base+"${baseManager}/weixin/messagekey/"+$(this).attr("data-id")+"/edit.do"; 
	})
	//判断打开删除模态框的条件
	$("#delButton").click(function(){
		//没有选中checkbox
		if($("input[type=checkbox]:checked").length <= 0){
			alert("请选择要删除的关键字回复");
		//点击全选，但是列表为空
		}else if($("input[name='btSelectItem']:checked").length == 0){
			alert("没有可删除的关键字回复");
		}else{
			$(".delMessagekeyModal").modal();
		}
	})
	//批量删除
	$("#deleteMessagekey").click(function(){
		$(this).text("努力删除中...")
		$(this).attr("disabled","true");
		var keyMessageIds = [];
		rows = $("#messagekeyListTable").bootstrapTable("getSelections");	
		for(var i = 0;i<rows.length;i++){
			keyMessageIds[i] = rows[i].passiveMessageId;
		}
		$.ajax({		
		    type:"GET",
			url:"${managerPath}/weixin/messagekey/delete.do",
		    data:"keyMessageIds="+keyMessageIds,
		    success:function(msg) { 
				if (msg.result == false) {
					alert("删除失败");
				}else{
					alert("删除成功");
					location.href = base+"${baseManager}/weixin/messagekey/index.do";
				}
			}
		});	
	})    
</script>
