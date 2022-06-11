package com.RokuEng.springdata.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class Logger {

	@Around("@annotation(ProceedTimeAspect)")
	public Object proceedTime(ProceedingJoinPoint joinPoint) throws Throwable {

		log.info("");
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		log.info("");

		log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

		return proceed;
	}

	@Before("@annotation(com.RokuEng.springdata.aspect.InterestRateApplyAspect)")
	public void onInterestRateApply() {
		log.info("Interest rate applied to all debt accounts");
	}
}
