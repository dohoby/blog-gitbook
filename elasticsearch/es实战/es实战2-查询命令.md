---
title: es实战2-查询命令
date: 2018-11-06 13:35:37
tags: [elasticsearch,devOps]
devops: elasticsearch
categories: elasticsearch
---
** {{ title }} ** <Excerpt in index | 首页摘要>
<!-- more -->

#### 、精确查询(词条查询)



#### 、匹配查询




#### 、多个匹配查询






#### 、短语匹配查询


```
GET /pg.nsp.sp_oa.oa_sp_listing/_doc/_search
{
  "from": 0,
  "size": 100,
  "query": {
    "bool": {
      "must": [
        {
          "multi_match": {
            "query": "Lightning Purple",
            "minimum_should_match": "100",
            "fields": [
              "title^1.0",
              "description^1.0",
              "keywords^1.0"
            ],
            "type": "phrase",
            "operator": "and",
            "slop": 0,
            "prefix_length": 0,
            "max_expansions": 50,
            "zero_terms_query": "NONE",
            "auto_generate_synonyms_phrase_query": true,
            "fuzzy_transpositions": true,
            "boost": 1
          }
        }
      ],
      "adjust_pure_negative": true,
      "boost": 1
    }
  }
}
```


```
GET /pg.nsp.sp_oa.oa_sp_listing/_doc/_search
{
  "from": 0,
  "size": 100,
  "query": {
    "bool": {
      "must": [
        {
          "multi_match": {
            "query": "SEA FAIRIES",
            "minimum_should_match": "100",
            "fields": [
              "title^1.0",
              "description^1.0",
              "keywords^1.0"
            ],
            "type": "phrase",
            "operator": "and",
            "slop": 0,
            "prefix_length": 0,
            "max_expansions": 50,
            "zero_terms_query": "NONE",
            "auto_generate_synonyms_phrase_query": true,
            "fuzzy_transpositions": true,
            "boost": 1
          }
        }
      ],
      "adjust_pure_negative": true,
      "boost": 1
    }
  }
}

```

https://blog.csdn.net/dm_vincent/article/details/41941659?utm_source=tuicool





#### 、正则表达式查询

#### 、聚合查询(distinct)
场景：当需要获取某个字段上的所有可用值时，可以使用terms聚合查询完成

第一个size是hits那部分数据的大小，这里聚合不需要hits那部分，  
第二个size是聚合所需要查询的数据  
site.keyword是你想对那个字段进行不同值查询  
group_by_site是随意起的值  

类似于sql的select distinct site, count(*) from sp_oa.oa_sp_listing

```
GET /pg.nsp.sp_oa.oa_sp_listing/_doc/_search
{
  "from": 0,
  "size": 0,
  "aggregations": {
    "group_by_site": {
      "terms": {
        "field": "site.keyword",
        "size": 1000,
        "min_doc_count": 1,
        "shard_min_doc_count": 0,
        "show_term_doc_count_error": false,
        "order": [
          {
            "_count": "desc"
          },
          {
            "_key": "asc"
          }
        ]
      }
    }
  }
}
```

https://www.cnblogs.com/qijiu/p/6876096.html


#### 、聚合数目查询(cardinality)
类似于sql的select count( * ) from (select distinct * from  table)


```

GET /pg.nsp.sp_oa.oa_sp_listing/_doc/_search
{
  "from": 0,
  "size": 0,
  "aggregations": {
    "count_site": {
      "cardinality": {
        "field": "site.keyword"
      }
    }
  }
}


```

结果：

```
{
  "took": 1,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": 16548333,
    "max_score": 0,
    "hits": []
  },
  "aggregations": {
    "count_site": {
      "value": 17
    }
  }
}
```
