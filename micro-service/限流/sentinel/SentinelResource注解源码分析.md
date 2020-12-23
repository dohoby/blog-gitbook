---
title: SentinelResource注解源码分析
tags: []
date: 2020-12-22 20:17:47
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 

SentinelResource注解切面处理类源码如下：

com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect

```java
@Aspect
public class SentinelResourceAspect extends AbstractSentinelAspectSupport {

    @Pointcut("@annotation(com.alibaba.csp.sentinel.annotation.SentinelResource)")
    public void sentinelResourceAnnotationPointcut() {
    }

    @Around("sentinelResourceAnnotationPointcut()")
    public Object invokeResourceWithSentinel(ProceedingJoinPoint pjp) throws Throwable {
        Method originMethod = resolveMethod(pjp);

        SentinelResource annotation = originMethod.getAnnotation(SentinelResource.class);
        if (annotation == null) {
            // Should not go through here.
            throw new IllegalStateException("Wrong state for SentinelResource annotation");
        }
        String resourceName = getResourceName(annotation.value(), originMethod);
        EntryType entryType = annotation.entryType();
        int resourceType = annotation.resourceType();
        Entry entry = null;
        try {
            entry = SphU.entry(resourceName, resourceType, entryType, pjp.getArgs());
            Object result = pjp.proceed();
            return result;
        } catch (BlockException ex) {
            return handleBlockException(pjp, annotation, ex);
        } catch (Throwable ex) {
            Class<? extends Throwable>[] exceptionsToIgnore = annotation.exceptionsToIgnore();
            // The ignore list will be checked first.
            if (exceptionsToIgnore.length > 0 && exceptionBelongsTo(ex, exceptionsToIgnore)) {
                throw ex;
            }
            if (exceptionBelongsTo(ex, annotation.exceptionsToTrace())) {
                traceException(ex);
                return handleFallback(pjp, annotation, ex);
            }

            // No fallback function can handle the exception, so throw it out.
            throw ex;
        } finally {
            if (entry != null) {
                entry.exit(1, pjp.getArgs());
            }
        }
    }
}

```

分析：
可以看到，关键入口都是
```java
    entry = SphU.entry(resourceName, resourceType, entryType, pjp.getArgs());
```
其中resourceName是从注解那里获取的，所以在这个步骤上，如果我们要实现账号级别的
限流控制，可以这样设计：  
1、定义一个账号-资源关联表，缓存起来  
2、SentinelResource注解标注在controller或serviceImpl层，resourceName可以固定某个字符串，但是这个还不是真正的  
   资源名称，  
3、自己写个类似SentinelResourceAspect的，请求进来时，从请求头中获取账号信息，从缓存(步骤1)中获取账号对应的资源，将resourceName(步骤2)加上资源
   这个才是真正要限流的资源
   
举例子：
```java

    @SentinelResource(value = "flowSyncResource",blockHandler = "sayHelloBlockHandler")
    public String sayHello(String name) {
        System.out.println(getCurrentMachineId() + "," + name + "," + new Date());
        return "Hello, " + name;
    }

```
然后自己定义个MySentinelResourceAspect,在里面组装资源名为flowSyncResource-AAA等



#### 
```java

```
![]()

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