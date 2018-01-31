package com.cencosud.middleware.recommendation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

	@Around("execution(* *(..)) && @annotation(com.cencosud.middleware.recommendation.annotation.Loggable)")
	public Object aroundCreation(ProceedingJoinPoint joinPoint) throws Throwable {
		LOGGER.info("Inicio: [" + joinPoint.getTarget().getClass().getName() +  "] (" + joinPoint.getSignature().getName() + ")");
		printParameters(joinPoint == null ? new Object[1] : joinPoint.getArgs());
		Object valorRetorno = joinPoint.proceed();
		LOGGER.info("Fin: [" + joinPoint.getTarget().getClass().getName() +  "] (" + joinPoint.getSignature().getName() + ")");
		return valorRetorno;
	}

	private void printParameters(Object[] arguments){
        for (Object object : arguments) {
            LOGGER.info("List of parameters : " + object);
        }
    }

    
}