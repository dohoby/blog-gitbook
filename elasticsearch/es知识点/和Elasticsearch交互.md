---
title: 和Elasticsearch交互
tags: [Elasticsearch]
date: 2019-07-31 13:49:03
categories: Elasticsearch
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

 Java可以使用 Elasticsearch 内置的两个客户端：
 
#### 节点客户端（Node client）
    节点客户端作为一个非数据节点加入到本地集群中。换句话说，它本身不保存任何数据，但是它知道数据在集群中的哪个节点中，并且可以把请求转发到正确的节点。 
#### 传输客户端（Transport client）
    轻量级的传输客户端可以将请求发送到远程集群。它本身不加入集群，但是它可以将请求转发到集群中的一个节点上。 

两个 Java 客户端都是通过 9300 端口并使用 Elasticsearch 的原生 传输 协议和集群交互。集群中的节点通过端口 9300 彼此通信。如果这个端口没有打开，节点将无法形成一个集群。

#### 客户端连接elasticsearch代码
```
    @Bean("transportClient")
    public TransportClient transportClient() throws UnknownHostException {
        Assert.notEmpty(hosts.hosts, "the host of elastic search cann't be null");

        TransportAddress[] transportAddress = new TransportAddress[hosts.hosts.size()];
        for (int i = 0; i < hosts.hosts.size(); i++) {
            Host host = hosts.hosts.get(i);
            transportAddress[i] = new TransportAddress(InetAddress.getByName(host.getHost()), 9300);
        }

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)//(Settings.EMPTY)
                .addTransportAddresses(transportAddress);

        return client;
    }
```
更多代码参考本站的【springboot2.0集成elasticsearch6】或者本站搜索ElasticSearchConfig

参考：
https://www.elastic.co/guide/cn/elasticsearch/guide/cn/_talking_to_elasticsearch.html
https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/transport-client.html
 
特别注意：本环境使用的elasticsearch为6.4.2，貌似7.x后该方法为过期了，8.x后删除了，若用更高版本，自行在官网查询对应的方法
