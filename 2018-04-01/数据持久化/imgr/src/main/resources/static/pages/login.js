layui.use(['form'],function(){
	var $ = layui.$;
	
	$(".verifycode-img").on("click",function(){
		$(".verifycode-img").attr("src","/kaptcha?t="+Math.floor(Math.random()*100));
	});
});