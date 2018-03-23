## Hello World

spring boot作为一款简便的框架，一开始的初衷就是为了简化spring项目的开发过程，省去大量的配置文件，减轻开发人员的工作量和工作的复杂程度。
既然spring boot作为一个越来越多人使用的框架，现在就开始动手写一个hello world走上使用spring boot的道路。
一、下载STS(spring tools suite)
	到spring的官网下载[STS](https://spring.io/tools/)开发工具。
二、新建Spring Starter Project
	点击STS中的**File**->**NEW**->**Spring Starter Project**,根据提示填写和选中需要导入的依赖，其中如果是WEB项目的话需要勾选WEB依赖。这里新建一个imgr项目。
三、运行Application文件
	找到自动生成的ImgrApplication.java文件，右键**Run As**->**Spring Boot App**。(备注：如果出现“错误: 找不到或无法加载主类”，是因为本地依赖库尚未下载依赖的文件。)
四、设置服务器端口
	在项目中resource文件夹下找到application.properties文件，配置服务器的端口号，如下面的配置：
		server.port=8080
	这个时候再次运行Application文件，然后再浏览器中输入localhost:8080就会出现一个**Whitelabel Error Page**页面，表示已经运行成功。
五、编写controller代码
	新建一个包controller，并在包中新建一个类HelloController.java,代码如下：
```
	@RestController
	public class HelloController {
		@RequestMapping("/")
		public String hello() {
			return "Hello World!";
		}
	}
```
