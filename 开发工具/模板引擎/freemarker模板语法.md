---
title: freemarker模板语法
tags: []
date: 2020-12-02 10:15:37
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->






#### 列表

```java
<#list table.importPackages as pkg>
import ${pkg};
</#list>
```
#### 条件if
```java
<#if swagger2>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
```

#### 判断不为空K
加上??两个问号
```java
    <#if superEntityClass??>
```

我的例子：
```java
<#if (cfg.KeySequenceMap[tableNameKey])??>
@KeySequence(value="${cfg.KeySequenceMap[tableNameKey]}")
</#if>
```
其中KeySequenceMap是一个Map，判断这个map中是否包含tableNameKey这个key，
有才执行if里面的

![https://www.cnblogs.com/yisanx/p/12421508.html](https://www.cnblogs.com/yisanx/p/12421508.html)


### freemarker输出特殊字符
https://blog.csdn.net/xiaojin21cen/article/details/113844342


在 ${ } 中加入 r ，即 ${r'原样输入的内容'} ，即可原样输出。

例如，想原样输出 ${user.name} ，如下：

${r'user.name'}


### freemarker遍历list处理第一个、最后一个元素

https://blog.csdn.net/moshowgame/article/details/82744463




#### 


```java

```

```java

```
![]()
```




1. 
2. 
3. 
![]()