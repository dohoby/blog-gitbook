---
title: springboot日志问题
tags: [springboot,日志,问题]
date: 2019-10-11 09:58:17
categories: 各种问题
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

#### 1、springboot启动报日志错误问题

```
"D:\Program Files\Java8\jdk1.8.0_77\bin\java.exe" -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:58693,suspend=y,server=n -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dspring.jmx.enabled=true -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true -javaagent:C:\Users\Administrator\.IntelliJIdea2019.2\system\captureAgent\debugger-agent.jar -Dfile.encoding=UTF-8 -classpath "D:\Program Files\Java8\jdk1.8.0_77\jre\lib\charsets.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\deploy.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\access-bridge-64.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\cldrdata.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\dnsns.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\jaccess.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\jfxrt.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\localedata.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\nashorn.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\sunec.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\sunjce_provider.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\sunmscapi.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\sunpkcs11.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\ext\zipfs.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\javaws.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\jce.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\jfr.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\jfxswt.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\jsse.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\management-agent.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\plugin.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\resources.jar;D:\Program Files\Java8\jdk1.8.0_77\jre\lib\rt.jar;D:\2\lqx-project-demo\basic-server\target\classes;D:\2\lqx-project-demo\demo\search\elasticsearch-starter\target\classes;D:\maven\repository\org\springframework\boot\spring-boot-autoconfigure\2.1.6.RELEASE\spring-boot-autoconfigure-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\boot\spring-boot-configuration-processor\2.1.6.RELEASE\spring-boot-configuration-processor-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-json\2.1.6.RELEASE\spring-boot-starter-json-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\spring-web\5.1.8.RELEASE\spring-web-5.1.8.RELEASE.jar;D:\maven\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.9.9\jackson-datatype-jdk8-2.9.9.jar;D:\maven\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.9.9\jackson-datatype-jsr310-2.9.9.jar;D:\maven\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.9.9\jackson-module-parameter-names-2.9.9.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-data-jpa\2.1.6.RELEASE\spring-boot-starter-data-jpa-2.1.6.RELEASE.jar;D:\maven\repository\javax\transaction\javax.transaction-api\1.3\javax.transaction-api-1.3.jar;D:\maven\repository\javax\xml\bind\jaxb-api\2.3.1\jaxb-api-2.3.1.jar;D:\maven\repository\javax\activation\javax.activation-api\1.2.0\javax.activation-api-1.2.0.jar;D:\maven\repository\org\hibernate\hibernate-core\5.3.10.Final\hibernate-core-5.3.10.Final.jar;D:\maven\repository\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;D:\maven\repository\antlr\antlr\2.7.7\antlr-2.7.7.jar;D:\maven\repository\org\jboss\jandex\2.0.5.Final\jandex-2.0.5.Final.jar;D:\maven\repository\org\dom4j\dom4j\2.1.1\dom4j-2.1.1.jar;D:\maven\repository\org\hibernate\common\hibernate-commons-annotations\5.0.4.Final\hibernate-commons-annotations-5.0.4.Final.jar;D:\maven\repository\org\springframework\data\spring-data-jpa\2.1.9.RELEASE\spring-data-jpa-2.1.9.RELEASE.jar;D:\maven\repository\org\springframework\data\spring-data-commons\2.1.9.RELEASE\spring-data-commons-2.1.9.RELEASE.jar;D:\maven\repository\org\springframework\spring-orm\5.1.8.RELEASE\spring-orm-5.1.8.RELEASE.jar;D:\maven\repository\org\springframework\spring-aspects\5.1.8.RELEASE\spring-aspects-5.1.8.RELEASE.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-web\2.1.6.RELEASE\spring-boot-starter-web-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-tomcat\2.1.6.RELEASE\spring-boot-starter-tomcat-2.1.6.RELEASE.jar;D:\maven\repository\org\apache\tomcat\embed\tomcat-embed-core\9.0.21\tomcat-embed-core-9.0.21.jar;D:\maven\repository\org\apache\tomcat\embed\tomcat-embed-el\9.0.21\tomcat-embed-el-9.0.21.jar;D:\maven\repository\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.21\tomcat-embed-websocket-9.0.21.jar;D:\maven\repository\org\hibernate\validator\hibernate-validator\6.0.17.Final\hibernate-validator-6.0.17.Final.jar;D:\maven\repository\javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar;D:\maven\repository\org\springframework\spring-webmvc\5.1.8.RELEASE\spring-webmvc-5.1.8.RELEASE.jar;D:\maven\repository\commons-codec\commons-codec\1.12\commons-codec-1.12.jar;D:\maven\repository\io\springfox\springfox-swagger2\2.7.0\springfox-swagger2-2.7.0.jar;D:\maven\repository\io\swagger\swagger-annotations\1.5.13\swagger-annotations-1.5.13.jar;D:\maven\repository\io\swagger\swagger-models\1.5.13\swagger-models-1.5.13.jar;D:\maven\repository\io\springfox\springfox-spi\2.7.0\springfox-spi-2.7.0.jar;D:\maven\repository\io\springfox\springfox-core\2.7.0\springfox-core-2.7.0.jar;D:\maven\repository\io\springfox\springfox-schema\2.7.0\springfox-schema-2.7.0.jar;D:\maven\repository\io\springfox\springfox-swagger-common\2.7.0\springfox-swagger-common-2.7.0.jar;D:\maven\repository\io\springfox\springfox-spring-web\2.7.0\springfox-spring-web-2.7.0.jar;D:\maven\repository\org\reflections\reflections\0.9.11\reflections-0.9.11.jar;D:\maven\repository\com\google\guava\guava\18.0\guava-18.0.jar;D:\maven\repository\com\fasterxml\classmate\1.4.0\classmate-1.4.0.jar;D:\maven\repository\org\springframework\plugin\spring-plugin-core\1.2.0.RELEASE\spring-plugin-core-1.2.0.RELEASE.jar;D:\maven\repository\org\springframework\plugin\spring-plugin-metadata\1.2.0.RELEASE\spring-plugin-metadata-1.2.0.RELEASE.jar;D:\maven\repository\org\mapstruct\mapstruct\1.1.0.Final\mapstruct-1.1.0.Final.jar;D:\maven\repository\io\springfox\springfox-swagger-ui\2.7.0\springfox-swagger-ui-2.7.0.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-log4j\1.3.5.RELEASE\spring-boot-starter-log4j-1.3.5.RELEASE.jar;D:\maven\repository\org\slf4j\slf4j-log4j12\1.7.26\slf4j-log4j12-1.7.26.jar;D:\maven\repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-data-redis\2.1.6.RELEASE\spring-boot-starter-data-redis-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\data\spring-data-redis\2.1.9.RELEASE\spring-data-redis-2.1.9.RELEASE.jar;D:\maven\repository\org\springframework\data\spring-data-keyvalue\2.1.9.RELEASE\spring-data-keyvalue-2.1.9.RELEASE.jar;D:\maven\repository\org\springframework\spring-oxm\5.1.8.RELEASE\spring-oxm-5.1.8.RELEASE.jar;D:\maven\repository\org\springframework\spring-context-support\5.1.8.RELEASE\spring-context-support-5.1.8.RELEASE.jar;D:\maven\repository\io\lettuce\lettuce-core\5.1.7.RELEASE\lettuce-core-5.1.7.RELEASE.jar;D:\maven\repository\com\alibaba\fastjson\1.2.44\fastjson-1.2.44.jar;D:\maven\repository\org\apache\httpcomponents\httpclient\4.4.1\httpclient-4.4.1.jar;D:\maven\repository\org\apache\httpcomponents\httpcore\4.4.11\httpcore-4.4.11.jar;D:\maven\repository\com\google\code\gson\gson\2.8.5\gson-2.8.5.jar;D:\maven\repository\org\elasticsearch\elasticsearch\6.4.3\elasticsearch-6.4.3.jar;D:\maven\repository\org\elasticsearch\elasticsearch-core\6.4.3\elasticsearch-core-6.4.3.jar;D:\maven\repository\org\elasticsearch\elasticsearch-secure-sm\6.4.3\elasticsearch-secure-sm-6.4.3.jar;D:\maven\repository\org\elasticsearch\elasticsearch-x-content\6.4.3\elasticsearch-x-content-6.4.3.jar;D:\maven\repository\com\fasterxml\jackson\dataformat\jackson-dataformat-smile\2.9.9\jackson-dataformat-smile-2.9.9.jar;D:\maven\repository\com\fasterxml\jackson\dataformat\jackson-dataformat-cbor\2.9.9\jackson-dataformat-cbor-2.9.9.jar;D:\maven\repository\org\apache\lucene\lucene-core\7.4.0\lucene-core-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-analyzers-common\7.4.0\lucene-analyzers-common-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-backward-codecs\7.4.0\lucene-backward-codecs-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-grouping\7.4.0\lucene-grouping-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-highlighter\7.4.0\lucene-highlighter-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-join\7.4.0\lucene-join-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-memory\7.4.0\lucene-memory-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-misc\7.4.0\lucene-misc-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-queries\7.4.0\lucene-queries-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-queryparser\7.4.0\lucene-queryparser-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-sandbox\7.4.0\lucene-sandbox-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-spatial\7.4.0\lucene-spatial-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-spatial-extras\7.4.0\lucene-spatial-extras-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-spatial3d\7.4.0\lucene-spatial3d-7.4.0.jar;D:\maven\repository\org\apache\lucene\lucene-suggest\7.4.0\lucene-suggest-7.4.0.jar;D:\maven\repository\org\elasticsearch\elasticsearch-cli\6.4.3\elasticsearch-cli-6.4.3.jar;D:\maven\repository\net\sf\jopt-simple\jopt-simple\5.0.2\jopt-simple-5.0.2.jar;D:\maven\repository\com\carrotsearch\hppc\0.7.1\hppc-0.7.1.jar;D:\maven\repository\joda-time\joda-time\2.10.2\joda-time-2.10.2.jar;D:\maven\repository\com\tdunning\t-digest\3.2\t-digest-3.2.jar;D:\maven\repository\org\hdrhistogram\HdrHistogram\2.1.9\HdrHistogram-2.1.9.jar;D:\maven\repository\org\apache\logging\log4j\log4j-api\2.11.2\log4j-api-2.11.2.jar;D:\maven\repository\org\elasticsearch\jna\4.5.1\jna-4.5.1.jar;D:\maven\repository\org\elasticsearch\client\rest\6.0.0-alpha1\rest-6.0.0-alpha1.jar;D:\maven\repository\org\apache\httpcomponents\httpasyncclient\4.1.4\httpasyncclient-4.1.4.jar;D:\maven\repository\org\apache\httpcomponents\httpcore-nio\4.4.11\httpcore-nio-4.4.11.jar;D:\maven\repository\org\elasticsearch\client\elasticsearch-rest-high-level-client\6.4.3\elasticsearch-rest-high-level-client-6.4.3.jar;D:\maven\repository\org\elasticsearch\client\elasticsearch-rest-client\6.4.3\elasticsearch-rest-client-6.4.3.jar;D:\maven\repository\org\elasticsearch\plugin\parent-join-client\6.4.3\parent-join-client-6.4.3.jar;D:\maven\repository\org\elasticsearch\plugin\aggs-matrix-stats-client\6.4.3\aggs-matrix-stats-client-6.4.3.jar;D:\maven\repository\org\elasticsearch\plugin\rank-eval-client\6.4.3\rank-eval-client-6.4.3.jar;D:\maven\repository\org\elasticsearch\plugin\lang-mustache-client\6.4.3\lang-mustache-client-6.4.3.jar;D:\maven\repository\com\github\spullara\mustache\java\compiler\0.9.3\compiler-0.9.3.jar;D:\maven\repository\org\elasticsearch\client\transport\6.4.3\transport-6.4.3.jar;D:\maven\repository\org\elasticsearch\plugin\reindex-client\6.4.3\reindex-client-6.4.3.jar;D:\maven\repository\org\elasticsearch\plugin\percolator-client\6.4.3\percolator-client-6.4.3.jar;D:\maven\repository\org\elasticsearch\plugin\transport-netty4-client\6.4.3\transport-netty4-client-6.4.3.jar;D:\maven\repository\io\netty\netty-codec-http\4.1.36.Final\netty-codec-http-4.1.36.Final.jar;D:\maven\repository\io\netty\netty-resolver\4.1.36.Final\netty-resolver-4.1.36.Final.jar;D:\maven\repository\org\ansj\ansj_seg\5.1.1\ansj_seg-5.1.1.jar;D:\maven\repository\org\nlpcn\nlp-lang\1.7.2\nlp-lang-1.7.2.jar;D:\maven\repository\org\apache\httpcomponents\httpmime\4.5.6\httpmime-4.5.6.jar;D:\maven\repository\org\jsoup\jsoup\1.11.2\jsoup-1.11.2.jar;D:\maven\repository\javax\servlet\javax.servlet-api\4.0.1\javax.servlet-api-4.0.1.jar;D:\maven\repository\javax\persistence\javax.persistence-api\2.2\javax.persistence-api-2.2.jar;D:\maven\repository\org\apache\shiro\shiro-core\1.4.0\shiro-core-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-lang\1.4.0\shiro-lang-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-cache\1.4.0\shiro-cache-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-crypto-hash\1.4.0\shiro-crypto-hash-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-crypto-core\1.4.0\shiro-crypto-core-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-crypto-cipher\1.4.0\shiro-crypto-cipher-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-config-core\1.4.0\shiro-config-core-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-config-ogdl\1.4.0\shiro-config-ogdl-1.4.0.jar;D:\maven\repository\commons-beanutils\commons-beanutils\1.9.3\commons-beanutils-1.9.3.jar;D:\maven\repository\org\apache\shiro\shiro-event\1.4.0\shiro-event-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-web\1.4.0\shiro-web-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-spring\1.4.0\shiro-spring-1.4.0.jar;D:\maven\repository\org\apache\shiro\shiro-ehcache\1.4.0\shiro-ehcache-1.4.0.jar;D:\maven\repository\net\sf\ehcache\ehcache-core\2.6.11\ehcache-core-2.6.11.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-amqp\2.1.6.RELEASE\spring-boot-starter-amqp-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\spring-messaging\5.1.8.RELEASE\spring-messaging-5.1.8.RELEASE.jar;D:\maven\repository\org\springframework\spring-beans\5.1.8.RELEASE\spring-beans-5.1.8.RELEASE.jar;D:\maven\repository\org\springframework\amqp\spring-rabbit\2.1.7.RELEASE\spring-rabbit-2.1.7.RELEASE.jar;D:\maven\repository\org\springframework\amqp\spring-amqp\2.1.7.RELEASE\spring-amqp-2.1.7.RELEASE.jar;D:\maven\repository\org\springframework\retry\spring-retry\1.2.4.RELEASE\spring-retry-1.2.4.RELEASE.jar;D:\maven\repository\com\rabbitmq\amqp-client\5.4.3\amqp-client-5.4.3.jar;D:\maven\repository\org\springframework\spring-tx\5.1.8.RELEASE\spring-tx-5.1.8.RELEASE.jar;D:\maven\repository\org\projectlombok\lombok\1.16.10\lombok-1.16.10.jar;D:\maven\repository\org\redisson\redisson\3.11.2\redisson-3.11.2.jar;D:\maven\repository\io\netty\netty-common\4.1.36.Final\netty-common-4.1.36.Final.jar;D:\maven\repository\io\netty\netty-codec\4.1.36.Final\netty-codec-4.1.36.Final.jar;D:\maven\repository\io\netty\netty-buffer\4.1.36.Final\netty-buffer-4.1.36.Final.jar;D:\maven\repository\io\netty\netty-transport\4.1.36.Final\netty-transport-4.1.36.Final.jar;D:\maven\repository\io\netty\netty-resolver-dns\4.1.36.Final\netty-resolver-dns-4.1.36.Final.jar;D:\maven\repository\io\netty\netty-codec-dns\4.1.36.Final\netty-codec-dns-4.1.36.Final.jar;D:\maven\repository\io\netty\netty-handler\4.1.36.Final\netty-handler-4.1.36.Final.jar;D:\maven\repository\javax\cache\cache-api\1.1.1\cache-api-1.1.1.jar;D:\maven\repository\io\projectreactor\reactor-core\3.2.10.RELEASE\reactor-core-3.2.10.RELEASE.jar;D:\maven\repository\org\reactivestreams\reactive-streams\1.0.2\reactive-streams-1.0.2.jar;D:\maven\repository\io\reactivex\rxjava2\rxjava\2.2.9\rxjava-2.2.9.jar;D:\maven\repository\de\ruedigermoeller\fst\2.57\fst-2.57.jar;D:\maven\repository\org\javassist\javassist\3.21.0-GA\javassist-3.21.0-GA.jar;D:\maven\repository\org\objenesis\objenesis\2.5.1\objenesis-2.5.1.jar;D:\maven\repository\com\fasterxml\jackson\dataformat\jackson-dataformat-yaml\2.9.9\jackson-dataformat-yaml-2.9.9.jar;D:\maven\repository\com\fasterxml\jackson\core\jackson-core\2.9.9\jackson-core-2.9.9.jar;D:\maven\repository\com\fasterxml\jackson\core\jackson-databind\2.9.9\jackson-databind-2.9.9.jar;D:\maven\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;D:\maven\repository\net\bytebuddy\byte-buddy\1.9.13\byte-buddy-1.9.13.jar;D:\maven\repository\org\jodd\jodd-bean\5.0.10\jodd-bean-5.0.10.jar;D:\maven\repository\org\jodd\jodd-core\5.0.10\jodd-core-5.0.10.jar;D:\maven\repository\org\redisson\redisson-spring-boot-starter\3.11.2\redisson-spring-boot-starter-3.11.2.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-actuator\2.1.6.RELEASE\spring-boot-starter-actuator-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\boot\spring-boot-actuator-autoconfigure\2.1.6.RELEASE\spring-boot-actuator-autoconfigure-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\boot\spring-boot-actuator\2.1.6.RELEASE\spring-boot-actuator-2.1.6.RELEASE.jar;D:\maven\repository\io\micrometer\micrometer-core\1.1.5\micrometer-core-1.1.5.jar;D:\maven\repository\org\latencyutils\LatencyUtils\2.0.3\LatencyUtils-2.0.3.jar;D:\maven\repository\org\redisson\redisson-spring-data-21\3.11.2\redisson-spring-data-21-3.11.2.jar;D:\maven\repository\org\apache\poi\poi\4.0.0\poi-4.0.0.jar;D:\maven\repository\org\apache\poi\poi-ooxml\4.0.0\poi-ooxml-4.0.0.jar;D:\maven\repository\org\apache\poi\poi-ooxml-schemas\4.0.0\poi-ooxml-schemas-4.0.0.jar;D:\maven\repository\org\apache\xmlbeans\xmlbeans\3.0.1\xmlbeans-3.0.1.jar;D:\maven\repository\org\apache\commons\commons-compress\1.18\commons-compress-1.18.jar;D:\maven\repository\com\github\virtuald\curvesapi\1.04\curvesapi-1.04.jar;D:\maven\repository\com\xuxueli\xxl-job-core\1.8.2\xxl-job-core-1.8.2.jar;D:\maven\repository\javax\servlet\jsp\jsp-api\2.2\jsp-api-2.2.jar;D:\maven\repository\org\eclipse\jetty\jetty-server\9.4.19.v20190610\jetty-server-9.4.19.v20190610.jar;D:\maven\repository\org\eclipse\jetty\jetty-http\9.4.19.v20190610\jetty-http-9.4.19.v20190610.jar;D:\maven\repository\org\eclipse\jetty\jetty-util\9.4.19.v20190610\jetty-util-9.4.19.v20190610.jar;D:\maven\repository\org\eclipse\jetty\jetty-io\9.4.19.v20190610\jetty-io-9.4.19.v20190610.jar;D:\maven\repository\com\caucho\hessian\4.0.38\hessian-4.0.38.jar;D:\maven\repository\org\codehaus\jackson\jackson-mapper-asl\1.9.13\jackson-mapper-asl-1.9.13.jar;D:\maven\repository\org\codehaus\jackson\jackson-core-asl\1.9.13\jackson-core-asl-1.9.13.jar;D:\maven\repository\org\springframework\spring-context\5.1.8.RELEASE\spring-context-5.1.8.RELEASE.jar;D:\maven\repository\org\springframework\spring-aop\5.1.8.RELEASE\spring-aop-5.1.8.RELEASE.jar;D:\maven\repository\org\springframework\spring-expression\5.1.8.RELEASE\spring-expression-5.1.8.RELEASE.jar;D:\maven\repository\org\codehaus\groovy\groovy-all\2.4.5\groovy-all-2.4.5.jar;D:\maven\repository\org\apache\commons\commons-exec\1.3\commons-exec-1.3.jar;D:\maven\repository\com\belerweb\pinyin4j\2.5.0\pinyin4j-2.5.0.jar;D:\maven\repository\org\slf4j\slf4j-api\1.7.26\slf4j-api-1.7.26.jar;D:\maven\repository\org\slf4j\jcl-over-slf4j\1.7.26\jcl-over-slf4j-1.7.26.jar;D:\maven\repository\org\slf4j\jul-to-slf4j\1.7.26\jul-to-slf4j-1.7.26.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter\2.1.6.RELEASE\spring-boot-starter-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\boot\spring-boot\2.1.6.RELEASE\spring-boot-2.1.6.RELEASE.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-logging\2.1.6.RELEASE\spring-boot-starter-logging-2.1.6.RELEASE.jar;D:\maven\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;D:\maven\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;D:\maven\repository\org\apache\logging\log4j\log4j-to-slf4j\2.11.2\log4j-to-slf4j-2.11.2.jar;D:\maven\repository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;D:\maven\repository\org\springframework\spring-core\5.1.8.RELEASE\spring-core-5.1.8.RELEASE.jar;D:\maven\repository\org\springframework\spring-jcl\5.1.8.RELEASE\spring-jcl-5.1.8.RELEASE.jar;D:\maven\repository\org\yaml\snakeyaml\1.23\snakeyaml-1.23.jar;D:\maven\repository\redis\clients\jedis\3.0.1\jedis-3.0.1.jar;D:\maven\repository\org\apache\commons\commons-pool2\2.6.2\commons-pool2-2.6.2.jar;D:\maven\repository\com\baomidou\mybatis-plus-boot-starter\3.1.2\mybatis-plus-boot-starter-3.1.2.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-jdbc\2.1.6.RELEASE\spring-boot-starter-jdbc-2.1.6.RELEASE.jar;D:\maven\repository\com\zaxxer\HikariCP\3.2.0\HikariCP-3.2.0.jar;D:\maven\repository\org\springframework\spring-jdbc\5.1.8.RELEASE\spring-jdbc-5.1.8.RELEASE.jar;D:\maven\repository\com\baomidou\mybatis-plus\3.1.2\mybatis-plus-3.1.2.jar;D:\maven\repository\com\baomidou\mybatis-plus-generator\3.1.2\mybatis-plus-generator-3.1.2.jar;D:\maven\repository\com\alibaba\druid\1.1.18\druid-1.1.18.jar;D:\maven\repository\com\alibaba\druid-spring-boot-starter\1.1.18\druid-spring-boot-starter-1.1.18.jar;D:\maven\repository\org\json\json\20180130\json-20180130.jar;D:\maven\repository\org\apache\oltu\oauth2\org.apache.oltu.oauth2.client\0.31\org.apache.oltu.oauth2.client-0.31.jar;D:\maven\repository\org\apache\oltu\oauth2\org.apache.oltu.oauth2.common\0.31\org.apache.oltu.oauth2.common-0.31.jar;D:\maven\repository\org\codehaus\jettison\jettison\1.2\jettison-1.2.jar;D:\maven\repository\com\squareup\okhttp3\okhttp\3.8.1\okhttp-3.8.1.jar;D:\maven\repository\com\squareup\okio\okio\1.13.0\okio-1.13.0.jar;D:\maven\repository\org\apache\commons\commons-collections4\4.4\commons-collections4-4.4.jar;D:\maven\repository\org\apache\commons\commons-vfs2\2.1\commons-vfs2-2.1.jar;D:\maven\repository\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;D:\maven\repository\org\apache\commons\commons-lang3\3.4\commons-lang3-3.4.jar;D:\maven\repository\commons-collections\commons-collections\3.1\commons-collections-3.1.jar;D:\maven\repository\commons-fileupload\commons-fileupload\1.3.1\commons-fileupload-1.3.1.jar;D:\maven\repository\commons-io\commons-io\2.2\commons-io-2.2.jar;D:\maven\repository\com\baomidou\mybatis-plus-extension\3.1.2\mybatis-plus-extension-3.1.2.jar;D:\maven\repository\com\baomidou\mybatis-plus-core\3.1.2\mybatis-plus-core-3.1.2.jar;D:\maven\repository\com\baomidou\mybatis-plus-annotation\3.1.2\mybatis-plus-annotation-3.1.2.jar;D:\maven\repository\com\github\jsqlparser\jsqlparser\1.2\jsqlparser-1.2.jar;D:\maven\repository\org\mybatis\mybatis\3.5.1\mybatis-3.5.1.jar;D:\maven\repository\org\mybatis\mybatis-spring\2.0.1\mybatis-spring-2.0.1.jar;D:\maven\repository\com\baomidou\dynamic-datasource-spring-boot-starter\2.5.5\dynamic-datasource-spring-boot-starter-2.5.5.jar;D:\maven\repository\org\springframework\boot\spring-boot-starter-aop\2.1.6.RELEASE\spring-boot-starter-aop-2.1.6.RELEASE.jar;D:\maven\repository\org\aspectj\aspectjweaver\1.9.4\aspectjweaver-1.9.4.jar;D:\IntelliJ IDEA 2019.2.1\lib\idea_rt.jar" com.basic.BasicServerApplication
Connected to the target VM, address: '127.0.0.1:58693', transport: 'socket'
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/D:/maven/repository/org/slf4j/slf4j-log4j12/1.7.26/slf4j-log4j12-1.7.26.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/D:/maven/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.Log4jLoggerFactory]
Exception in thread "main" java.lang.IllegalArgumentException: LoggerFactory is not a Logback LoggerContext but Logback is on the classpath. Either remove Logback or the competing implementation (class org.slf4j.impl.Log4jLoggerFactory loaded from file:/D:/maven/repository/org/slf4j/slf4j-log4j12/1.7.26/slf4j-log4j12-1.7.26.jar). If you are using WebLogic you will need to add 'org.slf4j' to prefer-application-packages in WEB-INF/weblogic.xml: org.slf4j.impl.Log4jLoggerFactory
	at org.springframework.util.Assert.instanceCheckFailed(Assert.java:655)
	at org.springframework.util.Assert.isInstanceOf(Assert.java:555)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.getLoggerContext(LogbackLoggingSystem.java:279)
	at org.springframework.boot.logging.logback.LogbackLoggingSystem.beforeInitialize(LogbackLoggingSystem.java:103)
	at org.springframework.boot.context.logging.LoggingApplicationListener.onApplicationStartingEvent(LoggingApplicationListener.java:212)
	at org.springframework.boot.context.logging.LoggingApplicationListener.onApplicationEvent(LoggingApplicationListener.java:193)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener(SimpleApplicationEventMulticaster.java:172)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener(SimpleApplicationEventMulticaster.java:165)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:139)
	at org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent(SimpleApplicationEventMulticaster.java:127)
	at org.springframework.boot.context.event.EventPublishingRunListener.starting(EventPublishingRunListener.java:69)
	at org.springframework.boot.SpringApplicationRunListeners.starting(SpringApplicationRunListeners.java:47)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:301)
	at com.basic.BasicServerApplication.main(BasicServerApplication.java:30)
Disconnected from the target VM, address: '127.0.0.1:58693', transport: 'socket'

Process finished with exit code 1

```

#### 2、代码分析
通过点击报错日志里面的具体报错类，查找到对应LogbackLoggingSystem.java下面的代码				

```
	private LoggerContext getLoggerContext() {
		ILoggerFactory factory = StaticLoggerBinder.getSingleton().getLoggerFactory();
		Assert.isInstanceOf(LoggerContext.class, factory,
				String.format(
						"LoggerFactory is not a Logback LoggerContext but Logback is on "
								+ "the classpath. Either remove Logback or the competing "
								+ "implementation (%s loaded from %s). If you are using "
								+ "WebLogic you will need to add 'org.slf4j' to "
								+ "prefer-application-packages in WEB-INF/weblogic.xml",
						factory.getClass(), getLocation(factory)));
		return (LoggerContext) factory;
	}

private Object getLocation(ILoggerFactory factory) {
		try {
			ProtectionDomain protectionDomain = factory.getClass().getProtectionDomain();
			CodeSource codeSource = protectionDomain.getCodeSource();
			if (codeSource != null) {
				return codeSource.getLocation();
			}
		}
		catch (SecurityException ex) {
			// Unable to determine location
		}
		return "unknown location";
	}
```
```
	public static void isInstanceOf(Class<?> type, @Nullable Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			instanceCheckFailed(type, obj, message);
		}
	}

```



代码分析如下：
1. LoggerContext这个通过import导入的包发现是logback的，就是logback-classic:jar:1.2.3.jar下的
2. factory通过getLocation获取到的结果是file:/D:/maven/repository/org/slf4j/slf4j-log4j12/1.7.26/slf4j-log4j12-1.7.26.jar路径下的
3. 通过Assert.isInstanceOf(LoggerContext.class, factory比较发现这2者不是对应的关系
所以报String.format(
   						"LoggerFactory is not a Logback LoggerContext but Logback is on "
   								+ "the classpath. Either remove Logback or the competing "
   								+ "implementation (%s loaded from %s). If you are using "
   								+ "WebLogic you will need to add 'org.slf4j' to "
   								+ "prefer-application-packages in WEB-INF/weblogic.xml",
   						factory.getClass(), getLocation(factory)))
这段错误，也就是日志打印出来的错误   

![jenkins](springboot日志问题/springboot日志问题-1.png)

看maven仓库也会发现也存在slf4j-log4j12对应的包
![jenkins](springboot日志问题/springboot日志问题-2.png)

4. 


#### 3、解决

1. 去到对应项目下，执行mvn dependency:tree,结果如下
2. 搜索log4j或搜索slf4j-log4j12，看到存在slf4j-log4j12-1.7.26.jar这个jar包,看到他是在elasitcsearch-starter这个项目下的
```
[INFO] +- com.basic.search:elasitcsearch-starter:jar:1.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-log4j:jar:1.3.5.RELEASE:compile
[INFO] |  |  +- org.slf4j:slf4j-log4j12:jar:1.7.26:compile
```
3. 查看elasitcsearch-starter这个项目的pom.xml，看到引入了spring-boot-starter-log4j，问题就在这里了，这里会依赖slf4j-log4j12
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j</artifactId>
            <version>${spring-log4j.version}</version>
        </dependency>
```

4. 解决办法：引用elasitcsearch-starter时排除掉slf4j-log4j12即可
```
        <dependency>
            <groupId>com.basic.search</groupId>
            <artifactId>elasitcsearch-starter</artifactId>
            <version>1.0.0</version>
            <exclusions>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-log4j12</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
```

5. 附注：未解决之前项目依赖如下：

```
λ mvn dependency:tree
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building basic-server 2.0.2
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-dependency-plugin:3.1.1:tree (default-cli) @ basic-server ---
[INFO] com.basic:basic-server:jar:2.0.2
[INFO] +- com.basic.search:elasitcsearch-starter:jar:1.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-autoconfigure:jar:2.1.6.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-configuration-processor:jar:2.1.6.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:2.1.6.RELEASE:compile
[INFO] |  |  +- org.springframework:spring-web:jar:5.1.8.RELEASE:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.9.9:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.9.9:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.9.9:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-jpa:jar:2.1.6.RELEASE:compile
[INFO] |  |  +- javax.transaction:javax.transaction-api:jar:1.3:compile
[INFO] |  |  +- javax.xml.bind:jaxb-api:jar:2.3.1:compile
[INFO] |  |  |  \- javax.activation:javax.activation-api:jar:1.2.0:compile
[INFO] |  |  +- org.hibernate:hibernate-core:jar:5.3.10.Final:compile
[INFO] |  |  |  +- org.jboss.logging:jboss-logging:jar:3.3.2.Final:compile
[INFO] |  |  |  +- antlr:antlr:jar:2.7.7:compile
[INFO] |  |  |  +- org.jboss:jandex:jar:2.0.5.Final:compile
[INFO] |  |  |  +- org.dom4j:dom4j:jar:2.1.1:compile
[INFO] |  |  |  \- org.hibernate.common:hibernate-commons-annotations:jar:5.0.4.Final:compile
[INFO] |  |  +- org.springframework.data:spring-data-jpa:jar:2.1.9.RELEASE:compile
[INFO] |  |  |  +- org.springframework.data:spring-data-commons:jar:2.1.9.RELEASE:compile
[INFO] |  |  |  \- org.springframework:spring-orm:jar:5.1.8.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-aspects:jar:5.1.8.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-web:jar:2.1.6.RELEASE:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-tomcat:jar:2.1.6.RELEASE:compile
[INFO] |  |  |  +- org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.21:compile
[INFO] |  |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:9.0.21:compile
[INFO] |  |  |  \- org.apache.tomcat.embed:tomcat-embed-websocket:jar:9.0.21:compile
[INFO] |  |  +- org.hibernate.validator:hibernate-validator:jar:6.0.17.Final:compile
[INFO] |  |  |  \- javax.validation:validation-api:jar:2.0.1.Final:compile
[INFO] |  |  \- org.springframework:spring-webmvc:jar:5.1.8.RELEASE:compile
[INFO] |  +- commons-codec:commons-codec:jar:1.12:compile
[INFO] |  +- io.springfox:springfox-swagger2:jar:2.7.0:compile
[INFO] |  |  +- io.swagger:swagger-annotations:jar:1.5.13:compile
[INFO] |  |  +- io.swagger:swagger-models:jar:1.5.13:compile
[INFO] |  |  +- io.springfox:springfox-spi:jar:2.7.0:compile
[INFO] |  |  |  \- io.springfox:springfox-core:jar:2.7.0:compile
[INFO] |  |  +- io.springfox:springfox-schema:jar:2.7.0:compile
[INFO] |  |  +- io.springfox:springfox-swagger-common:jar:2.7.0:compile
[INFO] |  |  +- io.springfox:springfox-spring-web:jar:2.7.0:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  +- com.google.guava:guava:jar:18.0:compile
[INFO] |  |  +- com.fasterxml:classmate:jar:1.4.0:compile
[INFO] |  |  +- org.springframework.plugin:spring-plugin-core:jar:1.2.0.RELEASE:compile
[INFO] |  |  +- org.springframework.plugin:spring-plugin-metadata:jar:1.2.0.RELEASE:compile
[INFO] |  |  \- org.mapstruct:mapstruct:jar:1.1.0.Final:compile
[INFO] |  +- io.springfox:springfox-swagger-ui:jar:2.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-log4j:jar:1.3.5.RELEASE:compile
[INFO] |  |  +- org.slf4j:slf4j-log4j12:jar:1.7.26:compile
[INFO] |  |  \- log4j:log4j:jar:1.2.17:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:2.1.6.RELEASE:compile
[INFO] |  |  +- org.springframework.data:spring-data-redis:jar:2.1.9.RELEASE:compile
[INFO] |  |  |  +- org.springframework.data:spring-data-keyvalue:jar:2.1.9.RELEASE:compile
[INFO] |  |  |  +- org.springframework:spring-oxm:jar:5.1.8.RELEASE:compile
[INFO] |  |  |  \- org.springframework:spring-context-support:jar:5.1.8.RELEASE:compile
[INFO] |  |  \- io.lettuce:lettuce-core:jar:5.1.7.RELEASE:compile
[INFO] |  +- com.alibaba:fastjson:jar:1.2.44:compile
[INFO] |  +- org.apache.httpcomponents:httpclient:jar:4.4.1:compile
[INFO] |  |  \- org.apache.httpcomponents:httpcore:jar:4.4.11:compile
[INFO] |  +- com.google.code.gson:gson:jar:2.8.5:compile
[INFO] |  +- org.elasticsearch:elasticsearch:jar:6.4.3:compile
[INFO] |  |  +- org.elasticsearch:elasticsearch-core:jar:6.4.3:compile
[INFO] |  |  +- org.elasticsearch:elasticsearch-secure-sm:jar:6.4.3:compile
[INFO] |  |  +- org.elasticsearch:elasticsearch-x-content:jar:6.4.3:compile
[INFO] |  |  |  +- com.fasterxml.jackson.dataformat:jackson-dataformat-smile:jar:2.9.9:compile
[INFO] |  |  |  \- com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:jar:2.9.9:compile
[INFO] |  |  +- org.apache.lucene:lucene-core:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-analyzers-common:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-backward-codecs:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-grouping:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-highlighter:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-join:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-memory:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-misc:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-queries:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-queryparser:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-sandbox:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-spatial:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-spatial-extras:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-spatial3d:jar:7.4.0:compile
[INFO] |  |  +- org.apache.lucene:lucene-suggest:jar:7.4.0:compile
[INFO] |  |  +- org.elasticsearch:elasticsearch-cli:jar:6.4.3:compile
[INFO] |  |  |  \- net.sf.jopt-simple:jopt-simple:jar:5.0.2:compile
[INFO] |  |  +- com.carrotsearch:hppc:jar:0.7.1:compile
[INFO] |  |  +- joda-time:joda-time:jar:2.10.2:compile
[INFO] |  |  +- com.tdunning:t-digest:jar:3.2:compile
[INFO] |  |  +- org.hdrhistogram:HdrHistogram:jar:2.1.9:compile
[INFO] |  |  +- org.apache.logging.log4j:log4j-api:jar:2.11.2:compile
[INFO] |  |  \- org.elasticsearch:jna:jar:4.5.1:compile
[INFO] |  +- org.elasticsearch.client:rest:jar:6.0.0-alpha1:compile
[INFO] |  |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.4:compile
[INFO] |  |  \- org.apache.httpcomponents:httpcore-nio:jar:4.4.11:compile
[INFO] |  +- org.elasticsearch.client:elasticsearch-rest-high-level-client:jar:6.4.3:compile
[INFO] |  |  +- org.elasticsearch.client:elasticsearch-rest-client:jar:6.4.3:compile
[INFO] |  |  +- org.elasticsearch.plugin:parent-join-client:jar:6.4.3:compile
[INFO] |  |  +- org.elasticsearch.plugin:aggs-matrix-stats-client:jar:6.4.3:compile
[INFO] |  |  +- org.elasticsearch.plugin:rank-eval-client:jar:6.4.3:compile
[INFO] |  |  \- org.elasticsearch.plugin:lang-mustache-client:jar:6.4.3:compile
[INFO] |  |     \- com.github.spullara.mustache.java:compiler:jar:0.9.3:compile
[INFO] |  +- org.elasticsearch.client:transport:jar:6.4.3:compile
[INFO] |  |  +- org.elasticsearch.plugin:reindex-client:jar:6.4.3:compile
[INFO] |  |  \- org.elasticsearch.plugin:percolator-client:jar:6.4.3:compile
[INFO] |  +- org.elasticsearch.plugin:transport-netty4-client:jar:6.4.3:compile
[INFO] |  |  +- io.netty:netty-codec-http:jar:4.1.36.Final:compile
[INFO] |  |  \- io.netty:netty-resolver:jar:4.1.36.Final:compile
[INFO] |  +- org.ansj:ansj_seg:jar:5.1.1:compile
[INFO] |  |  \- org.nlpcn:nlp-lang:jar:1.7.2:compile
[INFO] |  +- org.apache.httpcomponents:httpmime:jar:4.5.6:compile
[INFO] |  +- org.jsoup:jsoup:jar:1.11.2:compile
[INFO] |  +- javax.servlet:javax.servlet-api:jar:4.0.1:compile
[INFO] |  \- javax.persistence:javax.persistence-api:jar:2.2:compile
[INFO] +- org.postgresql:postgresql:jar:42.2.2:compile
[INFO] +- org.apache.shiro:shiro-core:jar:1.4.0:compile
[INFO] |  +- org.apache.shiro:shiro-lang:jar:1.4.0:compile
[INFO] |  +- org.apache.shiro:shiro-cache:jar:1.4.0:compile
[INFO] |  +- org.apache.shiro:shiro-crypto-hash:jar:1.4.0:compile
[INFO] |  |  \- org.apache.shiro:shiro-crypto-core:jar:1.4.0:compile
[INFO] |  +- org.apache.shiro:shiro-crypto-cipher:jar:1.4.0:compile
[INFO] |  +- org.apache.shiro:shiro-config-core:jar:1.4.0:compile
[INFO] |  +- org.apache.shiro:shiro-config-ogdl:jar:1.4.0:compile
[INFO] |  |  \- commons-beanutils:commons-beanutils:jar:1.9.3:compile
[INFO] |  \- org.apache.shiro:shiro-event:jar:1.4.0:compile
[INFO] +- org.apache.shiro:shiro-web:jar:1.4.0:compile
[INFO] +- org.apache.shiro:shiro-spring:jar:1.4.0:compile
[INFO] +- org.apache.shiro:shiro-ehcache:jar:1.4.0:compile
[INFO] |  \- net.sf.ehcache:ehcache-core:jar:2.6.11:compile
[INFO] +- org.springframework.boot:spring-boot-starter-amqp:jar:2.1.6.RELEASE:compile
[INFO] |  +- org.springframework:spring-messaging:jar:5.1.8.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-beans:jar:5.1.8.RELEASE:compile
[INFO] |  \- org.springframework.amqp:spring-rabbit:jar:2.1.7.RELEASE:compile
[INFO] |     +- org.springframework.amqp:spring-amqp:jar:2.1.7.RELEASE:compile
[INFO] |     |  \- org.springframework.retry:spring-retry:jar:1.2.4.RELEASE:compile
[INFO] |     +- com.rabbitmq:amqp-client:jar:5.4.3:compile
[INFO] |     \- org.springframework:spring-tx:jar:5.1.8.RELEASE:compile
[INFO] +- org.projectlombok:lombok:jar:1.16.10:compile
[INFO] +- org.redisson:redisson:jar:3.11.2:compile
[INFO] |  +- io.netty:netty-common:jar:4.1.36.Final:compile
[INFO] |  +- io.netty:netty-codec:jar:4.1.36.Final:compile
[INFO] |  +- io.netty:netty-buffer:jar:4.1.36.Final:compile
[INFO] |  +- io.netty:netty-transport:jar:4.1.36.Final:compile
[INFO] |  +- io.netty:netty-resolver-dns:jar:4.1.36.Final:compile
[INFO] |  |  \- io.netty:netty-codec-dns:jar:4.1.36.Final:compile
[INFO] |  +- io.netty:netty-handler:jar:4.1.36.Final:compile
[INFO] |  +- javax.cache:cache-api:jar:1.1.1:compile
[INFO] |  +- io.projectreactor:reactor-core:jar:3.2.10.RELEASE:compile
[INFO] |  |  \- org.reactivestreams:reactive-streams:jar:1.0.2:compile
[INFO] |  +- io.reactivex.rxjava2:rxjava:jar:2.2.9:compile
[INFO] |  +- de.ruedigermoeller:fst:jar:2.57:compile
[INFO] |  |  +- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  \- org.objenesis:objenesis:jar:2.5.1:compile
[INFO] |  +- com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:jar:2.9.9:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-core:jar:2.9.9:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.9.9:compile
[INFO] |  |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.9.0:compile
[INFO] |  +- net.bytebuddy:byte-buddy:jar:1.9.13:compile
[INFO] |  \- org.jodd:jodd-bean:jar:5.0.10:compile
[INFO] |     \- org.jodd:jodd-core:jar:5.0.10:compile
[INFO] +- org.redisson:redisson-spring-boot-starter:jar:3.11.2:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-actuator:jar:2.1.6.RELEASE:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:2.1.6.RELEASE:compile
[INFO] |  |  |  \- org.springframework.boot:spring-boot-actuator:jar:2.1.6.RELEASE:compile
[INFO] |  |  \- io.micrometer:micrometer-core:jar:1.1.5:compile
[INFO] |  |     \- org.latencyutils:LatencyUtils:jar:2.0.3:compile
[INFO] |  \- org.redisson:redisson-spring-data-21:jar:3.11.2:compile
[INFO] +- org.apache.poi:poi:jar:4.0.0:compile
[INFO] +- org.apache.poi:poi-ooxml:jar:4.0.0:compile
[INFO] |  +- org.apache.poi:poi-ooxml-schemas:jar:4.0.0:compile
[INFO] |  |  \- org.apache.xmlbeans:xmlbeans:jar:3.0.1:compile
[INFO] |  +- org.apache.commons:commons-compress:jar:1.18:compile
[INFO] |  \- com.github.virtuald:curvesapi:jar:1.04:compile
[INFO] +- com.xuxueli:xxl-job-core:jar:1.8.2:compile
[INFO] |  +- javax.servlet.jsp:jsp-api:jar:2.2:compile
[INFO] |  +- org.eclipse.jetty:jetty-server:jar:9.4.19.v20190610:compile
[INFO] |  |  +- org.eclipse.jetty:jetty-http:jar:9.4.19.v20190610:compile
[INFO] |  |  |  \- org.eclipse.jetty:jetty-util:jar:9.4.19.v20190610:compile
[INFO] |  |  \- org.eclipse.jetty:jetty-io:jar:9.4.19.v20190610:compile
[INFO] |  +- com.caucho:hessian:jar:4.0.38:compile
[INFO] |  +- org.codehaus.jackson:jackson-mapper-asl:jar:1.9.13:compile
[INFO] |  |  \- org.codehaus.jackson:jackson-core-asl:jar:1.9.13:compile
[INFO] |  +- org.springframework:spring-context:jar:5.1.8.RELEASE:compile
[INFO] |  |  +- org.springframework:spring-aop:jar:5.1.8.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-expression:jar:5.1.8.RELEASE:compile
[INFO] |  +- org.codehaus.groovy:groovy-all:jar:2.4.5:compile
[INFO] |  \- org.apache.commons:commons-exec:jar:1.3:compile
[INFO] +- com.belerweb:pinyin4j:jar:2.5.0:compile
[INFO] +- junit:junit:jar:4.12:test
[INFO] |  \- org.hamcrest:hamcrest-core:jar:1.3:test
[INFO] +- org.slf4j:slf4j-api:jar:1.7.26:compile
[INFO] +- org.slf4j:jcl-over-slf4j:jar:1.7.26:compile
[INFO] +- org.slf4j:jul-to-slf4j:jar:1.7.26:compile
[INFO] +- org.springframework.boot:spring-boot-starter:jar:2.1.6.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot:jar:2.1.6.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-logging:jar:2.1.6.RELEASE:compile
[INFO] |  |  +- ch.qos.logback:logback-classic:jar:1.2.3:compile
[INFO] |  |  |  \- ch.qos.logback:logback-core:jar:1.2.3:compile
[INFO] |  |  \- org.apache.logging.log4j:log4j-to-slf4j:jar:2.11.2:compile
[INFO] |  +- javax.annotation:javax.annotation-api:jar:1.3.2:compile
[INFO] |  +- org.springframework:spring-core:jar:5.1.8.RELEASE:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:5.1.8.RELEASE:compile
[INFO] |  \- org.yaml:snakeyaml:jar:1.23:compile
[INFO] +- redis.clients:jedis:jar:3.0.1:compile
[INFO] |  \- org.apache.commons:commons-pool2:jar:2.6.2:compile
[INFO] +- com.baomidou:mybatis-plus-boot-starter:jar:3.1.2:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-jdbc:jar:2.1.6.RELEASE:compile
[INFO] |     +- com.zaxxer:HikariCP:jar:3.2.0:compile
[INFO] |     \- org.springframework:spring-jdbc:jar:5.1.8.RELEASE:compile
[INFO] +- com.baomidou:mybatis-plus:jar:3.1.2:compile
[INFO] +- com.baomidou:mybatis-plus-generator:jar:3.1.2:compile (optional)
[INFO] +- com.alibaba:druid:jar:1.1.18:compile
[INFO] +- com.alibaba:druid-spring-boot-starter:jar:1.1.18:compile
[INFO] +- org.json:json:jar:20180130:compile
[INFO] +- org.apache.oltu.oauth2:org.apache.oltu.oauth2.client:jar:0.31:compile
[INFO] |  +- org.apache.oltu.oauth2:org.apache.oltu.oauth2.common:jar:0.31:compile
[INFO] |  \- org.codehaus.jettison:jettison:jar:1.2:compile
[INFO] +- com.squareup.okhttp3:okhttp:jar:3.8.1:compile
[INFO] |  \- com.squareup.okio:okio:jar:1.13.0:compile
[INFO] +- org.apache.commons:commons-collections4:jar:4.4:compile
[INFO] +- org.apache.commons:commons-vfs2:jar:2.1:compile
[INFO] |  \- commons-logging:commons-logging:jar:1.2:compile
[INFO] +- org.apache.commons:commons-lang3:jar:3.4:compile
[INFO] +- commons-collections:commons-collections:jar:3.1:compile
[INFO] +- commons-fileupload:commons-fileupload:jar:1.3.1:compile
[INFO] |  \- commons-io:commons-io:jar:2.2:compile
[INFO] +- com.baomidou:mybatis-plus-extension:jar:3.1.2:compile
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.1.2:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.1.2:compile
[INFO] |  |  +- com.github.jsqlparser:jsqlparser:jar:1.2:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.1:compile
[INFO] |  \- org.mybatis:mybatis-spring:jar:2.0.1:compile
[INFO] +- com.baomidou:dynamic-datasource-spring-boot-starter:jar:2.5.5:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-aop:jar:2.1.6.RELEASE:compile
[INFO] |     \- org.aspectj:aspectjweaver:jar:1.9.4:compile
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:2.1.6.RELEASE:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:2.1.6.RELEASE:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:2.1.6.RELEASE:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.4.0:test
[INFO]    |  \- net.minidev:json-smart:jar:2.3:test
[INFO]    |     \- net.minidev:accessors-smart:jar:1.2:test
[INFO]    |        \- org.ow2.asm:asm:jar:5.0.4:test
[INFO]    +- org.assertj:assertj-core:jar:3.11.1:test
[INFO]    +- org.mockito:mockito-core:jar:2.23.4:test
[INFO]    |  \- net.bytebuddy:byte-buddy-agent:jar:1.9.13:test
[INFO]    +- org.hamcrest:hamcrest-library:jar:1.3:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.0:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:5.1.8.RELEASE:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.6.2:test
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.200 s
[INFO] Finished at: 2019-10-11T16:00:25+08:00
[INFO] Final Memory: 43M/449M
[INFO] ------------------------------------------------------------------------

D:\2\lqx-project-demo\basic-server (v2.0.3)
λ
```

