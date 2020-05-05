/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */


layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //用户管理
  table.render({
    elem: '#LAY-user-back-manage'
    ,url: 'user/userList' //模拟接口
    ,method: 'post'
    ,page: true
    ,cols: [[
      {field: 'userName', title: '用户名'}
      ,{field: 'actualName', title: '姓名'}
      ,{field: 'mobile', title: '手机'}
      ,{field: 'email', title: '邮箱'}
      ,{field: 'createAt', title: '创建时间',templet:function (d) {                   
  	    return d.createAt.replace("T"," ");
      }}
      ,{field: 'createUserName', title: '创建人'}
      ,{field: 'areaName', title: '负责区域'}
      ,{field: 'roleName', title: '角色'}
      ,{field: 'state', title:'状态', templet: '#buttonTpl', minWidth: 80, align: 'center'}
      ,{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
    ]]
    ,text: '对不起，加载出现异常！'
  });
  
  //监听工具条
  table.on('tool(LAY-user-back-manage)', function(obj){
    var data = obj.data;
   if(obj.event === 'update'){
      var tr = $(obj.tr);
      layer.open({
        type: 2
        ,title: '编辑用户'
        ,content: 'view?url=user-edit'
        ,area: ['420px', '500px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
         var submitID = 'LAY-user-back-submit'
        ,submit = layero.find('iframe').contents().find('#'+ submitID);
      	var body = layer.getChildFrame('body', index);
      	body.find("#LAY-user-back-submit").click();
        }
        ,success: function(layero, index){           
        	var body = layer.getChildFrame('body', index);
            body.find("#userName").val(data.userName);
            body.find("#actualName").val(data.actualName);
            body.find("#mobile").val(data.mobile);
            body.find("#email").val(data.email);
            body.find("#backstageRoleList").val(data.backstageRoleList);
            body.find("#state").val(data.state);
            body.find("#id").val(data.id);
            body.find("#siteArea").val(data.siteArea);
        }
      })
    }else if(obj.event === 'delete'){
    	layer.confirm('确定删除此用户？', function(index){
    	$.ajax({
       		url : 'user/delById',
       		data : {id:data.id} ,
       		dataType : 'JSON',
       		type : 'POST',
       		success : function(result){
       			if(result.success){
       				layer.alert('删除成功', {
						icon: 1
					});
       				table.reload('LAY-user-back-manage',{page: {curr: 1}});
       	        }else{
       	        	layer.msg('删除失败', {
						icon: 5
					});
       	        }
       	   }
       	});
    	});
    }
  });
  
  
  
  //角色管理
  table.render({
    elem: '#LAY-user-back-role'
    ,url: 'role/role_list', //模拟接口
    method: 'post'
    ,page: true
    ,cols: [[
      {field: 'roleName', title: '名称'}
      ,{field: 'roleDesc', title: '描述'}
      ,{field: 'createAt', title: '创建时间',templet:function (d) {                   
  	    return d.createAt.replace("T"," ");
      }}
      ,{field: 'createUser', title: '创建人'}
      ,{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
    ]]
    ,text: '对不起，加载出现异常！'
  });
  
  //监听工具条
  table.on('tool(LAY-user-back-role)', function(obj){
    var data = obj.data;
    if(obj.event === 'edit'){
      var tr = $(obj.tr);
      layer.open({
        type: 2
        ,title: '编辑角色'
        ,content: 'view?url=role-edit'
        ,area: ['500px', '480px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
        	var submitID = 'LAY-user-role-submit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);
              	var body = layer.getChildFrame('body', index);
              	body.find("#LAY-user-role-submit").click();
        }
        ,success: function(layero, index){
        	var body = layer.getChildFrame('body', index);
            body.find("#roleName").val(data.roleName);
            body.find("#roleDesc").val(data.roleDesc);
            body.find("#roleId").val(data.roleId);
            body.find("#menuList").val(data.menuList);
        }
      })
    }else if(obj.event === 'delete'){
    	layer.confirm('确定删除此角色？', function(index){
        	$.ajax({
           		url : 'role/delById',
           		data : {id:data.id} ,
           		dataType : 'JSON',
           		type : 'POST',
           		success : function(result){
           			if(result.success){
           				layer.alert('删除成功', {
    						icon: 1
    					});
           				table.reload('LAY-user-back-role',{page: {curr: 1}});
           	        }else{
           	        	layer.msg('删除失败', {
    						icon: 5
    					});
           	        }
           	   }
           	});
        	});
        }
  });

  exports('useradmin', {})
});