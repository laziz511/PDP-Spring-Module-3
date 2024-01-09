package uz.pdp.online.springbootapplication.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RequestMappingLoggingAspect {

    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void logBeforeRequestMappingMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Log => Before execution of method annotated with @RequestMapping: {}", methodName);
    }

    @After("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void logAfterRequestMappingMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Log => After execution of method annotated with @RequestMapping: {}", methodName);
    }
}