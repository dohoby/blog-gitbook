---
title: ieda快捷生成代码
tags: [idea]
date: 2020-10-15 12:10:46
categories: idea
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1. psvm

```java
   //生成main方法:
     public static void main(String[] args) {}
```
#### 2. sout

```java
//生成打印输出:
System.out.println();
```

#### 3. "abc".sout


```java
//生成打印字符串:
System.out.println("adc");

```

#### 4. "abc".format 

```java
  //生成字符串格式化:
  String.format("abc", )
```


//如List或者Array: List<String> list = new ArrayList<>(); 
#### 5. itli 
```java

   //生成for循环
    for (int i = 0; i < list.size(); i++) {
          String s =  list.get(i);
    }
 ```

#### 6. itco
```java

  // 生成Collection迭代器
     for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
           String next =  iterator.next();   
     }
 ```

#### 7. iter 
```java

  ///生成增强for循环
     for (String s : list) {       
     }
 ```

#### 8.iten
```java

   ///生成 enumeration遍历
     while (enumeration.hasMoreElements()) {
          Object nextElement =  enumeration.nextElement();   
      }
 
```

#### 9. itar
```java

      ///生成数组for循环
      int[] array = {1,2,3,4,5};
      for (int i = 0; i < array.length; i++) {
          int i1 = array[i]; 
      }
 
```

#### 10. itit
```java

    ///生成迭代器 iterator
     Iterator iterator = list.iterator();
     while (iterator.hasNext()) {
          Object next =  iterator.next();
     }
 ```

#### 11. ittok
```java

   //ittok  生成String token遍历
     for (StringTokenizer stringTokenizer = new StringTokenizer(APP_NAME); stringTokenizer.hasMoreTokens(); ) {
          String s = stringTokenizer.nextToken();
      }
 ```

#### 12. itws  

```java

   //生成Axis2 web service调用
       try {
           MyServiceLocator locator = new MyServiceLocator();
           Activator service = locator.get();
           // If authorization is required
           //((MyService_Soap_BindingStub)service).setUsername("user3");
           //((MyService_Soap_BindingStub)service).setPassword("pass3");
           // invoke business method
           service.businessMethod();
      } catch (javax.xml.rpc.ServiceException ex) {
          ex.printStackTrace();
      } catch (java.rmi.RemoteException ex) {
          ex.printStackTrace();
      }
```
 

#### 13 .try 如:"abc".try
```java

 //生成try.....catch
        try {
            "abc"
        } catch (Exception e) {
          e.printStackTrace();
        }
 ```

#### 14. ifn
```java

  //生成判断是否为空  
      if (list == null) {
      }
```

#### 15. inn
```java

    ///生成判断是否不为空  
     if (list != null) {
     }
```

#### 16. fori
```java

    //生成简单for循环
      for (int i = 0; i < ; i++) { 
      }
 
```
#### 17. inst   
```java

//生成是否是该对象引用
    if (list instanceof Object) {
         Object o = (Object) list; 
    }
```

#### 18.psf
```java

   ///生成 共有 静态最终的
     public static final
 ```

#### 19. psfi 
```java

  ///生成 共有 静态最终的 int
    public static final int 
 ```

#### 20.psfs
```java

 ///生成 共有 静态最终的 String
    public static final String 
 ```

```java

```

```java

```
