---
title: sentinel命名空间namespace分析
tags: [sentinel]
date: 2021-1-30 21:23:47
categories: sentinel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->


#### Namespace注册和获取以及设值

###### appName取值
设值入口  
com.alibaba.csp.sentinel.config.SentinelConfig#resolveAppName  

取值顺序逻辑如下：  
- 环境变量system env中取csp.sentinel.app.name
- property中取csp.sentinel.app.name
- property中取project.name
- 从property中取sun.java.command(正在执行的类)


根据上面的取值，我们可以如下配置启动参数，这个会影响sentinel的namespace
例如启动参数配置：
```java
-Dserver.port=19988  -Dproject.name=ClusterDemoApplication -Dcsp.sentinel.log.use.pid=true -Dcsp.sentinel.app.name=SYS_IOT.flow-sync-sentinel
-Dserver.port=19989  -Dproject.name=ClusterDemoApplication2 -Dcsp.sentinel.log.use.pid=true -Dcsp.sentinel.app.name=SYS_IOT.flow-sync-sentinel
-Dserver.port=19990  -Dproject.name=ClusterDemoApplication3 -Dcsp.sentinel.log.use.pid=true -Dcsp.sentinel.app.name=SYS_IOT.flow-sync-sentinel

```
上面由于设置了-Dcsp.sentinel.app.name=SYS_IOT.flow-sync-sentinel
所以在sentinel-dashbord控制台上左边菜单栏看到的是SYS_IOT.flow-sync-sentinel  
![](source/_posts/micro-service/限流/sentinel/入门/sentinel命名空间namespace分析vice/限流/sentinel/入门/sentinel命名空间namespace分析/sentinel-namespace-1.png)
![](source/_posts/micro-service/限流/sentinel/入门/sentinel命名空间namespace分析vice/限流/sentinel/入门/sentinel命名空间namespace分析/sentinel-namespace-2.png)


另外启动参数配置的，参考类SentinelConfig

###### appName用途
入口：com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer#handleEmbeddedStart


- 嵌入式集群启动
com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer#handleEmbeddedStart
- 配置供应者注册器获取Namespace
com.alibaba.csp.sentinel.cluster.registry.ConfigSupplierRegistry#getNamespaceSupplier
- 配置供应者注册器返回默认Namespace
com.alibaba.csp.sentinel.cluster.registry.ConfigSupplierRegistry#DEFAULT_APP_NAME_SUPPLIER
- 配置供应者注册器实际上返回AppNameUtil.getAppName()
com.alibaba.csp.sentinel.util.AppNameUtil#getAppName
- AppNameUtil.getAppName()里返回的是SentinelConfig#getAppName
com.alibaba.csp.sentinel.config.SentinelConfig#getAppName


#### namespace加载分析
namespace

入口：com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer#handleEmbeddedStart

```java
 private void handleEmbeddedStart() {
        //先从环境变量参数，配置文件属性里，或者启动的类中获取namespace
        String namespace = ConfigSupplierRegistry.getNamespaceSupplier().get();//(1)
        if (StringUtil.isNotEmpty(namespace)) {
            // Mark server global mode as embedded.
            ClusterServerConfigManager.setEmbedded(true);//(2)
            if (!ClusterServerConfigManager.getNamespaceSet().contains(namespace)) {//(3)
                Set<String> namespaceSet = new HashSet<>(ClusterServerConfigManager.getNamespaceSet());
                namespaceSet.add(namespace);
                ClusterServerConfigManager.loadServerNamespaceSet(namespaceSet);//(4) 
            }

            // Register self to connection group.
            ConnectionManager.addConnection(namespace, HostNameUtil.getIp());
        }
    }

```
分析：

- (1)先从环境变量参数，配置文件属性里，或者启动的类中获取namespace
- (2)若获取到namespace不为空，则集群配置管理器ClusterServerConfigManager设置为嵌入式
- (3)若ClusterServerConfigManager中的namespaceSet命名集合不包含namespace，则新建namespaceSet集合，
     新建实例化时添加默认namespace为default
- (4)调用loadServerNamespaceSet加载步骤(1)的namespace  
     这个会从Apollo获取该命名空间最新的配置的  



#### 

```java

```

```java

```
[]()

#### 
```java

```

```java

```
[]()

#### 


```java

```

```java

```
[]()
```




1. 
2. 
3. 
![]()