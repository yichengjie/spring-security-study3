1. 问题：如果在父项目加上executions上面的那段配置后，普通的子项目（被别的项目依赖的公用模块）在打包时会报错找不到springboot主类
	解决方式：
	  方式1： 父项目中去掉executions，然后在最终打运行包的项目中，配置spring-boot-maven-plugin包括executions部分
	  方式2： 父项目删除spring-boot-maven-plugin，然后在最终打运行包的项目中，配置spring-boot-maven-plugin包括executions部分
