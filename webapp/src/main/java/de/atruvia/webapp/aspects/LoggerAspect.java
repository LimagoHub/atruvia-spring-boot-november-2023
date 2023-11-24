package de.atruvia.webapp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Profile("production")
@Aspect
@Slf4j
public class LoggerAspect {



    @Before(value ="Pointcuts.personenControllerMethods()")
    public void logAdvice(JoinPoint joinPoint) {
        log.warn("####### Log advice #######");
        log.warn(joinPoint.getSignature().getName() + " wurde gerufen");
    }

    @AfterReturning(value ="execution(public * de.atruvia.webapp.presentation.controller.personen.version1.PersonenQueryController.*(..))", returning = "result")
    public void afterdemo(JoinPoint joinPoint, Object result) {
        log.warn("####### Log advice #######");
        log.warn(joinPoint.getSignature().getName() + " erefolgreich beendet mit result: " + result);
    }

    @AfterThrowing(value ="Pointcuts.serviceMethods()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.warn("####### Log advice #######");
        log.warn(joinPoint.getSignature().getName() + " Kein Upps: " + ex);
    }

    @Around(value ="Pointcuts.dozentMethods()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        var start = Instant.now();
        final Object proceed = joinPoint.proceed();
        var ende = Instant.now();
        var duration = Duration.between(start, ende);
        System.out.printf("Ausführung der Methode %s dauerte %s millis.\n", joinPoint.getSignature().getName(), duration.toMillis());
        return proceed;

    }
}
