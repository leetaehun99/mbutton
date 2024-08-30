package com.doo.mbutton.frame.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class LoggingAdvice {

	private Log logger = LogFactory.getLog(getClass());
	
	@Around("execution(* com.doo.mbutton.**.service..*.*(..))")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StopWatch stopWatch = new StopWatch();

		try {
			
			logger.debug(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + " - start");			

			stopWatch.start();
			Object retValue = joinPoint.proceed();
			stopWatch.stop();
			return retValue;

		} catch (Throwable e) {
			throw e;
		} finally {
			logger.debug(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + " - end [ " + stopWatch.getTotalTimeMillis() + " ]");			
		}
		
	}

}
