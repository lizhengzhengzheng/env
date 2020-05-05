layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;
//站点管理
  table.render({
	    elem: '#LAY-site-management'
	    ,url: 'site/sitePage' 
	    ,method: 'post'
	    ,page: true
	    ,cols: [[
	    	{field: 'siteName', title: '名称'}
	      ,{field: 'areaName', title: '区域'}
	      ,{field: 'siteAddress', title: '地址'}
	      ,{field: 'typeId', title: '类型',templet:function (d) {   
	    	  if(d.typeId == 1){
		    		 return "饮用水"; 
		    	  }else if(d.typeId == 2){
		    		  return "湖库"; 
		    	  }else if(d.typeId == 3){
		    		  return "大气"; 
		    	  }else if(d.typeId == 4){
		    		  return "其他"; 
		    	  }else{
		    		  return ""; 
		    	  }
	          }}
	      ,{field: 'accessCode', title: '接入码'}
	      ,{field: 'manager', title: '负责人'}
	      ,{field: 'netIp', title: '通信ip'}
	      ,{field: 'uploadFrequency', title: '频率(秒/次)'}
	      ,{field: 'createUserName', title: '创建人'}
	      ,{field: 'createAt', title: '创建时间',templet:function (d) {                   
      	    return d.createAt.replace("T"," ");
          }}
	      ,{title: '操作', width: 250, align: 'center', fixed: 'right', toolbar: '#table-site-management'}
	    ]]
	    ,text: '对不起，加载出现异常！'
	  });
	  
	  //监听工具条
	  table.on('tool(LAY-site-management)', function(obj){
	    var data = obj.data;
	    if(obj.event === 'del'){
	      layer.confirm('确定删除此站点？', function(index){
	    	  $.ajax({
					url : 'site/delete',
					type : 'post',
					data :  {"id":data.id},
					success : function(result) {
						if (result.success) {
							layer.alert(result.message, function(index){
								obj.del();
						        layer.close(index);
							})
						} else {
							layer.alert(result.message, function(index){
								obj.del();
						        layer.close(index);
							})
						}
					}
				})
	      });
	    }else if(obj.event === 'edit'){
	    	layer.open({
	            type: 2,
	            title: false,
	            closeBtn: 0,
	            content: 'view?url=site-edit',
	            area: ['100%', '100%'],
	            shadeClose:true,
	            success: function (layero, index) {
	            	var body = layer.getChildFrame('body', index);
	                body.find("#siteName").val(data.siteName);
	                body.find("#areaId").val(data.siteArea);
	                body.find("#typeId").val(data.typeId);
	                body.find("#siteAddress").val(data.siteAddress);
	                body.find("#siteDesc").val(data.siteDesc);
	                body.find("#longitude").val(data.longitude);
	                body.find("#latitude").val(data.latitude);
	                body.find("#img_url").val(data.siteImg);
	                body.find("#test-upload-normal-img").attr('src',data.siteImg);
	                body.find("#deviceName").val(data.deviceName);
	                body.find("#accessCode").val(data.accessCode);
	                body.find("#netIp").val(data.netIp);
	                body.find("#simNum").val(data.simNum);
	                body.find("#simStore").val(data.simStore);
	                body.find("#networkingMethod").val(data.networkingMethod);
	                body.find("#uploadFrequency").val(data.uploadFrequency);
	                body.find("#id").val(data.id);
	            }
	        })
	    }else if(obj.event === 'check'){
	    	layer.open({
	            type: 2,
	            title: false,
	            closeBtn: 0,
	            content: 'view?url=site-detectEquip',
	            area: ['100%', '100%'],
	            shadeClose:true,
	            success: function (layero, index) {
	            	var body = layer.getChildFrame('body', index);
	                body.find("#siteId").val(data.id);
	                body.find("#siteName").html(data.siteName);
	            }
	        })
	    }
	  });

	  exports('siteTable', {})
	});