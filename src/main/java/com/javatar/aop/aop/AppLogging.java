package com.javatar.aop.aop;

import com.javatar.aop.model.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.sound.midi.Track;

@Aspect
@Component
public class AppLogging {

//    @Before(value = "execution(* com.javatar.aop.service.EmployeeService.*(..)) and args(employee)")
//    public void beforeAdvice(JoinPoint joinPoint, Employee employee){
//        System.out.println("Before method: " + joinPoint.getSignature());
//        System.out.println("" + employee.getEmpId() + employee.getName());
//    }
//
//    @After(value = "execution(* com.javatar.aop.service.EmployeeService.*(..)) and args(employee) ")
//    public void afterAdvice(JoinPoint joinPoint, Employee employee){
//        System.out.println("after method: " + joinPoint.getSignature());
//        System.out.println("successfully " + employee.getEmpId() + employee.getName());
//    }

    @Around("execution(* com.javatar.aop.service.EmployeeService.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before: " +  joinPoint.proceed());
        Object result = joinPoint.proceed();
        System.out.println("After: " + result);
        return result;
    }
}
