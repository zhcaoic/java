package com.test.userservicetest.domain.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Aspect
@Component
public class LoggerUtil {

    // 初始化Logger
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.toString());
    private static final String LOG_PATH = "/home/master/file/javalog/user_service_test/service_log.log";

    // 设置Logger
    static {
        logger.setLevel(Level.INFO);
        try {
            FileHandler fileHandler = new FileHandler(LOG_PATH, false);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.info("#################################### Start ####################################");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*

    // 记录所有进入Controller的request请求及输出的response响应
    @Pointcut("execution(public * com.test.userservicetest.controller..*.*(..))")
    public void webControllerLog() {}


    // Controller处理前记录request信息
    @Before("webControllerLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        // 获取请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录request信息
        logger.info("#################################### Request ####################################");
        logger.info("###  TIME         :  " + new Date().toString());
        logger.info("###  URL          :  " + request.getRequestURL().toString());
        logger.info("###  HTTP_METHOD  :  " + request.getMethod());
        logger.info("###  IP           :  " + request.getRemoteAddr());
        logger.info("###  CLASS_METHOD :  " + joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        logger.info("###  ARGS         :  " + Arrays.toString(joinPoint.getArgs()));
        logger.info("#################################### Start ####################################");

    }


    // Controller处理后记录response信息
    @AfterReturning(returning = "ret", pointcut = "webControllerLog()")
    public void doAfterReturning(Object ret) throws Throwable{
        logger.info("#################################### Response ####################################");
        logger.info("###  TIME     :  " + new Date().toString());
        logger.info("###  RESPONSE :  " + ret);
        logger.info("#################################### Start ####################################");
    }

    */


}
