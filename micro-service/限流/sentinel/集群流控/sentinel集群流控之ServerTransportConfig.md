---
title: sentinel集群流控-ServerTransportConfig
tags: []
date: 2020-11-25 16:42:37
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->


#### ServerTransportConfig\配置transport.port用途
8719端口配置如下：
```java
iotcard.cloud.sentinel.transport.port=8719
```

对应配置类为com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig

```java
public class ServerTransportConfig {
    public static final int DEFAULT_IDLE_SECONDS = 600;
    private int port;
    private int idleSeconds;
```
调试发现当点击控制台的集群流控，就跳到

![](source/_posts/micro-service/限流/sentinel/集群流控/sentinel集群流控/集群流控-1.png)

com.alibaba.csp.sentinel.cluster.server.command.handler.FetchClusterServerInfoCommandHandler#handle
```java
    public CommandResponse<String> handle(CommandRequest request) {
        JSONObject info = new JSONObject();
        JSONArray connectionGroups = new JSONArray();
        Set<String> namespaceSet = ClusterServerConfigManager.getNamespaceSet();
        Iterator var5 = namespaceSet.iterator();

        while(var5.hasNext()) {
            String namespace = (String)var5.next();
            ConnectionGroup group = ConnectionManager.getOrCreateConnectionGroup(namespace);
            if (group != null) {
                connectionGroups.add(group);
            }
        }

        ServerTransportConfig transportConfig = (new ServerTransportConfig()).setPort(ClusterServerConfigManager.getPort()).setIdleSeconds(ClusterServerConfigManager.getIdleSeconds());
        ServerFlowConfig flowConfig = (new ServerFlowConfig()).setExceedCount(ClusterServerConfigManager.getExceedCount()).setMaxOccupyRatio(ClusterServerConfigManager.getMaxOccupyRatio()).setIntervalMs(ClusterServerConfigManager.getIntervalMs()).setSampleCount(ClusterServerConfigManager.getSampleCount()).setMaxAllowedQps(ClusterServerConfigManager.getMaxAllowedQps());
        JSONArray requestLimitData = this.buildRequestLimitData(namespaceSet);
        info.fluentPut("port", ClusterServerConfigManager.getPort()).fluentPut("connection", connectionGroups).fluentPut("requestLimitData", requestLimitData).fluentPut("transport", transportConfig).fluentPut("flow", flowConfig).fluentPut("namespaceSet", namespaceSet).fluentPut("embedded", ClusterServerConfigManager.isEmbedded());
        info.put("appName", AppNameUtil.getAppName());
        return CommandResponse.ofSuccess(info.toJSONString());
    }
```

分析：由于控制台要推送规则到配置中心或者客户端，则必须要连接到客户端，所以客户端必须提供个端口
才能建立连接，所以有默认个18730端口,所以ServerTransportConfig中的port就是这个端口



![](source/_posts/micro-service/限流/sentinel/集群流控/sentinel集群流控/集群流控-2.png)

服务端配置类：
![](source/_posts/micro-service/限流/sentinel/集群流控/sentinel集群流控/集群流控-3.png)

