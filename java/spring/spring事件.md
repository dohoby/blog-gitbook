---
title: spring事件
tags: [spring,事件]
date: 2019-07-30 10:25:22
categories: spring
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 定义事件

自定义事件,继承ApplicationEvent
```
package com.basic.eventlistener.event;

import com.basic.eventlistener.model.JumiaListing;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Data
public class ListingSaveEvent extends ApplicationEvent {

    private List<JumiaListing> jumiaListings;

    /**
     * 这里参数可以自己定义，可以多个
     */
    public ListingSaveEvent(Object source, List jumiaListings) {
        super(source);
        this.jumiaListings = jumiaListings;
    }
}

```

#### 定义Listener监听器(业务处理)

在方法上加上@Async和 @EventListener主键即可

```
package com.basic.eventlistener.listener;

import com.basic.eventlistener.model.JumiaListing;
import com.basic.eventlistener.event.ListingSaveEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class ListingSaveListener {

    @Async
    @EventListener
    public void listingSave(ListingSaveEvent listingSaveEvent) {
        log.info("线程名称：<{}>", Thread.currentThread().getName());

        List<JumiaListing> jumiaListings = listingSaveEvent.getJumiaListings();
        if (jumiaListings == null) {
            return;
        }



    }


}


```

#### 调用

```
package com.basic.eventlistener;

import com.basic.eventlistener.event.ListingSaveEvent;
import com.basic.eventlistener.model.JumiaListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventDemo {
    @Autowired
    private ApplicationContext applicationContext;

    public void publishEvent() {
        // 业务处理
        List<JumiaListing> listingList = new ArrayList<>();

        // 发布订阅模式，有一个业务 event 专门存储这些数据到数据库
        applicationContext.publishEvent(new ListingSaveEvent(this, listingList));
    }

}

```

#### 实体

```
package com.basic.eventlistener.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class JumiaListing extends Model<JumiaListing> {
    private static final long serialVersionUID = -7585917820758380041L;

    private String id;

    private String productCode;

    private String propertyCode;


}


```
