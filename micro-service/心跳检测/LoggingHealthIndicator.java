package com.fzs.iot.manager.support.health;

import io.netty.util.internal.SystemPropertyUtil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 定时向控制台输出日志,以便于在容器日志中检测是否存活
 * <pre>
 *     &lt;logger name="com.fzs.parking.trade.support.health.LoggingHealthIndicator" additivity="false"&gt;
 *         &lt;appender-ref ref="CONSOLE"/&gt;
 *     &lt;/logger&gt;
 * </pre>
 *
 * @author xty
 * Created by xty on 2020/12/14.
 */
@Service
public class LoggingHealthIndicator implements ApplicationListener<ApplicationReadyEvent>, DisposableBean, Runnable {

    private static final Logger log = LoggerFactory.getLogger(LoggingHealthIndicator.class);

    /**
     * 日志打印速率,单位为秒,最小值为5
     * <p>
     * -Dhealth.logging.period=10
     * </p>
     */
    private static final long LOGGING_PERIOD = Math.max(5, SystemPropertyUtil.getLong("health.logging.period", 10));

    /**
     * 日志打印开关,默认为true
     * <p>
     * -Dhealth.logging.enable=false
     * </p>
     */
    private static final boolean LOGGING_ENABLE = SystemPropertyUtil.getBoolean("health.logging.enable", true);

    private final ScheduledExecutorService executorService = Executors
            .newSingleThreadScheduledExecutor(r -> new Thread(r, "logging-health-indicator"));

    private final AtomicLong counter = new AtomicLong();

    private Future<?> future;

    @Override
    public void run() {
        if (log.isInfoEnabled()) {
            log.info("health indicator: {}.", counter.incrementAndGet() & Long.MAX_VALUE);
        }
    }

    @Override
    public void destroy() {
        if (!LOGGING_ENABLE) {
            return;
        }
        if (log.isInfoEnabled()) {
            log.info("stop health indicator...");
        }
        if (future != null) {
            future.cancel(true);
        }
        executorService.shutdownNow();
    }

    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        if (!LOGGING_ENABLE || future != null) {
            return;
        }
        future = executorService.scheduleAtFixedRate(this, LOGGING_PERIOD, LOGGING_PERIOD, TimeUnit.SECONDS);
    }


}
