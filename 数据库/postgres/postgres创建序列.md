---
title: postgres创建序列
tags: [postgres,数据库]
date: 2019-08-08 15:44:00
categories: postgres
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### postgres创建序列并设置到某个主键上
```
CREATE SEQUENCE jumia_brand_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


alter table jumia_brand alter column id set default nextval('jumia_brand_seq');
```
