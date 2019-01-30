package com.zm.order.aop.Aspect;


import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zm.order.aop.annotation.BusLog;
import com.zm.order.bean.Log;
import com.zm.order.service.LogService;
import com.zm.utils.UUIDUtil;

@Component
@Aspect
public class BusLogAop {
	@Autowired
	private LogService service;
	
	//定义一个方法为切点
	@Pointcut("@annotation(com.zm.order.aop.annotation.BusLog))")
	public void pointcut(){}
	
	// 环绕切点,在进入切点前,跟切点后执行
	@Around(value = "pointcut()")
	public Object doAround(ProceedingJoinPoint point) throws Throwable{
	    Method method = getMethod(point);
	    BusLog busLog = method.getAnnotation(BusLog.class);
	    Log log = new Log();
	    log.setId(UUIDUtil.generateUUID());
	    log.setOp_from(busLog.op_from());
	    log.setOp_time(new Date());
	    log.setOp_type(busLog.op_type());
	    log.setOp_user("cjl");
	    service.add(log);
	    return point.proceed();
	}
	
    /**
     * 取接口调用方法
     * @param joinpoint
     * @return
     */
    private Method getMethod(JoinPoint joinpoint) {
        Signature signature = joinpoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        return methodSignature.getMethod();
    }
    
    @Before("pointcut()")
    public void doBefore(){
    	System.out.println("方法执行前操作");
    }
    
    /**
     * 处理完请求，返回内容
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void doAfterReturning(Object ret) {
        System.out.println("方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("pointcut()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }


    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("pointcut()")
    public void after(JoinPoint jp){
        System.out.println("方法最后执行.....");
    }
}
