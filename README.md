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

六、详细代码见文件夹2018-03-24/helloworld/imgr

## 多环境配置

在项目的开发中，需要根据不同的环境进行不同的配置，例如开发环境、测试环境以及正式环境等，都需要配置不同的内容。spring boot提供了多环境配置的功能，在配置文件application.properties中配置`spring.profiles.active=dev`然后新增一个配置文件，名称如"application-dev.properties"，即可在里面进行配置。如果需要切换到另外的环境，只需要改变`spring.profiles.active`的值，同时新增相对应的文件即可。

## 热部署

在spring boot中，熱部署是一件非常简单的事，只需要配置一下即可完成热部署的功能，热部署有两种方式(热部署都需要开启自动编译)：

一、devtools
只需要在pom.xml中加上devtools的依赖即可，如下：
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
	<optional>true</optional>
	<scope>true</scope>
</dependency>
```
使用devtools用一个缺点，就是在实际项目开发中，项目重新加载，如果存在登录页面，需要重新登录系统。

二、springloaded
在pom.xml中加上依赖，如下：
```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>springloaded</artifactId>
</dependency>
```
同时如果有开启devtools的，需要将devtools的依赖注释或者在配置文件中通过`spring.devtools.restart.enabled=false`配置devtools不起作用。
要让springloaded起作用，还需要使用debug模式来运行项目或者用maven命令`maven spring-boot:run`来启动。(备注：该热部署对于新增的mapping无效)

三、详细代码见文件夹2018-03-24/热部署/imgr

## 部署
spring boot项目可以通过jar包或者war包的方式进行部署，由于spring boot已经集成了tomcat，所以通过jar包就可以打包部署，这里主要介绍jar包的部署方式，war包的部署方式以后再介绍。
其中环境：虚拟机CentOS
一、打包
打包的方式有两种，一种直接用maven命令打包，另一种为在IDE中用maven命令打包。
右键项目->`Run As` -> `Maven Build...` ->输入打包命令`package`即可打包完成。

二、上传到服务器
在xshell中通过rz命令将jar包通过ftp上传到服务器。

三、启动项目
1、在linux中可以通过命令`java -jar imgr.jar`命令启动应用。使用以上命令可以启动应用，但是当用户退出shell窗口或者使用`clear`命令清屏的时候，应该也会关闭。
2、使用命令`nohup java -jar imgr.jar &`命令可以解决以上的问题
3、为了停止应用更加方便，可以使用命令`echo $!`来记录每一次的应用进程id。

四、命令脚本见文件夹2018-03-24/部署/start.sh