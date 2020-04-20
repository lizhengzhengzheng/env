$(function () {
    //加载弹出层
    layui.use(['form','element'],
    function() {
        layer = layui.layer;
        element = layui.element;
    });

    //触发事件
  var tab = {
        tabAdd: function(title,url,id){
          //新增一个Tab项
          element.tabAdd('xbs_tab', {
            title: title 
            ,content: '<iframe tab-id="'+id+'" frameborder="0" src="'+url+'" scrolling="yes" class="x-iframe"></iframe>'
            ,id: id
          })
        }
        ,tabDelete: function(othis){
          //删除指定Tab项
          element.tabDelete('xbs_tab', '44'); //删除：“商品管理”
          
          
          othis.addClass('layui-btn-disabled');
        }
        ,tabChange: function(id){
          //切换到指定Tab项
          element.tabChange('xbs_tab', id); //切换到：用户管理
        }
      };
	//表格中的操作
	table = {
		check:function(e,data){//选择
			var allCheck = false
			if($(e).hasClass('header')){//判断是否是全选
				if($(e).hasClass('layui-form-checked')){//全选取消
					allCheck = false
					for(var i in data){
						data[i].check = false
					}
				}else{//全选
					allCheck = true
					for(var i in data){
						data[i].check = true
					}
				}
			}else{//实验室选中
				var i = $(e).data("index")
				data[i].check = !data[i].check
				for(var i in data){
					if(data[i].check){
						allCheck = true
					}else{
						allCheck = false
						break
					}
				}
			}	
			return allCheck
		},
		del:function(e,url,data){//单个删除
			layer.confirm("确认删除吗？",function(){
				var i = $(e).data("index")
				var id = data[i].id
				// $ajax({
					// url:url,
					// data:{id:id},
					// success:function(res){
						data.splice(i,1)
						layer.msg("删除成功",{time:1000}) 
					// },
// 						error:function(){
// 							
// 						}
					// })
				// })
			})
		},
		delAll:function(url,data){//选中删除
			var ids = []//请求是传输的实验室id数组
			for(var i in data){
				if(data[i].check){
					ids.push(data[i].id)
				}
			}
			if(ids.length){
				layer.confirm(" 确认删除吗？",function(){
					// $.ajax({
						// url:url,
						// data:{ids:ids},
						// success:function(res){
							for(var i in data){
								if(data[i].check){
									data.splice(i,1)
									layer.msg("删除成功",{time:1000}) 
								}
							}
						// },
// 						error:function(){
// 							
// 						}
					// })
				})
			}else{
				layer.msg("请选择实验室")
			}
		},
		// 启用停用
		enable:function(e,url,data){
			var i = $(e).data("index");
			var id = data.id
			var str1 = vm.Classroom[i].state?"确认要停用吗？":"确认要启用吗？"
			var str2 = vm.Classroom[i].state?"停用成功":"启用成功"
			layer.confirm(str1,function(){
				// $.ajax({
					// url:url,
					// data:{id:id,state:!vm.Classroom[i].state},
					// success:function(){
						vm.Classroom[i].state = !vm.Classroom[i].state
						layer.msg(str2,{icon:1,time:1000})
					// },
					// error:function(){
						// layer.msg("操作失败，请重试！",{time:1000})
					// }
				// })
			})
		}
	}
    
	
	
	 

    $('.container .left_open i').click(function(event) {
        if($('.left-nav').css('left')=='0px'){
            $('.left-nav').animate({left: '-221px'}, 100);
            $('.page-content').animate({left: '0px'}, 100);
            $('.page-content-bg').hide();
        }else{
            $('.left-nav').animate({left: '0px'}, 100);
            $('.page-content').animate({left: '221px'}, 100);
            if($(window).width()<768){
                $('.page-content-bg').show();
            }
        }
    });

    $('.page-content-bg').click(function(event) {
        $('.left-nav').animate({left: '-221px'}, 100);
        $('.page-content').animate({left: '0px'}, 100);
        $(this).hide();
    });

    $('.layui-tab-close').click(function(event) {
        $('.layui-tab-title li').eq(0).find('i').remove();
    });

   $("tbody.x-cate tr[fid!='0']").hide();
    // 栏目多级显示效果
    $('.x-show').click(function () {
        if($(this).attr('status')=='true'){
            $(this).html('&#xe625;'); 
            $(this).attr('status','false');
            cateId = $(this).parents('tr').attr('cate-id');
            $("tbody tr[fid="+cateId+"]").show();
       }else{
            cateIds = [];
            $(this).html('&#xe623;');
            $(this).attr('status','true');
            cateId = $(this).parents('tr').attr('cate-id');
            getCateId(cateId);
            for (var i in cateIds) {
                $("tbody tr[cate-id="+cateIds[i]+"]").hide().find('.x-show').html('&#xe623;').attr('status','true');
            }
       }
    })

    //左侧菜单效果
    // $('#content').bind("click",function(event){
    $(document).on('click','.left-nav #nav li',function(event){
    	 event.stopPropagation();
        if($(this).children('.sub-menu').length){
            if($(this).hasClass('open')){
                $(this).removeClass('open');
                $(this).find('.nav_right').html('&#xe697;');
                $(this).children('.sub-menu').stop().slideUp();
                $(this).siblings().children('.sub-menu').slideUp();
            }else{
                $(this).addClass('open');
                $(this).children('a').find('.nav_right').html('&#xe6a6;');
                $(this).children('.sub-menu').stop().slideDown();
                $(this).siblings().children('.sub-menu').stop().slideUp();
                $(this).siblings().find('.nav_right').html('&#xe697;');
                $(this).siblings().removeClass('open');
            }
        }else{

            var url = $(this).children('a').attr('_href');
            var title = $(this).find('cite').html();
            var index  = $('.left-nav #nav li').index($(this));
            for (var i = 0; i <$('.x-iframe').length; i++) {
                if($('.x-iframe').eq(i).attr('tab-id')==index+1){
                    tab.tabChange(index+1);
                    event.stopPropagation();
                    return;
                }
            };
            
            tab.tabAdd(title,url,index+1);
            tab.tabChange(index+1);
        }     
        event.stopPropagation();  
    });
});
var cateIds = [];
function getCateId(cateId) {
    $("tbody tr[fid="+cateId+"]").each(function(index, el) {
        id = $(el).attr('cate-id');
        cateIds.push(id);
        getCateId(id);
    });
}

/*弹出层1*/
/*
    参数解释：
    title   标题
    url     请求的url
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function x_admin_show(title,url,w,h){
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    if (w == null || w == '') {
        w=($(window).width()*0.9);
    };
    if (h == null || h == '') {
        h=($(window).height() - 50);
    };
    layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: title,
        content: url
    });
}
/*弹出层2*/
/*
    参数解释：
    title   标题
    url     请求的url
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function x_admin_show2(data){
    if (data.title == null || data.title == '') {
        data.title=false;
    };
    if (data.url == null || data.url == '') {
        data.url="404.html";
    };
    if (data.w == null || data.w == '') {
        w=($(window).width()*0.9);
    };
    if (data.h == null || data.h == '') {
        data.h=($(window).height() - 50);
    };
    layer.open({
        type: 2,
        area: [data.w+'px', data.h +'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: data.title,
        content: data.url,
		success:data.success,
		btn:data.btn,
		yes:data.yes
    });
}
/*关闭弹出框口*/
function x_admin_close(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
/*ajax请求*/
// url接口名 data参数 callback回调 contentType参数中有数组为true
function POST(url,data,callback,contentType){
	var index = layer.load();
	console.log(url)
	$.ajax({
		url: url,
		data:data,
		dataType:"json",
		type:"POST",
		contentType : contentType?'application/json':'application/x-www-form-urlencoded',
		success:function(res){
			layer.close(index);   
			if(res.resultCode==="9999"){
				res.message=='成功！'?'':layer.msg(res.message)
				if(callback) callback(res.data)
			}else{
				layer.msg(res.message)
			}
		},
		error:function(){
			layer.close(index);   
			layer.msg("操作失败，请刷新重试！")
		}
	});
	
}