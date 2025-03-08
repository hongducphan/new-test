package com.ducph.newtest.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.ducph.newtest..*(..))")
    public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[").append(methodName).append("]");

        // Add parameters to the log
        if (methodArgs.length > 0) {
            logMessage.append(" - ");
            String params = buildParametersLog(methodArgs, joinPoint.getSignature());
            logMessage.append(params);
        }

        log.info(logMessage.toString());

        // Execute the actual method
        return joinPoint.proceed();
    }

    private String buildParametersLog(Object[] methodArgs, Signature signature) {
        StringJoiner joiner = new StringJoiner(" - ");
        String[] parameterNames = ((MethodSignature) signature).getParameterNames();

        for (int i = 0; i < methodArgs.length; i++) {
            joiner.add(parameterNames[i] + ": " + methodArgs[i]);
        }
        return joiner.toString();
    }
}