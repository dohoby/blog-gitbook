---
title: sentinel集群流控-ClusterClientAssignConfig
tags: [sentinel]
date: 2021-1-30 21:23:47
categories: sentinel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->


#### ClusterClientAssignConfig客户端分配配置

com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig
```java
public class ClusterClientAssignConfig {
    private String serverHost;
    private Integer serverPort;

```

看下面知道是将ClusterGroupEntity中的ip和port设置到ClusterClientAssignConfig中的，
也就是说这个port也用在tokenServer和tokenClient之间通信，那machineId(例如10.20.11.237@8719)
中@后面的8719又是干嘛用的


```java
    protected Optional<ClusterClientAssignConfig> extractClientAssignment(List<ClusterGroupEntity> groupList) {
        if (groupList.stream().anyMatch(this::machineEqual)) {
            return Optional.empty();
        }
        // Build client assign config from the client set of target server group.
        for (ClusterGroupEntity group : groupList) {
            if (group.getClientSet().contains(getCurrentMachineId())) {
                String ip = group.getIp();
                Integer port = group.getPort();
                return Optional.of(new ClusterClientAssignConfig(ip, port));
            }
        }
        return Optional.empty();
    }
```


[集群流控](https://github.com/alibaba/Sentinel/wiki/%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7)

