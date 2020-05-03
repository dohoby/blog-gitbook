---
title: es实战5-聚合分组查询案例
date: 2019-05-20 13:35:37
tags: [elasticsearch,devOps]
devops: elasticsearch
categories: elasticsearch
---
** {{ title }} ** <Excerpt in index | 首页摘要>


### 需求背景

对在售商品按照sku,平台platform来查询每个sku在对应平台下的最大价格price，sql如下
<!-- more -->

```
SELECT platform,product_code,max(price) from test_listing GROUP BY platform,product_code
```


其他辅助查询
```
SELECT platform,product_code,max(price) from test_listingwhere product_code='SKU466577' GROUP BY platform,product_code

SELECT platform,product_code,price from test_listingwhere  product_code='SKU466577' and platform='lazada' order by price desc

```



### kibana查询

思路：先对sku进行聚合，再添加平台platform的子聚合，最后再统计最大价格price


```

GET /pg.nsp.sp_oa.oa_sp_listing/_doc/_search
{
  "from": 0,
  "size": 0,
  "aggregations": {
    "product_code_agg": {
      "terms": {
        "field": "product_code.keyword",
        "size": 10,
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
      },
      "aggregations": {
        "plaform_agg": {
          "terms": {
            "field": "platform.keyword",
            "size": 10,
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
          },
          "aggregations": {
            "price_max": {
              "max": {
                "field": "price"
              }
            }
          }
        }
      }
    }
  }
}

```


查询结果：

```
{
  "took": 501,
  "timed_out": false,
  "_shards": {
    "total": 5,
    "successful": 5,
    "skipped": 0,
    "failed": 0
  },
  "hits": {
    "total": 10125828,
    "max_score": 0,
    "hits": []
  },
  "aggregations": {
    "product_code_agg": {
      "doc_count_error_upper_bound": 3284,
      "sum_other_doc_count": 10094565,
      "buckets": [
        {
          "key": "SKU466577",
          "doc_count": 5949,
          "plaform_agg": {
            "doc_count_error_upper_bound": 0,
            "sum_other_doc_count": 0,
            "buckets": [
              {
                "key": "lazada",
                "doc_count": 5493,
                "price_max": {
                  "value": 356000
                }
              },
              {
                "key": "shopee",
                "doc_count": 451,
                "price_max": {
                  "value": 304600
                }
              },
              {
                "key": "wish",
                "doc_count": 5,
                "price_max": {
                  "value": 0
                }
              }
            ]
          }
        },
        {
          "key": "SKU564470",
          "doc_count": 4856,
          "plaform_agg": {
            "doc_count_error_upper_bound": 0,
            "sum_other_doc_count": 0,
            "buckets": [
              {
                "key": "lazada",
                "doc_count": 4478,
                "price_max": {
                  "value": 3048000
                }
              },
              {
                "key": "shopee",
                "doc_count": 361,
                "price_max": {
                  "value": 295400
                }
              },
              {
                "key": "wish",
                "doc_count": 9,
                "price_max": {
                  "value": 16
                }
              },
              {
                "key": "Aliexpress",
                "doc_count": 6,
                "price_max": {
                  "value": 28.540000915527344
                }
              },
              {
                "key": "eBay",
                "doc_count": 2,
                "price_max": {
                  "value": 8.489999771118164
                }
              }
            ]
          }
        }
      ]
    }
  }
}
```



### 单元测试

```
 @Test
    public void testAggsQuery5() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        int from = 0;
        int size = 0;//这里设置为0是不用返回hits那部分内容
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);

        TermsAggregationBuilder termsAggregationBuilder1=AggregationBuilders.terms("plaform_agg").field("platform.keyword");
        TermsAggregationBuilder termsAggregationBuilder2=AggregationBuilders.terms("product_code_agg").field("product_code.keyword");
        termsAggregationBuilder2.subAggregation(termsAggregationBuilder1);


        MaxAggregationBuilder maxAggregationBuilder= AggregationBuilders.max("price_max").field("price");
        termsAggregationBuilder1.subAggregation(maxAggregationBuilder);

        searchSourceBuilder.aggregation(termsAggregationBuilder2);



        log.info("aggregations:{}", searchSourceBuilder.toString());


        SearchAggResult searchResult = elasticSearchService.searchMaxAgg(searchSourceBuilder, "pg.nsp.sp_oa.oa_sp_listing", "_doc");
        System.out.println(searchResult.getTotal());
        System.out.println(searchResult.getSearchBuckets());
    }

```

参考：https://blog.csdn.net/sxf_123456/article/details/79482041








