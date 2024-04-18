---
title: oracle分批插入更新
tags: []
date: 2024-02-23 16:02:22
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 分批插入更新
才哥模板：
```java

declare
  inum=0;
begin
   for p in (select * from t_temp_table) loop
      更新或插入逻辑
    inum:=inum+1;
   if inum=50000 then
    commit;
    inum:=0;
   end if;
   end loop;
   commit;
end;

```

我的实例：
select count(CARD_NO) INTO v_count
这句是将查询的count数目赋值给变量v_count
再根据v_count>0(说明数据库中存在记录)则更新，否则新增

```java
declare
    inum number;
    v_count number;
begin
    inum:=0;
    for p in (select * from T_DATA_TEMP) loop
            select count(CARD_NO)
            INTO v_count
            from T_RISK_INFO  where CARD_NO=p.CARD_NO;
            if v_count>0 then
                update T_RISK_INFO a
                set a.last_position=p.ICCID,a.LAST_POSITION_UPDATE_TIME=p.CARD_ACTIVE_DATE
                where a.CARD_NO=p.CARD_NO;
            else
                insert INTO T_RISK_INFO(id,CARD_NO,last_position,LAST_POSITION_UPDATE_TIME,CREATOR,CREATE_TIME)
                values (SEQ_RISK_INFO.nextval,p.card_no,p.ICCID,p.CARD_ACTIVE_DATE,'lqx',sysdate);
            end if;
            inum:=inum+1;
            if inum=50000 then
                commit;
                inum:=0;
            end if;
        end loop;
    commit;
end;
```


[]()

#### merge into 插入或者更新
注意这种方式不能分批提交

https://blog.csdn.net/spw55381155/article/details/79891305
https://javaforall.cn/134030.html


```java
merge into 目标表 a

using 源表 b

on(a.条件字段1=b.条件字段1 and a.条件字段2=b.条件字段2 ……)

when matched then update set a.字段=b.字段 --目标表别称a和源表别称b都不要省略

when  not matched then insert (a.字段1,a.字段2……)values(b.字段1,b.字段2……) --目标表别称a可省略,源表别称b不可省略

```

```java

```
[]()

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