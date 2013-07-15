package com.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyLogger {

	private static Log logger = LogFactory.getLog(MyLogger.class);

	// 定义一个切点，在com.dao.implement包或其子包中定义的任意方法的执行
	@Pointcut("execution(* com.dao.implement..*.*(..))")
	private void allMethod() {
	}

	// 此方法将用作前置通知
	@Before("allMethod()")
	public void myBeforeAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "  类的  " + joinpoint.getSignature().getName();
		logger.info("前置通知: " + classAndMethod + " 方法开始执行！");
	}

	// 此方法将用作后置通知
	@AfterReturning("allMethod()")
	public void myAfterReturningAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "  类的  " + joinpoint.getSignature().getName();
		logger.info("后置通知: " + classAndMethod + " 方法执行 正常  结束！");
	}

	// 此方法将用作异常通知
	@AfterThrowing(pointcut = "allMethod()", throwing = "e")
	public void myAfterThrowingAdvice(JoinPoint joinpoint, Exception e) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "  类的  " + joinpoint.getSignature().getName();
		logger.info("异常通知: " + classAndMethod + " 方法抛出异常：" + e.getMessage());
	}

	// 此方法将用作最终通知
	@After("allMethod()")
	public void myAfterAdvice(JoinPoint joinpoint) {
		String classAndMethod = joinpoint.getTarget().getClass().getName()
				+ "  类的  " + joinpoint.getSignature().getName();
		logger.info("最终通知: " + classAndMethod + " 方法执行结束！");
	}

	// 此方法将用作环绕通知
	@Around("allMethod()")
	public Object myAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		long begintime = System.currentTimeMillis();// 记下开始时间
		// 传递给连接点对象进行接力处理
		Object result = pjp.proceed();
		long endtime = System.currentTimeMillis();// 记下结束时间
		String classAndMethod = pjp.getTarget().getClass().getName() + "  类的  "
				+ pjp.getSignature().getName();
		logger.info("环绕通知: " + classAndMethod + " 方法执行结束,耗时 "
				+ (endtime - begintime) + " 毫秒！ ");
		return result;
	}

}
