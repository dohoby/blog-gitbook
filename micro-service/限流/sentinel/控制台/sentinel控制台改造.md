---
title: sentinel控制台改造
tags: []
date: 2021-02-22 11:11:43
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1、pomx.ml增加redis依赖引用
```java   
     <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-data-redis</artifactId>
         <version>${spring.boot.version}</version>
     </dependency>
```


#### 2、新增类   
     FlowRuleRedisProvider：自定义实现基于redis的拉取规则
     
     FlowRuleRedisPublisher：自定义实现流控配置推送规则
     
     RedisConfig：redis配置类
     
     RuleConstants：自定义常量类
     
     说明：本次个人新增的类均在包com.alibaba.csp.sentinel.dashboard.rule.redis下面

上面具体的类内容看项目

#### 3、新增redis和流控规则配置

```java  
    # Redis\u6570\u636E\u5E93\u7D22\u5F15\uFF08\u9ED8\u8BA4\u4E3A0\uFF09
    spring.redis.database=2 
    # Redis\u670D\u52A1\u5668\u5730\u5740
    spring.redis.host=10.20.100.167
    # Redis\u670D\u52A1\u5668\u8FDE\u63A5\u7AEF\u53E3
    spring.redis.port=7003
    # \u8FDE\u63A5\u8D85\u65F6\u65F6\u95F4\uFF08\u6BEB\u79D2\uFF09
    spring.redis.timeout=30000
    
    ##\u7AEF\u53E3\u53F7
    server.port=9090
    
    #\u6D41\u63A7\u89C4\u5219key\u524D\u7F00
    rule.flow=cluster-flow-rules-
    rule.channel=sentinel-channel-
```
1、 
上面redis配置可以配置在Apollo中  
2、  
 rule.flow 是用来获取集群流控规则的key前缀  
 rule.channel 是发送流控规则变化消息到redis的channel前缀，    
 这2个在上面新增的类FlowRuleRedisProvider和FlowRuleRedisPublisher中会利用到  



#### 4、修改com.alibaba.csp.sentinel.dashboard.controller.v2.FlowControllerV2类 替换为自定义拉取和推送规则实现类

```java     
     @Autowired
     @Qualifier("flowRuleRedisProvider")
     private DynamicRuleProvider<List<FlowRuleEntity>> ruleProvider;
     @Autowired
     @Qualifier("flowRuleRedisPublisher")
     private DynamicRulePublisher<List<FlowRuleEntity>> rulePublisher;
```

#### 5、修改src\main\webapp\resources\app\scripts\directives\sidebar\sidebar.html将dashboard.flowV1替换为dashboard.flow
修改前源码

```java
<li ui-sref-active="active" ng-if="!entry.isGateway">
            <a ui-sref="dashboard.flowV1({app: entry.app})">
              <i class="glyphicon glyphicon-filter"></i>&nbsp;&nbsp;流控规则</a>
          </li>
```

修改后源码
```java
 <li ui-sref-active="active" ng-if="!entry.isGateway">
            <a ui-sref="dashboard.flow({app: entry.app})">
              <i class="glyphicon glyphicon-filter"></i>&nbsp;&nbsp;流控规则</a>
          </li>
```


改造参考：

[Sentinel 控制台（集群流控管理）](https://github.com/alibaba/Sentinel/wiki/Sentinel-%E6%8E%A7%E5%88%B6%E5%8F%B0%EF%BC%88%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7%E7%AE%A1%E7%90%86%EF%BC%89#%E8%A7%84%E5%88%99%E9%85%8D%E7%BD%AE)

[https://gitee.com/lvlaotou/sentinel-dashboard-redis/](https://gitee.com/lvlaotou/sentinel-dashboard-redis/)



#### 编译sentinel-dashbord遇到的问题

######问题1：  

```java
Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test
```

加上下面插件解决：
```java
    <plugin>  
        <groupId>org.apache.maven.plugins</groupId>  
        <artifactId>maven-surefire-plugin</artifactId>  
        <version>2.4.2</version>  
        <configuration>  
          <skipTests>true</skipTests>  
        </configuration>  
    </plugin>  

```
参考：
[https://www.cnblogs.com/lxcy/p/8279899.html](https://www.cnblogs.com/lxcy/p/8279899.html)






######问题2：  


```java
Error:java: Compilation failed: internal java compiler error 
```

解决:
查看java编译器版本,发现是1.5的，将本项目对应的版本改为1.8
![](sentinel控制台改造/sentinel-dashbord-1.jpg)



```java

```

