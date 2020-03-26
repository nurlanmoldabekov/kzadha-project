package com.kzadha.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DaoAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void callAtMyServicePublic() { }

    @Around("callAtMyServicePublic()")
    public Object execute(ProceedingJoinPoint p) {
        Object result = null;
        try {
            result = (Object) p.proceed();
            return result;
        } catch (Throwable t) {
            if (t instanceof EmptyResultDataAccessException) {
                logger.error(t.getMessage());
            } else {
                throw new RuntimeException(t);
            }

        }
        // Create dummy result
        return null;
    }
}