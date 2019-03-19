package com.hzzf.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2TestMethod {

    private static Logger logger = LogManager.getLogger(Log4j2TestMethod.class);

    public static void main (String[] args) {
        //logger.debug("debug test");
        //logger.info("info test");
        //logger.warn("warn test");
        //logger.error("error test");

        archiveTest(1000, "error");
    }

    // 日志备份测试
    public static void archiveTest(int num, String logLevel){
        if ("debug".equals(logLevel)) {
            for (int i = 1; i <= num; i++) {
                logger.debug("debug test");
            }
        } else if ("info".equals(logLevel)) {
            for (int i = 1; i <= num; i++) {
                logger.info("info test");
            }
        } else if ("warn".equals(logLevel)) {
            for (int i = 1; i <= num; i++) {
                logger.warn("warn test");
            }
        } else {
            for (int i = 1; i <= num; i++) {
                logger.error("error test");
            }
        }
    }
}
