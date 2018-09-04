package pw.io.booker.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import pw.io.booker.model.Token;
import pw.io.booker.repo.AuthenticationRepository;

@Aspect
@Service
public class LoginAspect {
	
	Logger logger = Logger.getLogger(LoginAspect.class);

	private AuthenticationRepository authenticationRepository;
	
	
	
	@Around("execution(* pw.io.booker.controller..*(..)) && args(token)")
	public Object validate(ProceedingJoinPoint joinpoint, Token token) {
		
		logger.info("Start validateLogin");
		
		/// if token is null, unable to login
		if(token == null) {
			throw new MyException("Invalid Token. Access denied");
		}	
		/// token did not match any token in repository
		if(authenticationRepository.findByToken(token.getToken()) == null) {
			throw new MyException("Invalid Token. Access denied");
		}		
		/// valid token
		try {
			return joinpoint.proceed();
		} catch (Throwable e) {
			throw new MyException("Access permited.");
		}	
	}

	
	
	
	
	
	
	
	
	

}
