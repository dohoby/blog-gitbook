---
title: http415问题
tags: []
date: 2020-10-14 16:29:41
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1、请求参数

```java
General
Reqquest URL: http://localhost:8080/fzsiotcard/api/interfacemanage/iotInterfaceDictReview/add?_=1602662669347
Request Method: POST
Status Code: 415 
Remote Address: [::1]:8080
Referrer Policy: strict-origin-when-cross-origin

Response Headers
Connection: keep-alive
Content-Language: zh-CN
Content-Length: 675
Content-Type: text/html;charset=utf-8
Date: Wed, 14 Oct 2020 08:04:29 GMT
Keep-Alive: timeout=20


Request Headers
Accept: application/json, text/javascript, */*; q=0.01
Accept-Encoding: gzip, deflate, br
Accept-Language: zh-CN,zh;q=0.9
Connection: keep-alive
Content-Length: 54
Content-Type: application/x-www-form-urlencoded; charset=UTF-8
Cookie: JSESSIONID=F15DD56D87E5EA271E84A5F7F78BF302; login=P_-DAwEBDkNvb2tpZVJlbWVtYmVyAf-EAAEDAQhNZW1iZXJJZAEEAAEHQWNjb3VudAEMAAEEVGltZQH_hgAAABD_hQUBAQRUaW1lAf-GAAAAHf-EAQIBBWFkbWluAQ8BAAAADtbw0KQ6weScAeAA|1600051620985785500|8b02a18f03f527ff1ba9d83799970afc65e34045
Host: localhost:8080
Origin: http://localhost:8080
Referer: http://localhost:8080/fzsiotcard/api/interfacemanage/iotInterfaceDictContent/init?interfaceDictId=2&categoryName=%E5%B9%B3%E5%8F%B0%E7%B1%BB%E5%88%AB
Sec-Fetch-Dest: empty
Sec-Fetch-Mode: cors
Sec-Fetch-Site: same-origin
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36
X-Requested-With: XMLHttpRequest

Query String Parameters
_: 1602662669347

Form data
dictContentId: 
interfaceDictId: 2
name: - LiangQingXiang


```


```java

```
![](http415问题/http415-2.png)
![](http415问题/http415-3.png)
![](http415问题/http415-4.png)

#### 2、响应结果
```java

HTTP状态 415 - 不支持的媒体类型
类型 状态报告

描述 源服务器拒绝服务请求，因为有效负载的格式在目标资源上此方法不支持。

Apache Tomcat/8.5.57
```
![](http415问题/http415-1.png)



```java

```
[]()

#### 3、后端


```java
    @ResponseBody
    @RequestMapping(value = "/add")//, method = RequestMethod.POST)
    public ResultBean insert(HttpServletRequest request, HttpServletResponse response, @RequestBody IotInterfaceDictReview record) {
        ResultBean resultBean = new ResultBean();


        iotInterfaceDictReviewService.insert(record);

        return resultBean;
    }
```
#### 4、解决
是后端controller请求参数里里多了个@RequestBody导致的，这个可能是前端以json才需要用这个吧  


```java

```
[]()
```




1. 
2. 
3. 
![]()