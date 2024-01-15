package com.javatar.aop.aop;

import com.javatar.aop.model.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sound.midi.Track;

@Aspect
@Component
public class AppLogging {
    
    private static Logger log = LoggerFactory.getLogger(AppLogging.class);

    @Before(value = "execution(* com.javatar.aop.service.EmployeeService.*(..)) && args(employee)")
    public void beforeAdvice(JoinPoint joinPoint, Employee employee){
        log.info("Before method: " + joinPoint.getSignature());
        log.info("" + employee.getEmpId() + employee.getName());
    }

    @After(value = "execution(* com.javatar.aop.service.EmployeeService.*(..)) && args(employee) ")
    public void afterAdvice(JoinPoint joinPoint, Employee employee){
        log.info("after method: " + joinPoint.getSignature());
        log.info("successfully " + employee.getEmpId() + employee.getName());
    }

    @Pointcut("execution(public * com.javatar.aop.service.*.*(..))")
    public void logApp() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    public void logAppAnno(){
    }

    //    @Around("logApp()")
    @Around("execution(public * com.javatar.aop.service.*.*(..)) && @annotation(com.javatar.aop.aop.Log)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(String.format("Method %s going to call from class %s",
                joinPoint.getSignature().toString(),
                joinPoint.getTarget().getClass().getSimpleName()));

        Object returnObj = joinPoint.proceed(joinPoint.getArgs());

        log.info(String.format("Method %s has called from class %s with return value type %s",
                joinPoint.getSignature().toString(),
                joinPoint.getTarget().getClass().getSimpleName(),
                returnObj.getClass().getName()));

        return returnObj;
    }

//    @Around("execution(* com.javatar.aop.service.EmployeeService.*(..))")
//    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//        log.info("before: " +  joinPoint.proceed());
//        Object result = joinPoint.proceed();
//        log.info("After: " + result);
//        return result;
//    }
}
