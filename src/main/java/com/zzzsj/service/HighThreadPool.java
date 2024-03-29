package com.zzzsj.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zzzsj.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

/**
 * @author yyyzj
 * @create 2022/12/20 下午3:58
 */

@Component
@Slf4j
public class HighThreadPool implements DisposableBean {

    private static final int THREADS = 20;
    private static final int MAX_THREADS = 50;
    private static final int ALIVETIME = 0;
    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        log.info("线程池init start=>");
        executorService = new ThreadPoolExecutor(THREADS,
                MAX_THREADS, ALIVETIME, TimeUnit.SECONDS, new LinkedBlockingDeque<>(20),
                new ThreadFactoryBuilder().setNameFormat("highThread-pool").build());
        log.info("线程池init end=>");
    }

    public void submit(Runnable task) {
        try {
            executorService.submit(task);
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }

    }

    @Override
    public void destroy() throws Exception {
        executorService.shutdown();
        log.info("线程池关闭=>");
    }
}
