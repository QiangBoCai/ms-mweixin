<@ms.html5>
	<@ms.nav title="站点管理"></@ms.nav>
	<@ms.panel>
		<@ms.panelNav>
			<@ms.buttonGroup>
				<@ms.addButton url="${base}${baseManager}/website/add.do"/>
				<@ms.delButton id="delWebsiteBtn" fieldName="websiteId" onclick="remove"/>
			</@ms.buttonGroup>
			<@ms.menuButton links=[{"click":"setManager","name":"设置管理员"}] name="操作"/>
		</@ms.panelNav>
		<@ms.table head=['编号','标题','域名','状态,180','手机端,180','续费日期,200'] checkbox="websiteId" id="websiteTable"> 
       			<#if websiteList?has_content>
	           		<#list websiteList as website>
					<tr>
						<td> 
							<input type="checkbox" name="websiteId" value="${website.websiteId}" data-manager-id="${website.websiteManagerId}" data-website-name="${website.websiteName}"/>
						</td>
                        <td>${website.websiteId?c?default(0)}</td>
			            <td style="width:100px">
			            	<a class="btn btn-xs red tooltips" data-rid="" data-toggle="tooltip"  data-original-title="编辑站点" href="${base}${baseManager}/website/${website.websiteId?default(0)}/edit.do" data-id="${website.websiteId?c?default(0)}">
			            		${website.websiteName?default(0)}
			            	</a>
			            </td>
			            <td>
			            	<#list website.websiteUrl?split('\r') as url>
			            		<a href="${url}" target="_blank"  data-toggle="tooltip"  data-original-title="点击查看">${url}<a><br/>
			            	</#list>
			            </td>
			            <td><#if website.websiteState==0><font color="green">运行中</font><#else><font color="red">已停止</font></#if></td>
			            <td><#if website.websiteMobileState==0><font color="green">已启用</font><#else><font color="red">未启用</font></#if></td>
			            <td title="${website.websitePay?default('')}">${(website.websitePayDate?string('yyyy-MM-dd'))!''}</td>
                	</tr>
             		</#list>
             		<#else>
	  			 	<tr>
			            <td colspan="7">
			            	<@ms.nodata content="您还没有添加站点，请添加站点" />
						</td>
		          	</tr>
    			</#if>  
        </@ms.table>
	</@ms.panel>		


	<!--添加或编辑站点管理员-->
	<@ms.modal modalName="addAndEdit" title="管理员设置">
		 <@ms.modalBody height="400">
			<@ms.form isvalidation=true name="managerForm" action="${managerPath}/website/manager/update.do" redirect="${base}${baseManager}/website/list.do">
				<@ms.hidden name="managerWebsiteId"/>
				<@ms.hidden name="modelIds"/>
				<@ms.text name="managerName" readonly="readonly" width="250" label="帐号" title="管理员帐号" maxlength="12" placeholder="请输入管理员帐号" validation={"required":"true", "data-bv-notempty-message":"请输入管理员帐号!","data-bv-regexp":"true","data-bv-regexp-regexp":"^[a-zA-Z0-9_]+$","data-bv-regexp-message":"帐号只能由英文字母，数字，下划线组成!","data-bv-stringLength-message":"帐号长度最长为12个字符！"}/>
	    		<@ms.text name="managerNickName" width="250" label="昵称" title="管理员昵称"  maxlength="15" placeholder="请输入管理员昵称" validation={"required":"true", "data-bv-notempty-message":"请输入管理员昵称!"} />
	    		<@ms.password name="managerPassword" width="250" label="密码" title="管理员密码"   maxlength="16" validation={"data-bv-regexp":"true","data-bv-regexp-regexp":"^[a-zA-Z0-9_]+$","data-bv-regexp-message":"密码只能由英文字母，数字，下划线组成!"}  />
	    		<@ms.formRow label="模块" width="300">
	    				<@ms.tree id="modelListTree" type="checkbox" url="${base}${baseManager}/model/queryAll.do" idKey="modelId" pIdKey="modelModelId" text="modelTitle"/>
	    		</@ms.formRow>
	    	</@ms.form>
	     </@ms.modalBody>
	     <@ms.modalButton>
	 			<@ms.saveButton postForm="managerForm" postBefor="setModelIds" postAfter="closeModal"/>  
	 	</@ms.modalButton>
	</@ms.modal>



<script>	

function setManager() {
	if ($('input[name="websiteId"]:checked').length > 1) {
		<@ms.notify msg="只能选择一条站点！"/>
	} else if ($('input[name="websiteId"]:checked').length == 0) {
		<@ms.notify msg="请选择需要设置站点！"/>
	} else {	
		$("input[name='managerPassword']").val("");
		var website = $('#websiteTable input[name="websiteId"]:checked:first');
		var websiteId = website.val();
		ms.post("${managerPath}/website/manager/"+websiteId+"/edit.do",null,function(manager){
				$("input[name='managerWebsiteId']").val(websiteId);
				if (manager.managerId>0) {
					$("input[name='managerName']").val(manager.managerName);
					$("input[name='managerNickName']").val(manager.managerNickName);
					$("input[name='managerId']").val(manager.managerId);
					$("input[name='managerName']").attr("readonly","readonly");
					ms.post("${base}${baseManager}/role/"+manager.managerRoleID+"/queryByRole.do",null,function(models){
						var tempModels = [];
						for (i=0;i<models.length;i++) {
							tempModels.push(models[i].modelId);
						}
						
						if (models != null) {
							var nodes = modelListTree.getNodes();
							modelListTree.checkAllNodes(false);
							for (i=0;i<nodes.length;i++) {
								var children = nodes[i].children;
								if (children != undefined && children.length>0) {
									for(k=0;k<children.length;k++) {
										if(jQuery.inArray(children[k].modelId, tempModels) > -1) {
											modelListTree.checkNode(children[k], true, true);
										}									
									}
								} else {
									if(jQuery.inArray(nodes[i].modelId, tempModels) > -1) {
										modelListTree.checkNode(nodes[i], true, true);
									}							
								}

							}
							
						}
					});					
					
				} else {
					$("input[name='managerNickName']").val(website.data("website-name"));
					modelListTree.checkAllNodes(false)
					$("input[name='managerName']").removeAttr("readonly");
				}
	
		});		
		$(".addAndEdit").modal();//打开该模态框
				
	}
}

function closeModal(msg) {
	$(".addAndEdit").modal("hide");
}

//设置模块工
function setModelIds() {
	var nodes = modelListTree.getCheckedNodes(true);
	var ids = [];
	for (i=0;i<nodes.length;i++) {
		ids.push(nodes[i].modelId);
	}
	$("input[name='modelIds']").val(ids);
	return true;
}

//删除站点的ajax
function remove(ids) {
	ms.post("${base}${baseManager}/website//batchDelete.do","websiteIds="+ids,function(msg){
			if(msg.result){
				location.href="${base}${baseManager}/website/list.do";
			}
	});
}
	
</script>
</@ms.html5>

