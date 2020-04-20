//准备视图对象
layui.use(['table','form','layer'], function(){
    var layer = layui.layer;
	//成功后弹出提示框
	window.alertMessage = function(message) {
		layer.alert(message, function(index){
		   layer.close(index);
		});
	};
});