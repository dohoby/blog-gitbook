---
title: springboot排除数据源自动装配失效问题
tags: [springboot,自动装配,问题]
date: 2020-09-10 19:25:32
categories: springboot
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 1、问题描述

当springboot项目不需要连接数据库，当properties或yml文件没有配spring.datasource.url等数据库连接信息时，  
由于springboot会自动装配，将数据源自动加载进来， 所以得把数据源自动装配的去掉，否则会报错

```
2020-09-10 19:35:47.810  INFO 11356 --- [           main] com.fzs.iotcard.FzsIotCardApplication    : Starting FzsIotCardApplication on LAPTOP-6VJBADD9 with PID 11356 (D:\idea-workspace3\lqx-project-demo-github\fzs_iot_card2\target\classes started by hoby in D:\idea-workspace3\lqx-project-demo-github)
2020-09-10 19:35:47.815  INFO 11356 --- [           main] com.fzs.iotcard.FzsIotCardApplication    : The following profiles are active: local
2020-09-10 19:35:56.373  WARN 11356 --- [           main] o.m.s.mapper.ClassPathMapperScanner      : No MyBatis mapper was found in '[com.fzs.iotcard]' package. Please check your configuration.
2020-09-10 19:35:56.484  INFO 11356 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Multiple Spring Data modules found, entering strict repository configuration mode!
2020-09-10 19:35:56.486  INFO 11356 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data repositories in DEFAULT mode.
2020-09-10 19:35:56.536  INFO 11356 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 19ms. Found 0 repository interfaces.
2020-09-10 19:35:57.018  INFO 11356 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration' of type [org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration$$EnhancerBySpringCGLIB$$dfe19409] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
2020-09-10 19:35:57.615  INFO 11356 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2020-09-10 19:35:57.658  INFO 11356 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-09-10 19:35:57.658  INFO 11356 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.21]
2020-09-10 19:35:57.874  INFO 11356 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-09-10 19:35:57.874  INFO 11356 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 6290 ms
2020-09-10 19:35:58.394  INFO 11356 --- [           main] pertySourcedRequestMappingHandlerMapping : Mapped URL path [/v2/api-docs] onto method [public org.springframework.http.ResponseEntity<springfox.documentation.spring.web.json.Json> springfox.documentation.swagger2.web.Swagger2Controller.getDocumentation(java.lang.String,javax.servlet.http.HttpServletRequest)]
2020-09-10 19:35:58.554  INFO 11356 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-09-10 19:35:58.735  INFO 11356 --- [           main] c.a.d.s.b.a.DruidDataSourceAutoConfigure : Init DruidDataSource
2020-09-10 19:35:58.820  WARN 11356 --- [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class path resource [com/alibaba/druid/spring/boot/autoconfigure/DruidDataSourceAutoConfigure.class]: Invocation of init method failed; nested exception is org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException: Failed to determine a suitable driver class
2020-09-10 19:35:58.821  INFO 11356 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
2020-09-10 19:35:58.824  INFO 11356 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2020-09-10 19:35:58.919  INFO 11356 --- [           main] ConditionEvaluationReportLoggingListener : 

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2020-09-10 19:35:58.923 ERROR 11356 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.

Reason: Failed to determine a suitable driver class


Action:

Consider the following:
	If you want an embedded database (H2, HSQL or Derby), please put it on the classpath.
	If you have database settings to be loaded from a particular profile you may need to activate it (no profiles are currently active).


Process finished with exit code 1

```

### 2、问题分析
 
参考了网上把下面DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class  
进行了排除
```
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class)
```
但是排除掉后还是报相同的错，网上搜索了都不行    
然后自己仔细看打印的日志，发现有行

```
2020-09-10 19:35:58.735  INFO 11356 --- [           main] c.a.d.s.b.a.DruidDataSourceAutoConfigure : Init DruidDataSource
```
然后网上一搜DruidDataSourceAutoConfigure发现springboot也会默认装置druid的数据源，所以得把这个也排除掉

DruidDataSourceAutoConfigure源码如下:
```
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({DruidStatProperties.class, DataSourceProperties.class})
@Import({DruidSpringAopConfiguration.class,
    DruidStatViewServletConfiguration.class,
    DruidWebStatFilterConfiguration.class,
    DruidFilterConfiguration.class})
public class DruidDataSourceAutoConfigure {

    private static final Logger LOGGER = LoggerFactory.getLogger(DruidDataSourceAutoConfigure.class);

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public DataSource dataSource() {
        LOGGER.info("Init DruidDataSource");
        return new DruidDataSourceWrapper();
    }
}
```
当代码中缺失不配置DataSource就会默认创建一个DataSource，具体又是创建DruidDataSourceWrapper，  
而DruidDataSourceWrapper定义如下：

```
@ConfigurationProperties("spring.datasource.druid")
class DruidDataSourceWrapper extends DruidDataSource implements InitializingBean {
```
看到这里是依赖了spring.datasource.druid相关的druid配置，所以得把DruidDataSourceAutoConfigure也排除掉




### 3、问题解决
同时把DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DruidDataSourceAutoConfigure.class 这3个排除掉即可

```
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@EnableSwagger2
public class FzsIotCardApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FzsIotCardApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
```
或者把pom中的druid-spring-boot-starter去掉，DruidDataSourceAutoConfigure是存在这个包里的
```
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid-spring-boot-starter</artifactId>
            <version>${druid.starter.version}</version>
        </dependency>
```

参考：
[https://blog.csdn.net/superyu1992/article/details/80336928](https://blog.csdn.net/superyu1992/article/details/80336928)