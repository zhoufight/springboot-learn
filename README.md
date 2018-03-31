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

## 日志
spring boot中直接内置了slf4j的日志包，可以直接使用使用方式如下:
```
@RestController
public class HelloController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	public String hello() {
		log.info("HelloController.hello");
		return "Hello World!";
	}
}
```
如果需要配置日志log的打印方式，可以通过applcation.properties或者logback.xml的方式配置（以下只记述最基本的用法）

一、application.properties
```
#log日志的路径
logging.file=./log/imgr.log
```

二、logback.xml
application.properties的配置
```
#log
#配置logback
logging.config=classpath:logback.xml
#log日志的路径
logging.file=./log/imgr.log
```
logback.xml的配置
```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<!-- 使用默认的样式 -->
    <include resource="org/springframework/boot/logging/logback/base.xml"/>
    <!-- 打印的level -->
    <logger name="org.springframework.web" level="INFO"/>
</configuration>
```

三、代码见文件夹2018-03-24/日志/imgr

## maven命令

一、install与package的区别
install命令会将打包好的jar包放到本地的仓库:`mvn install`
package命令会将打包好的jar包放到target目录下:`mvn package`

二、忽略测试代码
通过设置参数可以在打包v奥的时候忽略测试代码的部分
`mvn package -Dmaven.test.skip=true`
或者
`mvn package -DskipTests`

## 注解与aop

借助注解和aop可以实现一些很方便的功能，例如记录日志。
以下用一个简单的例子说明一下aop的工作过程。

一、定义注解
```
@Target({ElementType.METHOD})    //注解的目标
@Retention(RetentionPolicy.RUNTIME)	//注解执行的时间，这里是运行的时候x
public @interface LogAnno {
	
}
```

二、定义逻辑代码
```
@Aspect
@Component
public class LogAop {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	//切点
	@Pointcut(value = "@annotation(com.shotq.imgr.aop.LogAnno)")
	public void service() {}
	
	@Around("service()")
	public void runMathod(ProceedingJoinPoint point) throws Throwable {
		Object result = point.proceed(); 	//业务代码执行
		MethodSignature ms = (MethodSignature) point.getSignature();
		Method method = ms.getMethod();
		log.info("执行方法："+method.getName());
	}
}
```


三、使用注解
```
@RestController
public class HelloController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("/")
	@LogAnno
	public String hello() {
		log.info("HelloController.hello");
		return "Hello World!";
	}
}
```

## 模板

springboot支持了多钟模板，其中官方推荐使用thymeleaf模板。其实使用是一个很简单的过程的，但是在尝试引入thymeleaf模板的时候却总是遇到问题，本地仓库各种包没有，各种包冲突，导致重复尝试了好几遍都没有成功。

一、导入thymeleaf的spring-boot启动包
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

二、使用springboot默认的thymeleaf配置或者配置内容
```
spring.thymeleaf.cache=false
```

三、在resource/templates目录下添加html文件

四、编写controller

五、代码见文件夹2018-03-31\模板\thymeleaf\imgr

## 模板--beetl

java的模板引擎有好多，例如freemarker、thymeleaf，但是在性能上他们都没有很好的性能，所以选择了性能更好的beetl，虽然在语法上，个人觉得beetl的确不够雅观。

一、导入beetl包
```
<dependency>
	<groupId>com.ibeetl</groupId>
	<artifactId>beetl</artifactId>
	<version>2.7.15</version>
</dependency>
```

二、配置beetl
通过java来配置beetl
```
@Configuration
public class BeetlConfig {
	
	@Value("${spring.beetl.prefix}") String templatePath;
	
	@Bean(initMethod = "init")
	public BeetlGroupUtilConfiguration beetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlConfiguration = new BeetlGroupUtilConfiguration();
		//beetl中可以自定义一些方法
		//GroupTemplate groupTemplate = beetlConfiguration.getGroupTemplate();
		//groupTemplate.registerFunction("", new Function());
		//前缀
		ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(BeetlConfig.class.getClassLoader(),templatePath);
		beetlConfiguration.setResourceLoader(resourceLoader);
		//设置配置
		//beetlConfiguration.setConfigProperties(configProperties);
		return beetlConfiguration;
	}
	
	@Bean
	public BeetlSpringViewResolver beetlSpringViewResolver() {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration());
		//后缀
		beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        return beetlSpringViewResolver;
	}

}
```
其中，可以通过配置GroupTemplate来获得一些自定义的东西，例如方法等，在权限管理或者一些特定的方法上可以使用这个注册方法，使得该方法可以在前端页面使用。

三、详细代码见文件夹2018-03-31\模板\beetl\imgr

## 验证码

一个网站，通常必不可少的内容就是登录页面，而为了安全，能够区分机器和人，通常都会有验证码。以下就是springboot集成kcaptcha验证码的过程。

一、导入kcaptcha包
```
<dependency>
    <groupId>com.github.penggle</groupId>
    <artifactId>kaptcha</artifactId>
    <version>2.3.2</version>
</dependency>
```

二、配置kcaptcha
```
@Bean
public DefaultKaptcha katchaConf() {
	DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
	Properties properties = new Properties();
	properties.setProperty("kaptcha.border", "no");
	properties.setProperty("kaptcha.border.color", "105,179,90");
	properties.setProperty("kaptcha.textproducer.font.color", "red");
	properties.setProperty("kaptcha.image.width", "100");
	properties.setProperty("kaptcha.image.height", "36");
	properties.setProperty("kaptcha.textproducer.font.size", "30");
	properties.setProperty("kaptcha.session.key", "code");
	properties.setProperty("kaptcha.textproducer.char.length", "4");
	properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体");
	Config config = new Config(properties);
	defaultKaptcha.setConfig(config);
	return defaultKaptcha;
}
```

三、配置一个controller
```
@Controller
public class KaptchaController {
	
	@Autowired
	Producer producer;

	@RequestMapping("/kaptcha")
	public void kaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		//图片
		response.setContentType("image/jpeg");
		//过期时间
		response.setDateHeader("Expires", 0);
		//设置没有缓存
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		
		String text = producer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		
		BufferedImage bi = producer.createImage(text);
		
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
	}
}
```

四、登录页面的样式（省略）

五、代码见文件夹2018-03-31\验证码\imgr（注：关于静态资源的获取见下一个内容）

## 静态资源

spring-boot的静态资源通常默认为/resources/static/目录下的内容，所以如果没有另外设置姿态资源路径，可以通过static下的路径获取。例如，/resources/static/layui/layui.css的资源是通过路径/layui/layui.css获取的。
如果需要另外设置姿态资源的路径。可以通过`spring.mvc.static-path-pattern=`来设置。

