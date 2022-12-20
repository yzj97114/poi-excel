package com.zzzsj.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.zzzsj.common.exception.BizException;
import com.zzzsj.common.utils.PdfUtils;
import com.zzzsj.service.HighThreadPool;
import com.zzzsj.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author 79282
 */

@RestController
@Slf4j
public class ThreadPoolTestController {

    @Autowired
    private HighThreadPool highThreadPool;




    @RequestMapping("/pool")
    public void poolTest() throws InterruptedException {
        long s = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            int finalI = i;

            try {
                highThreadPool.submit(() -> {
                    try {
                        log.info("子线程执行===={}", finalI);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        log.error("aaaaa");
                    } finally {
                        countDownLatch.countDown();
                    }

                });

            } catch (Exception e) {
                log.error("bbbbb");
                countDownLatch.countDown();
            }



        }
        countDownLatch.await();
        long e = System.currentTimeMillis();
        log.info("多线程执行时间：{}", e - s);
//
//
//        log.info("主线程执行=======");
//        s = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            log.info("主线程执行===={}", i);
//            Thread.sleep(1000);
//        }
//        e = System.currentTimeMillis();
//        log.info("单线程执行时间：{}", e - s);

    }


}
