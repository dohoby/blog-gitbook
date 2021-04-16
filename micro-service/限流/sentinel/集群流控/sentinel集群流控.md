---
title: sentinel集群流控
tags: [sentinel]
date: 2021-1-30 21:23:47
categories: sentinel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 

若在生产环境使用集群限流，管控端还需要关注以下的问题：

Token Server 自动管理（分配/选举 Token Server）
Token Server 高可用，在某个 server 不可用时自动 failover 到其它机器


如何实现心跳检测

如何发放令牌和获取令牌



![]()
