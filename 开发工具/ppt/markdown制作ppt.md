---
title: markdown制作ppt
tags: []
date: 2022-12-29 15:43:21
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### ppt内容
	
Spring中注册Bean的方式有哪些？		 
https://blog.csdn.net/Weixiaohuai/article/details/123210761




#### slidev教程
https://cn.sli.dev/guide/#scaffolding-your-first-presentation

新拉项目
```java
npm init slidev

```


启动
```java
npm run dev

```
打包
```java
npm run build

```
备注：打包完后去到dist文件夹下，直接cmd下输入anywhere即可访问静态页面

导出
```java
npm run export

```

图片要放在public目录下，引入以/开头
<div align=center>
<img src="/public/comment.png" class="m-30 h-60 rounded shadow center" style="" />
</div>

其中m-60 h-60调整图片的大小的

居中参考下面的
![评价](/public/comment.png)


https://blog.csdn.net/Jivove/article/details/123496699



#### 我的培训例子

---
# try also 'default' to start simple
theme: seriph
# random image from a curated Unsplash collection by Anthony
# like them? see https://unsplash.com/collections/94734566/slidev
background: https://source.unsplash.com/collection/94734566/1920x1080
# apply any windi css classes to the current slide
class: 'text-center'
# https://sli.dev/custom/highlighters.html
highlighter: shiki
# show line numbers in code blocks
lineNumbers: false
# some information about the slides, markdown enabled
info: |
  ## Slidev Starter Template
  Presentation slides for developers.

  Learn more at [Sli.dev](https://sli.dev)
# persist drawings in exports and build
drawings:
  persist: false
# use UnoCSS
css: unocss
---

# Bean的注册方式

主讲人：梁庆祥    

<div class="pt-12">
  <span @click="$slidev.nav.next" class="px-2 py-1 rounded cursor-pointer" hover="bg-white bg-opacity-10">
    begin <carbon:arrow-right class="inline"/>
  </span>
</div>


<div class="abs-br m-6 flex gap-2">
  <button @click="$slidev.nav.openInEditor()" title="Open in Editor" class="text-xl icon-btn opacity-50 !border-none !hover:text-white">
    <carbon:edit />
  </button>
  <a href="https://github.com/slidevjs/slidev" target="_blank" alt="GitHub"
    class="text-xl icon-btn opacity-50 !border-none !hover:text-white">
    <carbon-logo-github />
  </a>
</div>
<!--
The last comment block of each slide will be treated as slide notes. It will be visible and editable in Presenter Mode along with the slide. [Read more in the docs](https://sli.dev/guide/syntax.html#notes)
-->

---

# Bean注册几种方式?

<br>

- ① **XML文件配置** 
- ② **使用@Component注解 + @ComponentScan包扫描方式** 
- ③ **@Configuration + @Bean方式**
- ④ **使用FactoryBean**
- ⑤ **@Import方式** 
- ⑥ **@Import + ImportSelector方式** 
- ⑦ **@Import + ImportBeanDefinitionRegistrar方式**
- ⑧ **BeanDefinitionRegistryPostProcessor方式**
- ⑨ **BeanFactoryPostProcessor方式**

<br>
<br>

Read more about

<!--
You can have `style` tag in markdown to override the style for the current page.
Learn more: https://sli.dev/guide/syntax#embedded-styles
-->

<style>
h1 {
  background-color: #2B90B6;
  background-image: linear-gradient(45deg, #4EC5D4 10%, #146b8c 20%);
  background-size: 100%;
  -webkit-background-clip: text;
  -moz-background-clip: text;
  -webkit-text-fill-color: transparent;
  -moz-text-fill-color: transparent;
}
</style>
<!--
Here is another comment.
-->


---

# (1)XML文件配置

<br>

```java
class Student {
}

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="student" class="com.basic.test1.Student"/>

</beans>

public class Client {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        System.out.println(applicationContext.getBean("student"));
    }
}


```

<style>
.footnotes-sep {
  @apply mt-20 opacity-10;
}
.footnotes {
  @apply text-sm opacity-75;
}
.footnote-backref {
  display: none;
}
</style>

---

# (2)@Component注解 + @ComponentScan包扫描方式

<br>

```java {all}
@Component
class UserHandler {
}

@Service
 class UserService {
}

@Repository
 class UserDao {
}

@Controller
 class UserController {
}

@ComponentScan("com.basic.test2")
@Configuration
 class AppConfig {
}


```

---

# (3)@Configuration + @Bean方式

<br>

```java {all}
class Student {
}

@Configuration
class AppConfig {

    @Bean
    public Student student() {
        return new Student();
    }

}



```
---

# (4)使用FactoryBean

```java {all}
class User {
}

@Component
class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
}
@Configuration
@ComponentScan("com.basic.test4")
class AppConfig {
}



```
---

# (5)@Import方式

<br>

```java {all}
class Student {
}

@Import({Student.class})
@Configuration
class AppConfig {

}

```
---

# (6)@Import + ImportSelector方式

<br>

```java {all}
class Product {
}

class User {
}

class MyImportSelector implements ImportSelector {

    /// 指定需要定义bean的类名，注意要包含完整路径，而非相对路径
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.basic.test6.Product", "com.basic.test6.User"};
    }

}

@Import({MyImportSelector.class})
@Configuration
class AppConfig {

}

```
---

# (7)@Import + ImportBeanDefinitionRegistrar方式

```java {all}
class User {
}

class Product {
}

class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 可以自定义bean的名称、作用域等很多参数
        registry.registerBeanDefinition("user", new RootBeanDefinition(User.class));
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Product.class);
        rootBeanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        registry.registerBeanDefinition("product", rootBeanDefinition);
    }
}

@Import({CustomImportBeanDefinitionRegistrar.class})
@Configuration
class AppConfig {

}

```
---

# (8)BeanDefinitionRegistryPostProcessor方式

```java {all}
class User {
}

@Component
class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registry.registerBeanDefinition("user", new RootBeanDefinition(User.class));
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}

@Configuration
@ComponentScan("com.basic.test8")
class AppConfig {

}

```
---

# (9)BeanFactoryPostProcessor方式

<br>

```java {all}
class Product {
}

@Component
class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        DefaultListableBeanFactory registry = (DefaultListableBeanFactory) beanFactory;
        registry.registerBeanDefinition("product", new RootBeanDefinition(Product.class));
    }
}

@Configuration
@ComponentScan("com.basic.test9")
class AppConfig {

}

```

---


# Thanks

<div align=center>
<img src="/public/comment.png" class="m-30 h-60 rounded shadow center" style="" />
</div>



##  

<style>
 .center{
text-align: center;


    }

</style>

