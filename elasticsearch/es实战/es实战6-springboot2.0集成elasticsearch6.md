---
title: es实战6-springboot2.0集成elasticsearch6
date: 2019-05-20 13:35:37
tags: [elasticsearch,devOps,springboot]
devops: elasticsearch
categories: elasticsearch
---

** {{ title }} ** <Excerpt in index | 首页摘要>
<!-- more -->

### pom.xml

```

        <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>elasticsearch</artifactId>
            <version>6.2.4</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.elasticsearch.client/rest -->
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>rest</artifactId>
            <version>6.0.0-alpha1</version>
        </dependency>
        
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>elasticsearch-rest-high-level-client</artifactId>
            <version>6.2.4</version>
        </dependency>
        
        
        
```

### ElasticSearchConfig

```
package com.tobe.spcommon.common.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.util.List;

@Configuration
public class ElasticSearchConfig {

    @Autowired
    Hosts hosts;


    @Bean("restHighLevelClient")
    public RestHighLevelClient clientFactory() {
        Assert.notEmpty(hosts.hosts, "the host of elastic search cann't be null");

        HttpHost[] httpHosts = new HttpHost[hosts.hosts.size()];
        for (int i = 0; i < hosts.hosts.size(); i++) {
            Host host = hosts.hosts.get(i);
            httpHosts[i] = new HttpHost(host.host, host.port, host.protocol);
        }

        /*RestClientBuilder builder= RestClient.builder(
                new HttpHost("192.168.1.1", 9200, "http"),
                new HttpHost("192.168.1.1", 9201, "http"));*/
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHosts));
        return client;
    }

    //或者@Component替代@Configuration，这里配置了，就不能在启动类再用@EnableConfigurationProperties(ElasticSearchConfig.Hosts.class)
    @Configuration
    @ConfigurationProperties(prefix = "es")
    @Data
    public class Hosts {
        List<Host> hosts;
    }

    @Data
    public static class Host {
        private String host;
        private int port;
        private String protocol;
    }

}

```

### properties

```

#-----------------------
# ES new config
#-----------------------
es.hosts[0].host=192.168.1.1
es.hosts[0].port=9200
es.hosts[0].protocol=http
```


### ESTest


```
package com.tobe.spcommon.es;

import com.tobe.spcommon.common.config.ElasticSearchConfig;
import com.tobe.spcommon.common.service.ElasticSearchService;
import com.tobe.spcommon.dao.InfringeWordRepository;
import com.tobe.spcommon.pojo.view.req.infringeword.InfringeWordAddVo;
import com.tobe.spcommon.pojo.view.req.infringeword.InfringeWordEsInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Wuxi
 * @date 2018/5/14
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ESTest {
    @Resource
    ElasticSearchService elasticSearchService;

    @Test
    public void testBulkInsert() {
        List<InfringeWordEsInfo> infringeWordEsInfos = new ArrayList<>();
        InfringeWordEsInfo infringeWordEsInfo = new InfringeWordEsInfo();
        infringeWordEsInfo.setId("9");
        infringeWordEsInfo.setName("苹果9");
        infringeWordEsInfo.setFullCategory(1);
        infringeWordEsInfo.setPlatformNo(1);
        log.info(infringeWordEsInfo.toString());

        InfringeWordEsInfo infringeWordEsInfo2 = new InfringeWordEsInfo();
        infringeWordEsInfo2.setId("10");
        infringeWordEsInfo2.setName("苹果10");
        infringeWordEsInfo2.setFullCategory(1);
        infringeWordEsInfo2.setPlatformNo(1);
        log.info(infringeWordEsInfo2.toString());

        InfringeWordEsInfo infringeWordEsInfo3 = new InfringeWordEsInfo();
        infringeWordEsInfo3.setId("3");
        infringeWordEsInfo3.setName("苹果3");
        infringeWordEsInfo3.setFullCategory(1);
        infringeWordEsInfo3.setPlatformNo(0);
        log.info(infringeWordEsInfo3.toString());

        InfringeWordEsInfo infringeWordEsInfo4 = new InfringeWordEsInfo();
        infringeWordEsInfo4.setId("4");
        infringeWordEsInfo4.setName("苹果4");
        infringeWordEsInfo4.setFullCategory(1);
        infringeWordEsInfo4.setPlatformNo(0);
        log.info(infringeWordEsInfo4.toString());

        InfringeWordEsInfo infringeWordEsInfo19 = new InfringeWordEsInfo();
        infringeWordEsInfo19.setId("19");
        infringeWordEsInfo19.setName("苹果19");
        infringeWordEsInfo19.setFullCategory(1);
        infringeWordEsInfo19.setPlatformNo(19);
        log.info(infringeWordEsInfo19.toString());


        infringeWordEsInfos.add(infringeWordEsInfo);
        infringeWordEsInfos.add(infringeWordEsInfo2);
        infringeWordEsInfos.add(infringeWordEsInfo3);
        infringeWordEsInfos.add(infringeWordEsInfo4);
        infringeWordEsInfos.add(infringeWordEsInfo19);

        //infringeWordEsInfo3 infringeWordEsInfo4不会被更新
        try {
            BulkResponse bulkItemResponses = elasticSearchService.bulkInsert(infringeWordEsInfos);
            log.info("{}",bulkItemResponses);
        } catch (Exception e) {
            e.printStackTrace();
        }

       // elasticSearchService.update(infringeWordEsInfo3);

    }

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

    @Test
    public void test() throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.1.1", 9200, "http"),
                        new HttpHost("192.168.1.1", 9201, "http")));


        String queryString = "apple";//搜索关键字

        IndexRequest request = new IndexRequest(
                "posts",
                "doc",
                "3");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);

        try {
            //RequestOptions.DEFAULT.getHeaders()
            IndexResponse response = client.index(request);//new RequestOptions.DEFAULT.ReqHeader());new BasicHeader()
            log.info("------------------响应：{}", response);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                log.error("", e);
            }


        } finally {
            client.close();
        }


    }
}
```
### ElasticSearchServiceImpl


```
package com.tobe.spcommon.common.service.impl;

import com.tobe.spcommon.common.exception.SearchException;
import com.tobe.spcommon.common.service.ElasticSearchService;
import com.tobe.spcommon.common.utils.ElasticSearchUtil;
import com.tobe.spcommon.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ElasticSearchServiceImpl implements ElasticSearchService {

    @Resource
    RestHighLevelClient restHighLevelClient;


    public <T> IndexResponse insert(T value) {
        log.info("请求index：{}", value);

        try {
            ElasticSearchUtil.EsDataInfo esDataInfo = ElasticSearchUtil.assertAndReturnEsDataInfo(value);

            IndexRequest request = new IndexRequest(
                    esDataInfo.getIndex(),
                    esDataInfo.getType(),
                    esDataInfo.getId());

            request.source(JsonUtil.stringify(value), XContentType.JSON);

            IndexResponse response = restHighLevelClient.index(request);
            log.info("响应index：{}", response);
            return response;
        } catch (IOException | IllegalAccessException e) {
            log.error("", e);
            throw new SearchException(e);
        }
    }

    public <T> DeleteResponse delete(T value) {
        log.info("请求delete：{}", value);

        try {
            ElasticSearchUtil.EsDataInfo esDataInfo = ElasticSearchUtil.assertAndReturnEsDataInfo(value);

            DeleteRequest request = new DeleteRequest(
                    esDataInfo.getIndex(),
                    esDataInfo.getType(),
                    esDataInfo.getId());

            DeleteResponse response = restHighLevelClient.delete(request);
            log.info("响应delete：{}", response);
            return response;
        } catch (IOException | IllegalAccessException e) {
            log.error("", e);
            throw new SearchException(e);
        }
    }

    public <T> UpdateResponse update(T value) {
        log.info("请求update：{}", value);

        try {
            ElasticSearchUtil.EsDataInfo esDataInfo = ElasticSearchUtil.assertAndReturnEsDataInfo(value);

            UpdateRequest request = new UpdateRequest(
                    esDataInfo.getIndex(),
                    esDataInfo.getType(),
                    esDataInfo.getId());

            request.doc(JsonUtil.stringify(value), XContentType.JSON);

            UpdateResponse response = restHighLevelClient.update(request);
            log.info("响应update：{}", response);
            return response;
        } catch (IOException | IllegalAccessException e) {
            log.error("", e);
            throw new SearchException(e);
        }
    }

    public <T> GetResponse get(T t) {
        log.info("请求get：{}", t);

        try {
            ElasticSearchUtil.EsDataInfo esDataInfo = ElasticSearchUtil.assertAndReturnEsDataInfo(t);

            GetRequest request = new GetRequest(
                    esDataInfo.getIndex(),
                    esDataInfo.getType(),
                    esDataInfo.getId());

            GetResponse response = restHighLevelClient.get(request);
            log.info("响应delete：{}", response);
            return response;
        } catch (IOException | IllegalAccessException e) {
            log.error("", e);
            throw new SearchException(e);
        }
    }

    public <T> MultiGetResponse multiGet(List<T> values) {
        log.info("请求multiGet：{}", values);

        try {
            MultiGetRequest request = new MultiGetRequest();

            for (T t : values) {
                ElasticSearchUtil.EsDataInfo esDataInfo = ElasticSearchUtil.assertAndReturnEsDataInfo(t);
                request.add(esDataInfo.getIndex(), esDataInfo.getType(), esDataInfo.getId());
            }

            MultiGetResponse response = restHighLevelClient.multiGet(request);
            log.info("响应delete：{}", response);
            return response;
        } catch (IOException | IllegalAccessException e) {
            log.error("", e);
            throw new SearchException(e);
        }
    }

    public <T> BulkResponse bulkInsert(List<T> values) {
        log.info("请求bulkInsert：{}", values);

        BulkRequest request = new BulkRequest();


        try {
            for (Object object : values) {
                ElasticSearchUtil.EsDataInfo esDataInfo = ElasticSearchUtil.assertAndReturnEsDataInfo(object);
                IndexRequest indexRequest = new IndexRequest(
                        esDataInfo.getIndex(),
                        esDataInfo.getType(),
                        esDataInfo.getId());
                indexRequest.source(JsonUtil.stringify(object), XContentType.JSON);
                request.add(indexRequest);
            }

            BulkResponse response = restHighLevelClient.bulk(request);
            log.info("响应bulkInsert：{}", response);
            return response;
        } catch (IOException | IllegalAccessException e) {
            log.error("", e);
            throw new SearchException(e);
        }
    }

    public <T> SearchResponse search(List<T> values) {
        log.info("请求multiGet：{}", values);

        try {
            SearchRequest request = new SearchRequest();
            SearchSourceBuilder source = new SearchSourceBuilder();
            request.source(source);

            SearchResponse response = restHighLevelClient.search(request);
            log.info("响应delete：{}", response);
            return response;
        } catch (IOException e) {
            log.error("", e);
            throw new SearchException(e);
        }
    }
}

```
