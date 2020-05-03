---
title: es实战1-常见命令
date: 2018-11-06 13:35:37
tags: [elasticsearch,devOps]
devops: elasticsearch
categories: elasticsearch
---
** {{ title }} ** <Excerpt in index | 首页摘要>
<!-- more -->

####  1、增加索引

```
curl -XPUT '192.168.1.1:9200/twitter/tweet/1?pretty' -H 'Content-Type: application/json' -d'{  "platformNo": 19,"user" : "kimchy","post_date" : "2009-11-15T14:12:12","message" : "trying out Elasticsearch"}'

```

#### 2、删除索引

```
curl -XDELETE '192.168.1.1:9200/pg.nsp.192.168.1.1mon.sp_on_sale_goods'

```

#### 3、更新索引
重复的自动覆盖
```
curl -XPUT '192.168.1.1:9200/pg.nsp1/tweet/1?pretty' -H 'Content-Type: application/json' -d'{  "platformNo": 19,"reason" : "苹果","post_date" : "2009-11-15T14:12:12","message" : "trying out Elasticsearch"}'

```


#### 4、查询索引


```
curl -XGET '192.168.1.1:9200/posts/doc/1'

```


#### 5、查看集群健康

```
curl -XGET '192.168.1.146:9200/_cat/health?v&pretty'
```


#### 6、查看索引

```
curl -XGET '192.168.1.1:9200/_cat/indices?v&pretty'
```

#### 7、查看process

```
 curl http://192.168.1.146:9200/_nodes/process?pretty 

```

#### 8、查看_settings

```
 curl -XGET '192.168.1.146:9200/pg.nsp.sp_oa.oa_sp_listing/_settings?pretty'
```

#### 9、设置_settings（max_result_window）

```
curl -XPUT '192.168.1.146:9200/pg.nsp.sp_oa.oa_sp_listing/_settings?pretty' -H 'Content-Type:application/json' -d'{ "index" : { "max_result_window" : 100000001}}'

```

#### 10、查看_mapping

```
curl -XGET '192.168.1.146:9200/pg.nsp.sp_oa.oa_sp_listing/_mapping?pretty'

```
#### 11、设置_mapping

```

```


备注：若要在windows下的cmder下测试，改为双引号


```
curl -XPUT "192.168.1.1:9200/twitter/tweet/1?pretty" -H "Content-Type: application/json" -d"{  \"platformNo\": 19,\"user\" : \"kimchy\",\"post_date\" : \"2009-11-15T14:12:12\",\"message\" : \"trying out Elasticsearch\"}"
```


```

{
  "_index" : "twitter",
  "_type" : "tweet",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 0,
  "_primary_term" : 1
}

```
