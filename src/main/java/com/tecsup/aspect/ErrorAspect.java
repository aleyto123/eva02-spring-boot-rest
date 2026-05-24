package com.tecsup.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Intercepta únicamente si el método en service arroja un error/excepción
    @AfterThrowing(pointcut = "execution(* com.tecsup.service.*.*(..))", throwing = "error")
    public void logError(JoinPoint joinPoint, Throwable error) {
        logger.error("AOP [ErrorAspect]: Se produjo un error en el método: "
                + joinPoint.getSignature().getName() + ". Razón: " + error.getMessage());
    }
}