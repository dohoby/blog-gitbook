---
title: mybatis-plus生成器相关问题
tags: []
date: 2020-12-10 20:28:06
categories:
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->


#### 实体名重新命名问题
数据库表名字建得不规范(缩写等),而实体名字和数据库表名字不同

参考：
[https://github.com/baomidou/mybatis-plus/blob/1ea2629a12344f77dd5aa318e4bbee96740cc8e5/mybatis-plus-generator/src/main/java/com/baomidou/mybatisplus/generator/config/INameConvert.java#L70-L75](https://github.com/baomidou/mybatis-plus/blob/1ea2629a12344f77dd5aa318e4bbee96740cc8e5/mybatis-plus-generator/src/main/java/com/baomidou/mybatisplus/generator/config/INameConvert.java#L70-L75)

[https://github.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-generator/src/main/java/com/baomidou/mybatisplus/generator/config/INameConvert.java](https://github.com/baomidou/mybatis-plus/blob/3.0/mybatis-plus-generator/src/main/java/com/baomidou/mybatisplus/generator/config/INameConvert.java)


#### 整合oracle 自增序列配置
oracle的id一般自己定义个sequence来自增，而不是数据库默认自增，所以实体id要弄成序列的

看如下代码：

```java
@TableName("T_USER")
@KeySequence(value = "SEQ_USER")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.INPUT)
    private Integer userId;
}
```

主要是：
- @KeySequence(value = "SEQ_USER")  
- @TableId(value = "USER_ID", type = IdType.INPUT)  
这2行代码,type必须指定为IdType.INPUT

使用时，mybatis-plus 3.0版本的必须配置下面注入注解id的默认生成实现

```java

    /**
     * Sequence主键自增
     *  https://blog.csdn.net/ancdc/article/details/86517796
     * @return 返回oracle自增类
     */
    @Bean
    public OracleKeyGenerator oracleKeyGenerator(){
        return new OracleKeyGenerator();
    }

```

参考：
[https://blog.csdn.net/ancdc/article/details/86517796](https://blog.csdn.net/ancdc/article/details/86517796)


#### 代码生成器自定义数据库表字段类型转换

mybatis-plus提供的oracle转换类如下：
com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert

其中处理数字类型的如下：

```java
   /**
     * 将对应的类型名称转换为对应的 java 类类型
     * <p>
     * String.valueOf(Integer.MAX_VALUE).length() == 10
     * Integer 不一定能装下 10 位的数字
     * <p>
     * String.valueOf(Long.MAX_VALUE).length() == 19
     * Long 不一定能装下 19 位的数字
     *
     * @param typeName 类型名称
     * @return 返回列类型
     */
    private static IColumnType toNumberType(String typeName) {
        if (typeName.matches("number\\([0-9]\\)")) {
            return DbColumnType.INTEGER;
        } else if (typeName.matches("number\\(1[0-8]\\)")) {
            return DbColumnType.LONG;
        }
        return DbColumnType.BIG_DECIMAL;
    }
```

在实际的生成代码上发现，若数据库定义NUMBER(15,2) （表示15位，后面有2位小数点）  
然后上面返回的数据类型都是BigDecimal，而在旧代码里定义的是double  
然后看了下，发现没有定义返回double类型的，所以只好重写了个方法返回double类型的

```java
     private static IColumnType toNumberType(String typeName) {
         if (typeName.matches("number\\([0-9]\\)")) {
             return DbColumnType.INTEGER;
         } else if (typeName.matches("number\\(1[0-8]\\)")) {
             return DbColumnType.LONG;
         }
         //TODO add on 20201208
         if (typeName.matches("number\\(1[0-8],[0-9]\\)")) {
             return DOUBLE;
         }
         if (typeName.matches("number\\(3[0-8],[0-9]\\)")) {
             return DOUBLE;
         }
         return DbColumnType.BIG_processTypeConvertDECIMAL;
     }
 ```


然后在引用上，发现自己重写了OracleTypeConvert中的processTypeConvert方法没有生效，
只好自己写了个类MyOracleTypeConvert，然后再引入

```java
 protected DataSourceConfig getOracleDataSourceConfig() {
        DataSourceConfig d = new DataSourceConfig()
                .setDbType(DbType.ORACLE)// 数据库类型
                /*.setTypeConvert(new OracleTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    public IColumnType processTypeConvert(String fieldType) {
                        System.out.println("转换类型：" + fieldType);
                        // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                        //    return DbColumnType.BOOLEAN;
                        // }
                        if (fieldType.toLowerCase().contains("number")) {
                            return AbstractMybatisPlusGenerator.toNumberType(fieldType);
                        }

                        return super.processTypeConvert(getGlobalConfig(), fieldType);
                    }
                })*/
                //TODO 上面重写processTypeConvert不生效，所以改为用自己的
                .setTypeConvert(new MyOracleTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    public IColumnType processTypeConvert(String fieldType) {
                        System.out.println("转换类型：" + fieldType);

                        return super.processTypeConvert(getGlobalConfig(), fieldType);
                    }
                })
                .setDriverName("oracle.jdbc.driver.OracleDriver")
                .setUsername(getDataBaseInfo().getUsername())
                .setPassword(getDataBaseInfo().getPassword())
                .setUrl(getDataBaseInfo().getUrl());
        if (!StringUtils.isEmpty(getDataBaseInfo().getSchemaname())) {
            //oracle的看使用处ConfigBuilder类中，会默认设置为用户名，也就是username
            d.setSchemaName(getDataBaseInfo().getSchemaname());
        }

        return d;
    }
```

参考：
[https://blog.csdn.net/kanglong129/article/details/98360631](https://blog.csdn.net/kanglong129/article/details/98360631)



