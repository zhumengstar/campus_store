package com.java.test;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.trace("--> Hello trace.");
        logger.debug("--> Hello debug.");
        logger.info("--> Hello info.");
        logger.warn("--> Goodbye warn.");
        logger.error("--> Goodbye error.");
        // 获取classpath路径
        String s = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("classpath => " + s );

// 获取classpath路径
        String path = App.class.getResource("/").toString();
        System.out.println("classpath => " + path);

        //打印 Logback 内部状态
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}