---
title: form表单序列化后中文乱码问题
tags: []
date: 2020-11-06 10:27:45
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1、jquery form表单序列化后中文乱码问题解决代码实现


```java
前台：
var params =$('#addForm').serialize(); 
params = encodeURI(encodeURI(decodeURIComponent(params,true)));
$.ajax({
	type: "post",  
	url:  "sptSUPPSupplierBankController.cmd?method=persisit",  
	data: params,
	success: function(data) { 
		if(data=='1'){
			alert("保存成功");
		}else{
			alert("保存失败！");
		}
	}
});

jquery form表单.serialize()序列化后中文乱码问题原因及解决
原因：
.serialize()自动调用了encodeURIComponent方法将数据编码了 
解决方法：调用decodeURIComponent(XXX,true);将数据解码 
例如： 
var params = $("#formId").serialize();
params = decodeURIComponent(params,true);
在进行编码
params = encodeURI(encodeURI(params));
后台
params = java.net.URLDecoder.decode(params , "UTF-8");
问题解决。
```

参考：
[https://blog.csdn.net/fzy629442466/article/details/84786049?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.add_param_isCf&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.add_param_isCf](https://blog.csdn.net/fzy629442466/article/details/84786049?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.add_param_isCf&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.add_param_isCf)
[https://blog.csdn.net/w1014074794/article/details/43987689](https://blog.csdn.net/w1014074794/article/details/43987689)


#### 2、
```java

```

```java

```
[]()

#### 3、


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