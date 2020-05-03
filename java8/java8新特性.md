---
title: java8新特性
tags: [java8]
date: 2019-09-21 14:18:14
categories: java8
---
** {{ title }} ** <Excerpt in index | 首页摘要>


<!-- more -->

### 1、java8接口可以有默认方法
接口可以有实现体，但必须是默认实现，所以要在方法前加上default字段表示默认方法

```
public interface SmartApplicationListener extends ApplicationListener<ApplicationEvent>, Ordered {

	/**
	 * Determine whether this listener actually supports the given event type.
	 * @param eventType the event type (never {@code null})
	 */
	boolean supportsEventType(Class<? extends ApplicationEvent> eventType);

	/**
	 * Determine whether this listener actually supports the given source type.
	 * <p>The default implementation always returns {@code true}.
	 * @param sourceType the source type, or {@code null} if no source
	 */
	default boolean supportsSourceType(@Nullable Class<?> sourceType) {
		return true;
	}

	/**
	 * Determine this listener's order in a set of listeners for the same event.
	 * <p>The default implementation returns {@link #LOWEST_PRECEDENCE}.
	 */
	@Override
	default int getOrder() {
		return LOWEST_PRECEDENCE;
	}

}
```

#### 2、

```

```
1. 
2. 
3. 

#### 3、

1. 
2. 
3. 

```

```
