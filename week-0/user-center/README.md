### 小马哥训练营课堂作业

#### 第二周：分支week2
##### 内容：
+ 通过课堂上的简易版依赖注入和依赖查找，实现用户注册功能
+ 通过 UserService 实现用户注册注册用户需要校验
+ Id：必须大于 0 的整数；密码：6-32 位；电话号码: 采用中国大陆方式（11 位校验）

#### 第三周：分支week3
##### 内容：
###### 需求1：
+ 整合Jolokia，实现一个自定义 JMX MBean，通过 Jolokia 做 Servlet 代理

###### 需求2；
+ 拓展org.eclipse.microprofile.config.spi.ConfigSource实现，包括OS环境变量，本地配置文件
+ 拓展org.eclipse.microprofile.config.spi.Converter实现，提供String类型到简单类
+ 通过org.eclipse.microprofile.config.Config读取当前应用名称“application.name”

###### 一些说明
+ 本次作业测试入口均在 TestDBConnectionInitListener#contextInitialized
+ 需求1：运行后，输入地址：http://localhost:8080/jolokia/read/org.rolkevin.user.management.MBeanDemo:type=MBeanDemo
+ 需求2-1：本地配置文件在模块user-web/src/main/resources/META-INF/localconfig.properties
+ 需求2-2：Converter实现，只实现了String类型转换
+ 需求2-3：当前应用名称打印在Server运行日志中，关键词：本地配置-application.name


#### 第四周：分支week4
##### 内容：
###### 需求1：完善 my dependency-injection 模块
+ 脱离 web.xml 配置实现 ComponentContext 自动初始化
+ 使用独立模块并且能够在 user-web 中运行成功
###### 需求2：完善 my-configuration 模块
+ Config 对象如何能被 my-web-mvc 使用
+ 可能在 ServletContext 获取如何通过 ThreadLocal 获取

###### week4的一些说明
+ 需求1：在ApplicationServletContainerInitializer#onStartup()中，通过SPI方式，加载ApplicationContainerInitializer的实现，
回调各自的onStartUp()方法，以实现脱离web.xml配置
+ 需求2：在配置ServletConfigInitializer初始化最后，通过setAttribute方式将配置提供者DefaultConfigProviderResolver存入，
后续可通过getAttribute方式获取；测试入口：TestDBConnectionInitListener#contextInitialized()#testConfigProvider()；运行成功时会有相应日志输出



#### 第五周：分支week5

##### 内容：

###### 需求1：修复本程序 org.geektimes.reactive.streams 包下

###### 需求2：继续完善 my-rest-client POST 方法

###### week5的一些说明

- 需求2：post的实现在HttpPostInvocation#invoke()

​       需要先启动/部署user-web，后执行RestClientDemo#main()

- 需求1：reactive-messaging模块，test目录下，`DefaultSubscriber#onNext()`修改了一点内容



