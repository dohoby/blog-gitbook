---
title: oracle约束
tags: []
date: 2024-07-09 20:02:34
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 

```java
-- 创建一个测试表
CREATE TABLE employees (
    department_id NUMBER,
    employee_id NUMBER,
    name VARCHAR2(100),
    -- 其他字段...
    CONSTRAINT emp_dept_uk UNIQUE (department_id, employee_id)
);
 
-- 或者，如果你想要创建一个主键约束
CREATE TABLE employees (
    department_id NUMBER,
    employee_id NUMBER,
    name VARCHAR2(100),
    -- 其他字段...
    CONSTRAINT emp_dept_pk PRIMARY KEY (department_id, employee_id)
);
```
在这个例子中，emp_dept_uk 是一个唯一约束，它基于department_id和employee_id两个字段的组合来确保记录的唯一性。如果你想要创建一个主键约束，可以像上面例子中那样将约束命名为emp_dept_pk，并将关键字UNIQUE替换为PRIMARY KEY。
[https://blog.csdn.net/pan_junbiao/article/details/78166095](https://blog.csdn.net/pan_junbiao/article/details/78166095)
[https://www.cnblogs.com/lijiaman/p/7225831.html](https://www.cnblogs.com/lijiaman/p/7225831.html)


```java

```


#### 
```java

```

```java

```


#### 


```java

```

```java

```
[]()
```




1. 
2. 
3. 
![]()