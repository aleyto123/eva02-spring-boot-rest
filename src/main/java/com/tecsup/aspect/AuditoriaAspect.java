package com.tecsup.aspect;

import com.tecsup.model.AuditoriaLog;
import com.tecsup.repository.AuditoriaLogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private AuditoriaLogRepository logRepository;

    @Before("execution(* com.tecsup.service.*.*(..))")
    public void registrarAuditoria(JoinPoint joinPoint) {
        String metodo = joinPoint.getSignature().getName();
        String accion = "EJECUTADO";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String usuario = (auth != null && auth.isAuthenticated()) ? auth.getName() : "ANÓNIMO";

        AuditoriaLog log = new AuditoriaLog(accion, metodo, LocalDateTime.now(), usuario);
        logRepository.save(log);
    }
}