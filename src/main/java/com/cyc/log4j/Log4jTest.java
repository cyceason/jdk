package com.cyc.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jTest {
    private static Logger logger = Logger.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        // 加载不是根目录的路径配置文件
        PropertyConfigurator.configure("D:\\ideaworks\\jdk\\src\\main\\java\\com\\cyc\\log4j\\log4j.properties ");
        try {
            int i = 8 / 0;
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
