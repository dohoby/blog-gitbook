---
title: guava缓存简单使用三部曲
tags: [guava,缓存]
date: 2019-09-12 13:57:53
categories: guava
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 1、pom
```
<dependency>
    <groupId>com.google.guava</groupId>
    <artifactId>guava</artifactId>
    <version>28.1-jre</version>
</dependency>
```


### 2、定义缓存变量
```
    static LoadingCache<String, String> caches = CacheBuilder.newBuilder()
            .expireAfterWrite(7, TimeUnit.DAYS)
            .build(
                    new CacheLoader<String, String>() {
                        public String load(String key) {//key是infringeUrl
                            log.info("缓存key:{}", key);
                            if (!StringUtils.isEmpty(key)) {
                            }
                            return null;
                        }
                    });
```

#### 3、put设值到缓存
```
 caches.put("infringeUrl", item.getItemValue());
```


#### 4、获取使用缓存

```
 String url=caches.getUnchecked("infringeUrl");
```
#### 5、存在问题
[https://blog.csdn.net/codingtu/article/details/89577316](https://blog.csdn.net/codingtu/article/details/89577316)
