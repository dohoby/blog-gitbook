
title: redisTemplate配置问题
tags: []
date: 2020-12-23 21:48:59
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 问题：配置RedisTemplate,启动报错

看下面自定义配置的RedisTemplate，用了LettuceConnectionFactory(是RedisConnectionFactory的子类)

```java
   /**
     * redisTemplate 默认序列化使用的 jdkSerializeable, 存储二进制字节码, 所以一般需要自定义序列化类
     * https://www.cnblogs.com/puzhiwei/p/12519304.html 
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplateSerializer(LettuceConnectionFactory lettuceConnectionFactory) {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        RedisSerializer<?> stringSerializer = new StringRedisSerializer();

        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        // key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        // value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash key序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        // Hash value序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
```

报错日志：

```java
Parameter 0 of method redisTemplateSerializer in com.basic.config.RedisTemplateConfig required a bean of type 'org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory' that could not be found.

The following candidates were found but could not be injected:
	- Bean method 'redisConnectionFactory' in 'LettuceConnectionConfiguration' not loaded because @ConditionalOnMissingBean (types: org.springframework.data.redis.connection.RedisConnectionFactory; SearchStrategy: all) found beans of type 'org.springframework.data.redis.connection.RedisConnectionFactory' redissonConnectionFactory


Action:

Consider revisiting the entries above or defining a bean of type 'org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory' in your configuration.


```

### 原因分析：


看spring-boot-autoconfigure包下的RedisAutoConfiguration

org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration

其中用Import注解引入了LettuceConnectionConfiguration
```java

@Configuration(
    proxyBeanMethods = false
)
@ConditionalOnClass({RedisOperations.class})
@EnableConfigurationProperties({RedisProperties.class})
@Import({LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class})
public class RedisAutoConfiguration {
    public RedisAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean(
        name = {"redisTemplate"}
    )
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}

```

继续看LettuceConnectionConfiguration类
可以看到当缺失RedisConnectionFactory时，就会默认创建一个名称为redisConnectionFactory的bean，类型为LettuceConnectionFactory  
也就是这里会创建一个redisConnectionFactory
```java

    @Bean
    @ConditionalOnMissingBean({RedisConnectionFactory.class})
    LettuceConnectionFactory redisConnectionFactory(ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers, ClientResources clientResources) throws UnknownHostException {
        LettuceClientConfiguration clientConfig = this.getLettuceClientConfiguration(builderCustomizers, clientResources, this.getProperties().getLettuce().getPool());
        return this.createLettuceConnectionFactory(clientConfig);
    }
```

而上面配置用RedisTemplate时注入LettuceConnectionFactory类报错是因为同时使用了redisson,而redisson的自动装配也创建了redisConnectionFactory

看redisson-spring-boot-starter下的RedissonAutoConfiguration

org.redisson.spring.starter.RedissonAutoConfiguration

下面也会创建redissonConnectionFactory、RedisTemplate等，同时RedissonAutoConfiguration用了AutoConfigureBefore，会比  
RedisAutoConfiguration先装配

```java
@Configuration
@ConditionalOnClass({Redisson.class, RedisOperations.class})
@AutoConfigureBefore({RedisAutoConfiguration.class})
@EnableConfigurationProperties({RedissonProperties.class, RedisProperties.class})
public class RedissonAutoConfiguration {
    @Autowired
    private RedissonProperties redissonProperties;
    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private ApplicationContext ctx;

    public RedissonAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean(
        name = {"redisTemplate"}
    )
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean({StringRedisTemplate.class})
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    @ConditionalOnMissingBean({RedisConnectionFactory.class})
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

```


#### 解决

配置RedisTemplate，不要用LettuceConnectionFactory注入，直接用RedisConnectionFactory就可以
```java
    public RedisTemplate<String, Object> redisTemplateSerializer(RedisConnectionFactory redisConnectionFactory) {

```




