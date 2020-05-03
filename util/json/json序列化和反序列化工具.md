---
title: json序列化和反序列化工具
tags: [json,序列化]
date: 2019-08-05 14:47:40
categories: json
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 利用TypeReference将json反序列化成复杂对象
用法如下:
```
        String responseBody = "";
        CommonResult<JumiaListing> resultCommonResult = JsonUtil.getObjectMapper()
                .readValue(responseBody, new TypeReference<CommonResult<JumiaListing>>() {
                });
```

其中CommonResult如下

```
package com.basic.bean;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 通用结果
 */
@Slf4j
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommonResult<T> {
    private String targetUrl = null;
    private T result = null;
    private boolean success = false;
    private boolean authorizedRequest = false;
    private List<ErrorInfo> errorInfos;
    private String seqNo;
    private long costTime = -1L;
}

```                
