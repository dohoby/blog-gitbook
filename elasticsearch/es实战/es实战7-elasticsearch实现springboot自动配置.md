---
title: es实战7-elasticsearch实现springboot自动配置
tags: [elasticsearch,springboot,devOps]
date: 2019-11-04 19:23:27
categories: elasticsearch
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 1、新建ElasticSearchProperties
```
package com.basic.elasticsearch.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "elastic")
public class ElasticSearchProperties {
    List<Host> hosts;

}

```

```
package com.basic.elasticsearch.autoconfigure;

import lombok.Data;

@Data
public  class Host {
    private String host;
    private int port;
    private String protocol;
}
```

### 2、新建ElasticSearchAutoConfiguration自动配置类

```
package com.basic.elasticsearch.autoconfigure;

import com.basic.elasticsearch.service.ElasticSearchService;
import com.basic.elasticsearch.service.ElasticSearchTransportClientService;
import com.basic.elasticsearch.service.impl.ElasticSearchServiceImpl;
import com.basic.elasticsearch.service.impl.ElasticSearchTransportClientServiceImpl;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * https://www.cnblogs.com/xyzmy/p/9776037.html
 */
@Configuration
@EnableConfigurationProperties(ElasticSearchProperties.class)
@ConditionalOnClass({ElasticSearchService.class, ElasticSearchTransportClientService.class})
@ConditionalOnProperty(prefix = "elastic", value = "enabled", matchIfMissing = false)
//判断配置文件中是否存在elastic开头的配置,如果不存在配置，如果配置matchIfMissing为true,即使我们配置文件中配置elastic.enabled=true，也是默认生效的；
// matchIfMissing必须设置为false，配置文件不存在时则不自动配置,若配置文件存在，则再判断elastic.enabled为true才会自动配置
public class ElasticSearchAutoConfiguration {
    @Autowired
    ElasticSearchProperties elasticSearchProperties;

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient() {
        Assert.notEmpty(elasticSearchProperties.hosts, "the host of elastic search cann't be null");

        HttpHost[] httpHosts = new HttpHost[elasticSearchProperties.hosts.size()];
        for (int i = 0; i < elasticSearchProperties.hosts.size(); i++) {
            Host host = elasticSearchProperties.hosts.get(i);
            httpHosts[i] = new HttpHost(host.getHost(), host.getPort(), host.getProtocol());
        }

        /*RestClientBuilder builder= RestClient.builder(
                new HttpHost("192.168.1.82", 9200, "http"),
                new HttpHost("192.168.1.82", 9201, "http"));*/
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHosts));
        //client.wait(300000);

        return client;
    }

    //https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/transport-client.html
    @Bean
    @ConditionalOnMissingBean
    public TransportClient transportClient() throws UnknownHostException {
        Assert.notEmpty(elasticSearchProperties.hosts, "the host of elastic search cann't be null");

        TransportAddress[] transportAddress = new TransportAddress[elasticSearchProperties.hosts.size()];
        for (int i = 0; i < elasticSearchProperties.hosts.size(); i++) {
            Host host = elasticSearchProperties.hosts.get(i);
            transportAddress[i] = new TransportAddress(InetAddress.getByName(host.getHost()), 9300);
        }

        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)//(Settings.EMPTY)
                .addTransportAddresses(transportAddress);

        return client;
    }

    @Bean
    @ConditionalOnMissingBean(ElasticSearchService.class)
    public ElasticSearchService elasticSearchService() {
        ElasticSearchService elasticSearchService = new ElasticSearchServiceImpl(restHighLevelClient());

        return elasticSearchService;
    }

    @Bean
    @ConditionalOnMissingBean(ElasticSearchTransportClientService.class)
    public ElasticSearchTransportClientService elasticSearchTransportClientService() throws UnknownHostException {
        ElasticSearchTransportClientService elasticSearchTransportClientService = new ElasticSearchTransportClientServiceImpl(transportClient());

        return elasticSearchTransportClientService;
    }
}

```

### 3、在resource/METE-INF文件夹下新建spring.factories文件
文件内容如下
```
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.basic.elasticsearch.autoconfigure.ElasticSearchAutoConfiguration

```

### 4、用法

1. 引入pom依赖(注意排除掉一些不要的依赖)
```
        <dependency>
            <groupId>com.basic.search</groupId>
            <artifactId>elasitcsearch-starter</artifactId>
            <version>1.0.0</version>
            <exclusions>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-log4j12</artifactId>
                </exclusion>

                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```

2. 新建application-es.properties  

```
# ES new config
elastic.hosts[0].host=192.168.1.146
elastic.hosts[0].port=9200
elastic.hosts[0].protocol=http
elastic.enabled=true
```

3. 测试  

```
    @Test
    public void testInsert() {
        InfringeWordEsInfo infringeWordEsInfo = new InfringeWordEsInfo();
        infringeWordEsInfo.setId("6");
        infringeWordEsInfo.setName("苹果2");
        infringeWordEsInfo.setFullCategory(1);
        log.info(infringeWordEsInfo.toString());
        //infringeWordRepository.save(infringeWordEsInfo);


        try {
            elasticSearchService.insert(infringeWordEsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
```

### 4、另一种引入方式EnableElasticSearch

1. 新建EnableElasticSearch.java  

```
package com.basic.elasticsearch.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ElasticSearchAutoConfiguration.class})
public @interface EnableElasticSearch {
}

```

2. 引入  

```
@EnableElasticSearch

```