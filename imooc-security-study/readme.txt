1. 问题：如果在父项目加上executions上面的那段配置后，普通的子项目（被别的项目依赖的公用模块）在打包时会报错找不到springboot主类
	解决方式：
	  方式1： 父项目中去掉executions，然后在最终打运行包的项目中，配置spring-boot-maven-plugin包括executions部分
	  方式2： 父项目删除spring-boot-maven-plugin，然后在最终打运行包的项目中，配置spring-boot-maven-plugin包括executions部分

2. 作为服务提供商需要知道三件事：
   2.1 哪个应用在请求授权 （你是慕课在请求授权还是百度在请求授权，通过client_id确定）
   2.2 你在请求我的哪个用户给你授权(用户通过用户名密码，或则扫码等获取)
   2.3 你在请求什么授权（all，获取用户信息等，根据scope确定）

3. 社交登录访问地址http://www.pinzhi365.com/qqLogin/callback.do

