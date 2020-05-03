---
title: es实战3-综合查询案例
date: 2019-05-20 13:35:37
tags: [elasticsearch,devOps]
devops: elasticsearch
categories: elasticsearch
---
** {{ title }} ** <Excerpt in index | 首页摘要>

### 1、产品侵权查询
背景：将在售的侵权的sku（某些平台下的）搜索出来
<!-- more -->

1、短语匹配查询  
2、词条精确查询  
3、in查询

```
GET /pg.nsp.sp_oa.oa_sp_listing/_doc/_search
{
  "from": 0,
  "size": 10000,
  "timeout": "10m",
  "query": {
    "bool": {
      "must": [
        {
          "match_phrase": {
            "product_code": {
              "query": "SKU778231",
              "slop": 0,
              "boost": 1
            }
          }
        },
        {
          "term": {
            "sales_status": {
              "value": "online",
              "boost": 1
            }
          }
        },
        {
          "terms": {
            "platform": [
              "ebay",
              "amazon",
              "aliexpress",
              "newegg",
              "wish",
              "linio"
            ],
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

### 2、侵权品牌查询

1、多字段短语匹配查询  
   短语可以多个字符串组合在一起，而且位置固定才会搜索出来，比如Abercrombie & Fitch，特别适合短语查询

2、词条精确查询  
3、in查询


```
{
  "from": 0,
  "size": 10000,
  "timeout": "10m",
  "query": {
    "bool": {
      "must": [
        {
          "multi_match": {
            "query": "Abercrombie & Fitch",
            "fields": [
              "description^1.0",
              "keywords^1.0",
              "title^1.0"
            ],
            "type": "phrase",
            "operator": "AND",
            "slop": 0,
            "prefix_length": 0,
            "max_expansions": 50,
            "zero_terms_query": "NONE",
            "auto_generate_synonyms_phrase_query": true,
            "fuzzy_transpositions": true,
            "boost": 1
          }
        },
        {
          "terms": {
            "platform": [
              "Joom",
              "NewChic",
              "11Street",
              "Linio",
              "Lazada",
              "JD",
              "Yilinker",
              "eBay",
              "WholeSale"
            ],
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
  "size": 10000,
  "timeout": "20m",
  "query": {
    "bool": {
      "must": [
        {
          "multi_match": {
            "query": "segway",
            "fields": [
              "description^1.0",
              "title^1.0"
            ],
            "type": "phrase",
            "operator": "AND",
            "slop": 0,
            "prefix_length": 0,
            "max_expansions": 50,
            "zero_terms_query": "NONE",
            "auto_generate_synonyms_phrase_query": true,
            "fuzzy_transpositions": true,
            "boost": 1
          }
        },
        {
          "term": {
            "sales_status": {
              "value": "online",
              "boost": 1
            }
          }
        },
        {
          "term": {
            "platform": {
              "value": "wish",
              "boost": 1
            }
          }
        },
        {
          "term": {
            "product_code": {
              "value": "sku894685",
              "boost": 1
            }
          }
        }
      ],
      "adjust_pure_negative": true,
      "boost": 1
    }
  }
}

```



### 3、es列表搜索查询



```



```
