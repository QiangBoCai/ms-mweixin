<@ms.html5>
	<@ms.panel>
		<@ms.contentNav title="公众号列表">
			<!--列表操作按钮，添加和删除 开始 -->
			<@ms.panelNavBtnGroup>
				<@ms.panelNavBtnAdd id="addButton" value="" /><!-- 新增按钮 -->
				<@ms.panelNavBtnDel id="delButton" value="" /><!-- 删除按钮 -->
			</@ms.panelNavBtnGroup>		
			<!--列表操作按钮，添加和删除结束 -->											
		</@ms.contentNav>	
		<!--微信列表-->
		<table id="weixinListTable"
			data-show-refresh="true"
	        data-show-columns="true"
	        data-show-export="true"
			data-method="post" 
			data-detail-formatter="detailFormatter" 
			data-pagination="true"
			data-page-size="1"
			data-side-pagination="server">
		</table>	
	</@ms.panel>
</@ms.html5>
<@ms.modal  modalName="delWeixinModal" title="删除微信" >
	<@ms.modalBody>
		删除微信
	</@ms.modalBody>
	<@ms.modalButton>
		<!--模态框按钮组-->
		<@ms.button  value="确认删除？"  id="deleteWeixin"  />
	</@ms.modalButton>
</@ms.modal>
<script>
	$(function(){
		$("#weixinListTable").bootstrapTable({
        	url:"${managerPath}/weixin/index.do",
        	contentType : "application/x-www-form-urlencoded",
        	queryParamsType : "undefined",
        	queryParams:function(params) {
				return  $.param(params)+"&pageSize="+ params.limit+"&pageNo="+params.pageNumber;
			},
			columns: [{checkbox:'true'},
			{
				align:'center',
			   	field: 'weixinId',
			    title: '编号',
			    formatter:function(value,row,index){return"	<a  id='weixinIds' value=" + row.weixinId + ">" + row.weixinId + "</a>"}
			}, {
			    align:'center',
			    field: 'weixinNo',
			    title: '微信号'
			},{
			    align:'center',
			    field: 'weixinName',
			    title: '公众号名称',
			    formatter:function(value,row,index){return"	<a class='btn btn-xs red tooltips editWeixin' data-id=" + row.weixinId + ">" + row.weixinName + "</a>"}
			}, {
			    align:'center',
			    field: 'weixinType',
			    title: '公众号类型',
			    formatter:function(value,row,index){
			    	if(row.weixinType == 0){
			    		return "服务号";
			    	}
			    	else if(row.weixinType == 1){
			    		return "订阅号";
			    	}
			   }
			},{
			    align:'center',
			    field: 'weixinToken',
			    title: '微信token'
			},{
				align:'center',
				field: 'weixinOauthUrl',
			    title: '网页2.0授权地址'
			}]
        });
    })
	//新增微信
	$("#addButton").click(function(){
		location.href = "${managerPath}/weixin/add.do"; 	
	})
	//判断打开删除模态框条件
	$("#delButton").click(function(){
		//没有选中checkbox
		if($("input[type=checkbox]:checked").length <= 0){
			alert("请选择要删除的微信");
		//点击全选，但是列表为空
		}else if($("input[name='btSelectItem']:checked").length == 0){
			alert("没有可删除的微信");
		}else{
			$(".delWeixinModal").modal();
		}
	});
	//批量删除
	$("#deleteWeixin").click(function(){
		var weixinIds = [];
		weixinIds = $("#weixinIds").attr("value");
		$(this).text("努力删除中...")
		$(this).attr("disabled","true");
		$.ajax({		
		    type:"GET",
			url:"${managerPath}/weixin/delete.do",
		    data:"weixinIds="+weixinIds,
		    success:function(msg) { 
				if(msg.result == true) {
					alert("删除成功");
				}else{
					alert("删除失败");
				}
				location.reload();
			}
		});	
	})
    $("body").delegate(".editWeixin","click",function(){
    	var weixinId = $(this).attr("data-id");
    	location.href="${managerPath}/weixin/"+weixinId+"/function.do";
    })
</script>
