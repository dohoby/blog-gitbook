---
title: excel拼接字符串
tags: [excel,数据处理]
date: 2019-08-07 18:06:03
categories: excel
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->
### 1.前言
开发过程难免与数据打交道，业务人员有时会以excel表格提供数据给开发人员，所以掌握excel一些基本的技巧也很重要很有帮助

### 2.需求
如下图,将excel表格里的数据插入或者更新数据库，方法有很多 
1. 将excel转换为csv文件，然后用awk\cat基本命令处理
2. 利用excel直接拼接

![附件](excel拼接字符串/excel拼接字符串-1.png)


### 3.实战

1.定义拼接的字符串函数如下：

```
="INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'"&A3&"','"&B3&"','"&C3&"','"&D3&"','"&E3&"','"&F3&"','"&G3&"','"&H3&"','"&J3&"','"&K3&"','"&L3&"');"

```

2.点击excel的M3单元格，将上面内容复制到工具栏的fx函数处，如下：
![附件](excel拼接字符串/excel拼接字符串-2.png)

3.然后焦点回到M3单元格，点击回车，即可拼接了字符串
![附件](excel拼接字符串/excel拼接字符串-3.png)

4.再次点击M3单元格，鼠标移动到该单元格右下角，鼠标变成十字后,按住ctrl键,往下拉,松手即可
![附件](excel拼接字符串/excel拼接字符串-5.png)

5.然后复制M列出来即可，内容如下，把前后的双引号去掉即可

```
"INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'MOROCCO','MA','wonderful_sea-MA','0.2','1','0.1','0','0.05','10','DHS','2');"
"INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'EGYPT','EG','wonderful_sea-EG','0.25','1','0.1','0','0.05','19','EGP','0');"
"INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'KENYA','KE','wonderful_sea-KE','0.2','1','0.1','0','0.05','105','KSH','0');"
"INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'KENYA','KE','SMARTEST-KE','0.2','1','0.1','0','0.05','105','KSH','0');"
"INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'KENYA','KE','NICEST store-KE','0.2','1','0.1','0','0.05','105','KSH','0');"
"INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'NIGERIA','NG','Celmia official store-NG','0.2','1','0.1','0','0.05','370','NGN','0');"
"INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'NIGERIA','NG','INCERUN official store-NG','0.2','1','0.1','0','0.05','370','NGN','0');"
```


### 4.函数分析

结果是需要下面的，对比开始的那个函数，也就是'"&A3&"'这地方不同的地方是单引号里面的东西"&A3&"，所以用这个替换单引号里的值，再前后加上双引号即可

```
INSERT INTO sp_business.jumia_price_msg(id,site_name,short_name,shop_name,profit,incidental,deal_rate,poundage_rate,
other_rate,fixed_rate,currency,other_cost) 
VALUES(127,'NIGERIA','NG','INCERUN official store-NG','0.2','1','0.1','0','0.05','370','NGN','0');
```



另外更新的可以如下：

```
="UPDATE sp_common.shop
SET access_token='"&D2&"',site_url='"&C2&"',shop_mail='"&A2&"',site_id='CM',site_name='CAMEROO' 
WHERE shop_name='"&B2&"';"
```


