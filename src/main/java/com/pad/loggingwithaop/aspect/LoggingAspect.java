package com.pad.loggingwithaop.aspect;

import com.pad.loggingwithaop.model.Product;
import com.pad.loggingwithaop.payload.ResponseDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.pad.loggingwithaop.service.ProductService.updateProduct(..)) && within(com.pad.loggingwithaop.repository.*)")
    public void logParamBeforeExecuteUpdateProduct(JoinPoint joinPoint){
        log.debug("Log before executing method...");
        log.debug("This is qualified name: {}", joinPoint.getSignature().getDeclaringTypeName());
        log.debug("This is method name: {}", joinPoint.getSignature().getName());
        log.debug("Arguments of method: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @Pointcut("execution(* com.pad.loggingwithaop.service.ProductService.getProductById(..))")
    public void getProductById(){}

    @Before(value = "getProductById()")
    public void beforeGetProductById(JoinPoint joinPoint){
        log.debug("Get product with id {}", joinPoint.getArgs());
    }

    @AfterReturning(value = "getProductById()", returning = "result")
    public void afterReturnGetProductById(JoinPoint joinPoint, ResponseDTO result){
        log.debug("Value of product {} : {}", joinPoint.getArgs(), result);
    }

    @AfterThrowing(value = "getProductById()")
    public void handleGetProductByIdException(JoinPoint joinPoint){
        log.debug("Can not find product with id: {}", joinPoint.getArgs());
    }

    @Around("execution(* com.pad.loggingwithaop.service.ProductService.deleteProduct(..))")
    public void logAfterDeleteProduct(ProceedingJoinPoint joinPoint) throws Throwable{
        try{
            joinPoint.proceed();
            log.debug("Delete product {} successfully", joinPoint.getArgs());
        }
        catch (Exception e){
            log.debug("Can not find product with id: {}", joinPoint.getArgs());
            throw e;
        }
    }

}
