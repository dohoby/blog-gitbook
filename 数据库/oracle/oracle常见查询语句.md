---
title: oracle常见查询语句
tags: []
date: 2020-09-08 09:42:20
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 1、常见查询

```
//当前用户拥有的表
select  table_name from user_tables; 

//当前表字段 
select * from user_tab_columns where Table_Name='T_IOT_CARD' ORDER BY COLUMN_name asc;

//获取表注释：
select * from user_tab_comments

//获取字段注释：
select * from user_col_comments where table_name='T_IOT_CARD' 


```

参考：[https://blog.51cto.com/meiling/2068902](https://blog.51cto.com/meiling/2068902)

#### 2、oracle中如何查找表在哪个模式下？

```
select * from dba_tables where table_name like '%PROCESS_TEMPLATE%'

```
demo:
```
select * from dba_tables where table_name ='T_IOT_CARD' 

```


1. 
2. 
3. 

#### 3、

1. 
2. 
3. 

```

```
