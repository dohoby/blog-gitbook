---
title: bigdecimal相除问题
tags: []
date: 2024-01-03 18:51:53
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 问题代码 

问题代码如下：
```java
        BigDecimal salePrice=new BigDecimal("2.5000000");
        BigDecimal saleDiscount=new BigDecimal("8.3333");

        BigDecimal standardRate = salePrice
        .divide(saleDiscount)
        .multiply(BigDecimal.valueOf(100))
        .setScale(7, RoundingMode.HALF_UP);
        System.out.println(standardRate);
```
然后报错：
```java
Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
at java.math.BigDecimal.divide(BigDecimal.java:1693)
at com.redis.Test2Case.main(Test2Case.java:25)

```
分析：
报错是指：java.lang.AthmeticException：非终止小数展开；没有可精确表示的十进制结果。
其实就是无限循环，小数点后3333这些除不尽


```java
        BigDecimal salePrice=new BigDecimal("2.5000000");
        BigDecimal saleDiscount=new BigDecimal("8.3333");

        BigDecimal standardRate = salePrice
                .divide(saleDiscount,7,RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(7, RoundingMode.HALF_UP);
        System.out.println(standardRate);
```
结果：30.0120000

```java

```

[Non-terminating decimal expansion；no exact representable decimal result](https://blog.csdn.net/qq_42730111/article/details/118177631)
[https://mp.weixin.qq.com/s/lirmZlwdCIPIzsObsOiyDA](https://mp.weixin.qq.com/s/lirmZlwdCIPIzsObsOiyDA)

#### 
```java

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