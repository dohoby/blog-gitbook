---
title: es实战4-java常见查询实例
date: 2019-05-20 13:35:37
tags: [elasticsearch,devOps]
devops: elasticsearch
categories: elasticsearch
---
** {{ title }} ** <Excerpt in index | 首页摘要>
<!-- more -->

### 查询


```

SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
searchSourceBuilder.from(from);
searchSourceBuilder.size(size);


searchSourceBuilder.query(boolQueryBuilder);

//超时时间
searchSourceBuilder.timeout(new TimeValue(10, TimeUnit.MINUTES));
        
SearchResult searchResult = elasticSearchService.search(SpListingEsInfo.class, searchSourceBuilder);



```


### 布尔查询

```

BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

searchSourceBuilder.query(boolQueryBuilder);



```



### 词条精确查询

```
TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("sales_status", "online");
boolQueryBuilder.must(termQueryBuilder);
                
                
```

### 词条精确查询（in范围查询）
类似sql的in查询

```
List<String> countryShortCodes, List<String> platformNames

if (!platformNames.isEmpty()) {
    TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("platform", platformNames);
    boolQueryBuilder.must(termsQueryBuilder);
}

if (!countryShortCodes.isEmpty()) {
    TermsQueryBuilder termsQueryBuilder2 = QueryBuilders.termsQuery("site", countryShortCodes);
    boolQueryBuilder.must(termsQueryBuilder2);
}
                
                
```

### 短语查询(多字符串查询)
```
MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("product_code", productInfringe.getProductCode());
boolQueryBuilder.must(matchPhraseQueryBuilder);

```


### 多字段短语匹配查询(多字段多字符串查询)

```


MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(infringeBrand.getName(), "title", "description", "keywords").
        operator(Operator.AND).type(MultiMatchQueryBuilder.Type.PHRASE);
boolQueryBuilder.must(multiMatchQueryBuilder);
                
                
```

### 正则查询

```

BoolQueryBuilder boolQueryBuilder2 = QueryBuilders.boolQuery();
RegexpQueryBuilder regexpQueryBuilder = new RegexpQueryBuilder("description.keyword", ".*" + spListingPageQueryVo.getSearchText().trim() + ".*");
boolQueryBuilder2.should(regexpQueryBuilder);
RegexpQueryBuilder regexpQueryBuilder2 = new RegexpQueryBuilder("title.keyword", ".*" + spListingPageQueryVo.getSearchText().trim() + ".*");
boolQueryBuilder2.should(regexpQueryBuilder2);
RegexpQueryBuilder regexpQueryBuilder3 = new RegexpQueryBuilder("keywords.keyword", ".*" + spListingPageQueryVo.getSearchText().trim() + ".*");

boolQueryBuilder2.should(regexpQueryBuilder3);
boolQueryBuilder2.minimumShouldMatch(1);

boolQueryBuilder.must(boolQueryBuilder2);



```




### 排序

```
if (!StringUtils.isEmpty(spListingPageQueryVo.getSortKey())) {
    FieldSortBuilder fieldSortBuilder = new FieldSortBuilder(spListingPageQueryVo.getSortKey()+".keyword");
    if (spListingPageQueryVo.getSortType()) {//TRUE降序
        fieldSortBuilder.order(SortOrder.DESC);
    } else {
        fieldSortBuilder.order(SortOrder.ASC);
    }
    searchSourceBuilder.sort(fieldSortBuilder);
}
        
        
```


### 聚合查询（aggregation）



```
int from=0;
int size=0;//这里设置为0是不用返回hits那部分内容
SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
searchSourceBuilder.from(from);
searchSourceBuilder.size(size);

searchSourceBuilder.aggregation(AggregationBuilders.terms("group_by_site").field("site.keyword").size(1000));//group_by_site这个只是个名字，可以随便起的



```


### 聚合查询（cardinality）
```
int from=0;
int size=0;//这里设置为0是不用返回hits那部分内容
SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
searchSourceBuilder.from(from);
searchSourceBuilder.size(size);

searchSourceBuilder.aggregation(AggregationBuilders.cardinality("group_by_site").field("site.keyword"));//group_by_site这个只是个名字，可以随便起的


```


