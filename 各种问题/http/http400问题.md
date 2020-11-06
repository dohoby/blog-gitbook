---
title: http400问题
tags: []
date: 2020-10-30 15:08:41
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1、后端controller加上@RequestBody就报错400

```java
   @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultBean save(HttpServletRequest request,@RequestBody IotInterfaceParamConfigDTO record) {
        ResultBean resultBean = new ResultBean();
        IotUser user = super.getPortalUser(request);
        if (user == null) {
            return ResultBean.resultFail("0", "用户没登录");
        }


        if (record.getId() != null) {
            record.setCreator(user.getUserName());
            iotInterfaceParamConfigService.update(record);
        } else {
            record.setModifier(user.getUserName());
            iotInterfaceParamConfigService.add(record);
        }


        return resultBean;
    }
```

```java

```
[]()

#### 2、前端用ajax或者rest client
请求参数

```java
{
	"id": "",
	"interfacePlatformId": "12",
	"businessCategoryId": "11",
	"interfaceCategoryId": "11",
	"interfaceNumber": "aa",
	"interfaceName": "a",
	"serviceAddress": "a",
	"businessDesc": "a",
	"suitFor": "1",
	"enable": "1",
	"iotInterfaceParamRules": [{
		"id": "undefined",
		"cardManageMode": "4",
		"condition": ">1 >1",
		"priority": "3",
		"remarkDesc": "a"
	}],
	"iotInterfaceParamProperties": [{
		"id": "",
		"attributeName": "a",
		"condition": "a",
		"remarkDesc": "a"
	}]
}
```

```java

```
[]()

#### 3、解决：

log4j.properties加上debug，改为下面的，

```java
log4j.rootLogger=debug,info, CONSOLE, FILE,stdout

```

开启后查看日志：

```
2020-10-30 15:04:36.861 pid[] thread[http-nio-8080-exec-4hread] DEBUG hibernate4.support.OpenSessionInViewFilter:187 - Using SessionFactory 'sys_sessionFactory' for OpenSessionInViewFilter
2020-10-30 15:04:36,861 DEBUG [org.springframework.orm.hibernate4.support.OpenSessionInViewFilter] - Using SessionFactory 'sys_sessionFactory' for OpenSessionInViewFilter
2020-10-30 15:04:36.863 pid[] thread[http-nio-8080-exec-4hread] DEBUG factory.support.DefaultListableBeanFactory:243 - Returning cached instance of singleton bean 'sys_sessionFactory'
2020-10-30 15:04:36,863 DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory] - Returning cached instance of singleton bean 'sys_sessionFactory'
2020-10-30 15:04:36.863 pid[] thread[http-nio-8080-exec-4hread] DEBUG hibernate4.support.OpenSessionInViewFilter:139 - Opening Hibernate Session in OpenSessionInViewFilter
2020-10-30 15:04:36,863 DEBUG [org.springframework.orm.hibernate4.support.OpenSessionInViewFilter] - Opening Hibernate Session in OpenSessionInViewFilter
2020-10-30 15:04:36.864 pid[] thread[http-nio-8080-exec-4hread] DEBUG hibernate.internal.SessionImpl:302 - Opened session at timestamp: 16040414768
2020-10-30 15:04:36,864 DEBUG [org.hibernate.internal.SessionImpl] - Opened session at timestamp: 16040414768
2020-10-30 15:04:36.864 pid[] thread[http-nio-8080-exec-4hread] DEBUG web.servlet.DispatcherServlet:819 - DispatcherServlet with name 'dispatchServlet' processing POST request for [/fzsiotcard/admin/interfacemanage/iotInterfaceParamConfig/save]
2020-10-30 15:04:36,864 DEBUG [org.springframework.web.servlet.DispatcherServlet] - DispatcherServlet with name 'dispatchServlet' processing POST request for [/fzsiotcard/admin/interfacemanage/iotInterfaceParamConfig/save]
2020-10-30 15:04:36.864 pid[] thread[http-nio-8080-exec-4hread] DEBUG method.annotation.RequestMappingHandlerMapping:229 - Looking up handler method for path /admin/interfacemanage/iotInterfaceParamConfig/save
2020-10-30 15:04:36,864 DEBUG [org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping] - Looking up handler method for path /admin/interfacemanage/iotInterfaceParamConfig/save
2020-10-30 15:04:36.865 pid[] thread[http-nio-8080-exec-4hread] DEBUG method.annotation.RequestMappingHandlerMapping:234 - Returning handler method [public com.web.bean.ResultBean com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(javax.servlet.http.HttpServletRequest,com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO)]
2020-10-30 15:04:36,865 DEBUG [org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping] - Returning handler method [public com.web.bean.ResultBean com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(javax.servlet.http.HttpServletRequest,com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO)]
2020-10-30 15:04:36.865 pid[] thread[http-nio-8080-exec-4hread] DEBUG factory.support.DefaultListableBeanFactory:243 - Returning cached instance of singleton bean 'iotInterfaceParamConfigController'
2020-10-30 15:04:36,865 DEBUG [org.springframework.beans.factory.support.DefaultListableBeanFactory] - Returning cached instance of singleton bean 'iotInterfaceParamConfigController'
2020-10-30 15:04:36.865 pid[] thread[http-nio-8080-exec-4hread] DEBUG component.interceptors.AuthenticationInterceptor:49 - HandlerMethod: /admin/interfacemanage/iotInterfaceParamConfig/save rights authentication.
2020-10-30 15:04:36,865 DEBUG [com.fzs.uniauth.component.interceptors.AuthenticationInterceptor] - HandlerMethod: /admin/interfacemanage/iotInterfaceParamConfig/save rights authentication.
2020-10-30 15:04:36.866 pid[] thread[http-nio-8080-exec-4hread] DEBUG method.annotation.RequestResponseBodyMethodProcessor:140 - Reading [class com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@77cc5550]
2020-10-30 15:04:36,866 DEBUG [org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor] - Reading [class com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@77cc5550]
2020-10-30 15:04:36.867 pid[] thread[http-nio-8080-exec-4hread] DEBUG method.annotation.ExceptionHandlerExceptionResolver:132 - Resolving exception from handler [public com.web.bean.ResultBean com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(javax.servlet.http.HttpServletRequest,com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO)]: org.springframework.http.converter.HttpMessageNotReadableException: Could not read JSON: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"]); nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"])
2020-10-30 15:04:36,867 DEBUG [org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver] - Resolving exception from handler [public com.web.bean.ResultBean com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(javax.servlet.http.HttpServletRequest,com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO)]: org.springframework.http.converter.HttpMessageNotReadableException: Could not read JSON: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"]); nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"])
2020-10-30 15:04:36.867 pid[] thread[http-nio-8080-exec-4hread] DEBUG mvc.annotation.ResponseStatusExceptionResolver:132 - Resolving exception from handler [public com.web.bean.ResultBean com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(javax.servlet.http.HttpServletRequest,com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO)]: org.springframework.http.converter.HttpMessageNotReadableException: Could not read JSON: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"]); nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"])
2020-10-30 15:04:36,867 DEBUG [org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver] - Resolving exception from handler [public com.web.bean.ResultBean com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(javax.servlet.http.HttpServletRequest,com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO)]: org.springframework.http.converter.HttpMessageNotReadableException: Could not read JSON: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"]); nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"])
2020-10-30 15:04:36.868 pid[] thread[http-nio-8080-exec-4hread] DEBUG mvc.support.DefaultHandlerExceptionResolver:132 - Resolving exception from handler [public com.web.bean.ResultBean com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(javax.servlet.http.HttpServletRequest,com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO)]: org.springframework.http.converter.HttpMessageNotReadableException: Could not read JSON: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"]); nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"])
2020-10-30 15:04:36,868 DEBUG [org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver] - Resolving exception from handler [public com.web.bean.ResultBean com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(javax.servlet.http.HttpServletRequest,com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO)]: org.springframework.http.converter.HttpMessageNotReadableException: Could not read JSON: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"]); nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
 at [Source: (org.apache.catalina.connector.CoyoteInputStream); line: 13, column: 9] (through reference chain: com.web.supplier.interfacemanage.bean.IotInterfaceParamConfigDTO["iotInterfaceParamRules"]->java.util.ArrayList[0]->com.web.supplier.interfacemanage.entity.IotInterfaceParamRule["id"])
2020-10-30 15:04:36.868 pid[] thread[http-nio-8080-exec-4hread] DEBUG web.servlet.DispatcherServlet:993 - Null ModelAndView returned to DispatcherServlet with name 'dispatchServlet': assuming HandlerAdapter completed request handling
2020-10-30 15:04:36,868 DEBUG [org.springframework.web.servlet.DispatcherServlet] - Null ModelAndView returned to DispatcherServlet with name 'dispatchServlet': assuming HandlerAdapter completed request handling
2020-10-30 15:04:36.869 pid[] thread[http-nio-8080-exec-4hread] DEBUG web.servlet.DispatcherServlet:983 - Successfully completed request
2020-10-30 15:04:36,869 DEBUG [org.springframework.web.servlet.DispatcherServlet] - Successfully completed request
2020-10-30 15:04:36.869 pid[] thread[http-nio-8080-exec-4hread] DEBUG hibernate4.support.OpenSessionInViewFilter:159 - Closing Hibernate Session in OpenSessionInViewFilter
2020-10-30 15:04:36,869 DEBUG [org.springframework.orm.hibernate4.support.OpenSessionInViewFilter] - Closing Hibernate Session in OpenSessionInViewFilter
2020-10-30 15:05:05.847 pid[] thread[Apollo-RemoteConfigLongPollService-1hread] DEBUG apollo.internals.RemoteConfigLongPollService:172 - Long polling response: 304, url: http://dev-apollo-configservice:9012/notifications/v2?cluster=default&appId=fzs-iotcard&ip=10.20.11.161&notifications=%5B%7B%22namespaceName%22%3A%22application%22%2C%22notificationId%22%3A2154%7D%5D
2020-10-30 15:05:05,847 DEBUG [com.ctrip.framework.apollo.internals.RemoteConfigLongPollService] - Long polling response: 304, url: http://dev-apollo-configservice:9012/notifications/v2?cluster=default&appId=fzs-iotcard&ip=10.20.11.161&notifications=%5B%7B%22namespaceName%22%3A%22application%22%2C%22notificationId%22%3A2154%7D%5D
2020-10-30 15:05:05.849 pid[] thread[Apollo-RemoteConfigLongPollService-1hread] DEBUG apollo.internals.RemoteConfigLongPollService:163 - Long polling from http://dev-apollo-configservice:9012/notifications/v2?cluster=default&appId=fzs-iotcard&ip=10.20.11.161&notifications=%5B%7B%22namespaceName%22%3A%22application%22%2C%22notificationId%22%3A2154%7D%5D
2020-10-30 15:05:05,849 DEBUG [com.ctrip.framework.apollo.internals.RemoteConfigLongPollService] - Long polling from http://dev-apollo-configservice:9012/notifications/v2?cluster=default&appId=fzs-iotcard&ip=10.20.11.161&notifications=%5B%7B%22namespaceName%22%3A%22application%22%2C%22notificationId%22%3A2154%7D%5D

```

仔细观察发现是这行日志的问题：
```
IotInterfaceParamRule["id"]); nested exception is com.fasterxml.jackson.databind.exc.InvalidFormatException: Cannot deserialize value of type `java.lang.Long` from String "undefined": not a valid Long value
```
也就是IotInterfaceParamRule对应的字段id设值为undefined了，
再仔细观察请求json参数，果然发现有个undefined

```java
	"iotInterfaceParamRules": [{
		"id": "undefined",
		"cardManageMode": "4",
		"condition": ">1 >1",
		"priority": "3",
		"remarkDesc": "a"
	}]
```

干掉undefined再请求即可正常了


#### 正确json如下：
```json
{
	"id": "",
	"interfacePlatformId": "12",
	"businessCategoryId": "7",
	"interfaceCategoryId": "11",
	"interfaceNumber": "1",
	"interfaceName": "1",
	"serviceAddress": "1",
	"businessDesc": "1",
	"suitFor": "2",
	"enable": "1",
	"iotInterfaceParamRules": [{
		"id": "",
		"cardManageMode": "1",
		"condition": "<11 <11",
		"priority": "1",
		"remarkDesc": "11"
	}],
	"iotInterfaceParamProperties": [{
		"id": "",
		"attributeName": "1",
		"condition": "1",
		"remarkDesc": "1"
	}]
}
```


#### ajax请求：

```javascript
  //获取rule数据
    var ruleData = getRuleTableData();
    var propertyData = getPropertyData();


    var formdata = form.serialize();//序列化表单
    var arr = formdata.split("&");//转换成字符数组

    var newData = new Object();//用来存储转换后的数组
    for (var i = 0; i < arr.length; i++) {
        var fieldArr = arr[i].split("=");
        newData[fieldArr[0]] = fieldArr[1];
    }
    newData["iotInterfaceParamRules"] = ruleData;
    newData["iotInterfaceParamProperties"] = propertyData;


    var commitData = JSON.stringify(newData);
    console.log(commitData);
    var url = getContextPath() + "/admin/interfacemanage/iotInterfaceParamConfig/save";
    $.ajax({
        url: url,
        type: "POST",
        data: commitData,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        //Accept:"application/json; charset=utf-8",
        headers: {"Accept": "application/json"},

        success: function () {
            if (data.success) {
                Util.alertMsg("操作成功");
                loadBsDaily();
            } else {
                Util.prompt(data.message)
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("XMLHttpRequest.status:{}", XMLHttpRequest.status);
            console.log("XMLHttpRequest:{}", XMLHttpRequest);

            if (XMLHttpRequest.status == 500) {
                //Util.prompt("服务器错误")
                layer.msg('服务器错误!');
            } else {
                //Util.prompt("保存失败")
                layer.msg('保存失败!');
            }
        }
    });
```

别人的400(和我上面的不一样)
[400问题](https://blog.csdn.net/PMJ520/article/details/54743980?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.add_param_isCf&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.add_param_isCf)


#### ajax错误返回：

参考：
[https://www.cnblogs.com/xinzhisoft/p/10648946.html](https://www.cnblogs.com/xinzhisoft/p/10648946.html)


上面我自己打印的错误js日志：

```java

abort: ƒ (t)
always: ƒ ()
complete: ƒ ()
done: ƒ ()
error: ƒ ()
fail: ƒ ()
getAllResponseHeaders: ƒ ()
getResponseHeader: ƒ (t)
overrideMimeType: ƒ (t)
pipe: ƒ ()
progress: ƒ ()
promise: ƒ (t)
readyState: 4
responseText: "<!doctype html><html lang="zh"><head><title>HTTP状态 500 - 内部服务器错误</title><style type="text/css">body {font-family:Tahoma,Arial,sans-serif;} h1, h2, h3, b {color:white;background-color:#525D76;} h1 {font-size:22px;} h2 {font-size:16px;} h3 {font-size:14px;} p {font-size:12px;} a {color:black;} .line {height:1px;background-color:#525D76;border:none;}</style></head><body><h1>HTTP状态 500 - 内部服务器错误</h1><hr class="line" /><p><b>类型</b> 异常报告</p><p><b>消息</b> Request processing failed; nested exception is org.springframework.dao.DataIntegrityViolationException: </p><p><b>描述</b> 服务器遇到一个意外的情况，阻止它完成请求。</p><p><b>例外情况</b></p><pre>org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.springframework.dao.DataIntegrityViolationException: 
↵### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵
↵### The error may exist in file [D:\idea-workspace4\FZS_IOT_CARD\target\project-practice\WEB-INF\classes\mapper\interfacemanage\IotInterfaceParamRuleMapper.xml]
↵### The error may involve com.web.supplier.interfacemanage.mapper.IotInterfaceParamRuleMapper.insertSelective-Inline
↵### The error occurred while setting parameters
↵### SQL: insert into T_IOT_INTERFACE_PARAM_RULE      ( ID,                       CARD_MANAGE_MODE,                       CONDITION,                       PRIORITY,                       REMARK_DESC,                              STATUS,                                                          CREATE_TIME,                              DELETED )       values ( ?,                       ?,                       ?,                       ?,                       ?,                              ?,                                                          ?,                              ? )
↵### Cause: java.sql.SQLIntegrityConstraintViolationException: ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵↵; SQL []; ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵; nested exception is java.sql.SQLIntegrityConstraintViolationException: ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵
↵	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:965)
↵	org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:855)
↵	javax.servlet.http.HttpServlet.service(HttpServlet.java:652)
↵	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:829)
↵	javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
↵	org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:77)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
↵	org.springframework.orm.hibernate4.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:151)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)
↵</pre><p><b>根本原因。</b></p><pre>org.springframework.dao.DataIntegrityViolationException: 
↵### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵
↵### The error may exist in file [D:\idea-workspace4\FZS_IOT_CARD\target\project-practice\WEB-INF\classes\mapper\interfacemanage\IotInterfaceParamRuleMapper.xml]
↵### The error may involve com.web.supplier.interfacemanage.mapper.IotInterfaceParamRuleMapper.insertSelective-Inline
↵### The error occurred while setting parameters
↵### SQL: insert into T_IOT_INTERFACE_PARAM_RULE      ( ID,                       CARD_MANAGE_MODE,                       CONDITION,                       PRIORITY,                       REMARK_DESC,                              STATUS,                                                          CREATE_TIME,                              DELETED )       values ( ?,                       ?,                       ?,                       ?,                       ?,                              ?,                                                          ?,                              ? )
↵### Cause: java.sql.SQLIntegrityConstraintViolationException: ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵↵; SQL []; ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵; nested exception is java.sql.SQLIntegrityConstraintViolationException: ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵
↵	org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator.doTranslate(SQLErrorCodeSQLExceptionTranslator.java:249)
↵	org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)
↵	org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:71)
↵	org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:365)
↵	com.sun.proxy.$Proxy26.insert(Unknown Source)
↵	org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:237)
↵	org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:79)
↵	org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:40)
↵	com.sun.proxy.$Proxy167.insertSelective(Unknown Source)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamRuleServiceImpl.insert(IotInterfaceParamRuleServiceImpl.java:59)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl.lambda$saveIotInterfaceParamProperties$1(IotInterfaceParamConfigServiceImpl.java:173)
↵	java.util.ArrayList.forEach(ArrayList.java:1249)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl.saveIotInterfaceParamProperties(IotInterfaceParamConfigServiceImpl.java:167)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl.add(IotInterfaceParamConfigServiceImpl.java:124)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl$$FastClassBySpringCGLIB$$b816365b.invoke(&lt;generated&gt;)
↵	org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
↵	org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:629)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl$$EnhancerBySpringCGLIB$$47ae7cb1.add(&lt;generated&gt;)
↵	com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(IotInterfaceParamConfigController.java:88)
↵	com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController$$FastClassBySpringCGLIB$$17aa7ed9.invoke(&lt;generated&gt;)
↵	org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
↵	org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:700)
↵	org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)
↵	org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:91)
↵	org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)
↵	org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:633)
↵	com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController$$EnhancerBySpringCGLIB$$f44c91c5.save(&lt;generated&gt;)
↵	sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
↵	sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
↵	sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
↵	java.lang.reflect.Method.invoke(Method.java:498)
↵	org.springframework.web.method.support.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:215)
↵	org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:132)
↵	org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104)
↵	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:745)
↵	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:685)
↵	org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:80)
↵	org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:919)
↵	org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:851)
↵	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:953)
↵	org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:855)
↵	javax.servlet.http.HttpServlet.service(HttpServlet.java:652)
↵	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:829)
↵	javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
↵	org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:77)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
↵	org.springframework.orm.hibernate4.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:151)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)
↵</pre><p><b>根本原因。</b></p><pre>java.sql.SQLIntegrityConstraintViolationException: ORA-01400: 无法将 NULL 插入 (&quot;UIOTSYSTEM_DEV&quot;.&quot;T_IOT_INTERFACE_PARAM_RULE&quot;.&quot;EFFECTIVE_STATUS&quot;)↵
↵	oracle.jdbc.driver.T4CTTIoer.processError(T4CTTIoer.java:439)
↵	oracle.jdbc.driver.T4CTTIoer.processError(T4CTTIoer.java:395)
↵	oracle.jdbc.driver.T4C8Oall.processError(T4C8Oall.java:802)
↵	oracle.jdbc.driver.T4CTTIfun.receive(T4CTTIfun.java:436)
↵	oracle.jdbc.driver.T4CTTIfun.doRPC(T4CTTIfun.java:186)
↵	oracle.jdbc.driver.T4C8Oall.doOALL(T4C8Oall.java:521)
↵	oracle.jdbc.driver.T4CPreparedStatement.doOall8(T4CPreparedStatement.java:205)
↵	oracle.jdbc.driver.T4CPreparedStatement.executeForRows(T4CPreparedStatement.java:1008)
↵	oracle.jdbc.driver.OracleStatement.doExecuteWithTimeout(OracleStatement.java:1307)
↵	oracle.jdbc.driver.OraclePreparedStatement.executeInternal(OraclePreparedStatement.java:3449)
↵	oracle.jdbc.driver.OraclePreparedStatement.execute(OraclePreparedStatement.java:3550)
↵	oracle.jdbc.driver.OraclePreparedStatementWrapper.execute(OraclePreparedStatementWrapper.java:1374)
↵	com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:2931)
↵	com.alibaba.druid.filter.FilterEventAdapter.preparedStatement_execute(FilterEventAdapter.java:440)
↵	com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:2929)
↵	com.alibaba.druid.wall.WallFilter.preparedStatement_execute(WallFilter.java:601)
↵	com.alibaba.druid.filter.FilterChainImpl.preparedStatement_execute(FilterChainImpl.java:2929)
↵	com.alibaba.druid.proxy.jdbc.PreparedStatementProxyImpl.execute(PreparedStatementProxyImpl.java:131)
↵	com.alibaba.druid.pool.DruidPooledPreparedStatement.execute(DruidPooledPreparedStatement.java:493)
↵	sun.reflect.GeneratedMethodAccessor95.invoke(Unknown Source)
↵	sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
↵	java.lang.reflect.Method.invoke(Method.java:498)
↵	org.apache.ibatis.logging.jdbc.PreparedStatementLogger.invoke(PreparedStatementLogger.java:58)
↵	com.sun.proxy.$Proxy124.execute(Unknown Source)
↵	org.apache.ibatis.executor.statement.PreparedStatementHandler.update(PreparedStatementHandler.java:41)
↵	org.apache.ibatis.executor.statement.RoutingStatementHandler.update(RoutingStatementHandler.java:66)
↵	org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:45)
↵	org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:108)
↵	org.apache.ibatis.executor.CachingExecutor.update(CachingExecutor.java:75)
↵	org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:145)
↵	org.apache.ibatis.session.defaults.DefaultSqlSession.insert(DefaultSqlSession.java:134)
↵	sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
↵	sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
↵	sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
↵	java.lang.reflect.Method.invoke(Method.java:498)
↵	org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:355)
↵	com.sun.proxy.$Proxy26.insert(Unknown Source)
↵	org.mybatis.spring.SqlSessionTemplate.insert(SqlSessionTemplate.java:237)
↵	org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:79)
↵	org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:40)
↵	com.sun.proxy.$Proxy167.insertSelective(Unknown Source)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamRuleServiceImpl.insert(IotInterfaceParamRuleServiceImpl.java:59)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl.lambda$saveIotInterfaceParamProperties$1(IotInterfaceParamConfigServiceImpl.java:173)
↵	java.util.ArrayList.forEach(ArrayList.java:1249)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl.saveIotInterfaceParamProperties(IotInterfaceParamConfigServiceImpl.java:167)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl.add(IotInterfaceParamConfigServiceImpl.java:124)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl$$FastClassBySpringCGLIB$$b816365b.invoke(&lt;generated&gt;)
↵	org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
↵	org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:629)
↵	com.web.supplier.interfacemanage.service.impl.IotInterfaceParamConfigServiceImpl$$EnhancerBySpringCGLIB$$47ae7cb1.add(&lt;generated&gt;)
↵	com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController.save(IotInterfaceParamConfigController.java:88)
↵	com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController$$FastClassBySpringCGLIB$$17aa7ed9.invoke(&lt;generated&gt;)
↵	org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)
↵	org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:700)
↵	org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)
↵	org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:91)
↵	org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)
↵	org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:633)
↵	com.web.supplier.interfacemanage.controller.IotInterfaceParamConfigController$$EnhancerBySpringCGLIB$$f44c91c5.save(&lt;generated&gt;)
↵	sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
↵	sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
↵	sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
↵	java.lang.reflect.Method.invoke(Method.java:498)
↵	org.springframework.web.method.support.InvocableHandlerMethod.invoke(InvocableHandlerMethod.java:215)
↵	org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:132)
↵	org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:104)
↵	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandleMethod(RequestMappingHandlerAdapter.java:745)
↵	org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:685)
↵	org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:80)
↵	org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:919)
↵	org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:851)
↵	org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:953)
↵	org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:855)
↵	javax.servlet.http.HttpServlet.service(HttpServlet.java:652)
↵	org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:829)
↵	javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
↵	org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:77)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
↵	org.springframework.orm.hibernate4.support.OpenSessionInViewFilter.doFilterInternal(OpenSessionInViewFilter.java:151)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:88)
↵	org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:106)
↵	com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123)
↵</pre><p><b>):注意</b> 主要问题的全部 stack 信息可以在 server logs 里查看</p><hr class="line" /><h3>Apache Tomcat/8.5.57</h3></body></html>"
setRequestHeader: ƒ (t,e)
state: ƒ ()
status: 500
statusCode: ƒ (t)
statusText: "error"
success: ƒ ()
then: ƒ ()
__proto__: Object

```


1. 
2. 
3. 
![]()