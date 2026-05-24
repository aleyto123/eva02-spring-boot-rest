package com.tecsup.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Intercepta antes de ejecutar cualquier método en la capa service
    @Before("execution(* com.tecsup.service.*.*(..))")
    public void logAntes(JoinPoint joinPoint) {
        logger.info("AOP [Logging]: Iniciando la ejecución del método: -> " + joinPoint.getSignature().getName());
    }

    // Intercepta inmediatamente después de terminar el método en la capa service
    @After("execution(* com.tecsup.service.*.*(..))")
    public void logDespues(JoinPoint joinPoint) {
        logger.info("AOP [Logging]: Finalizó con éxito la ejecución del método: -> " + joinPoint.getSignature().getName());
    }
}