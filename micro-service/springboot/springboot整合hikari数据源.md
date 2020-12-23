---
title: springboot整合hikari数据源
tags: []
date: 2020-12-18 10:33:49
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 缺少hikari相关配置问题

刚开始配置数据源是下面这样，hikari相关的没配置,其他也没配置

```java
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url = jdbc:oracle:thin:@ (DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.20.100.63)(PORT = 1521))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = fzsdbdev)))
spring.datasource.username = UIOTSYSTEM_DEV
spring.datasource.password = fzs123321
```

然后同事发现偶尔出现下面错误：
```java
Invalid Operation, NOT Connected). Possibly consider using a shorter maxLifetime value
```


#### 数据源自动装配分析



org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration


org.springframework.boot.autoconfigure.jdbc.DataSourceConfiguration.Hikari

```java

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnClass(HikariDataSource.class)
	@ConditionalOnMissingBean(DataSource.class)
	@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource",
			matchIfMissing = true)
	static class Hikari {

		@Bean
		@ConfigurationProperties(prefix = "spring.datasource.hikari")
		HikariDataSource dataSource(DataSourceProperties properties) {
			HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
			if (StringUtils.hasText(properties.getName())) {
				dataSource.setPoolName(properties.getName());
			}
			return dataSource;
		}

	}

```

com.zaxxer.hikari.HikariConfig
```java

```


[SpringBoot：关于默认连接池Hikari的源码剖析](https://www.cnblogs.com/fdzang/p/10315165.html)

[Hikari 数据库连接池配置详解](https://blog.csdn.net/long690276759/article/details/82259550)



![]()




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