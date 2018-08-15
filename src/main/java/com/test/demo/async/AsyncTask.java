package com.test.demo.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;

public class AsyncTask {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async
    public Future<String> doTask1() throws InterruptedException {
        logger.info("Task1 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();

        logger.info("Task1 finished, time elapsed: {} ms.", end - start);

        return new AsyncResult<>("Task1 accomplished!");
    }

    @Async
    public Future<String> doTask2() throws InterruptedException {
        logger.info("Task2 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();

        logger.info("Task2 finished, time elapsed: {} ms.", end - start);

        return new AsyncResult<>("Task2 accomplished!");
    }
}