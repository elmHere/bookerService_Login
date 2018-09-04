package pw.io.booker.aspect;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import pw.io.booker.model.Token;
import pw.io.booker.repo.AuthenticationRepository;

@Aspect
@Component
public class LoginAspect {
	
	Logger logger = Logger.getLogger(LoginAspect.class);

	private AuthenticationRepository authenticationRepository;
	
	public LoginAspect(AuthenticationRepository authenticationRepository) {
		super();
		this.authenticationRepository = authenticationRepository;
	}
	
	
	
	@Around("execution(* pw.io.booker.controller..*(..)) && args(token,..)")
	public Object validate(ProceedingJoinPoint joinpoint, String token) throws Throwable {
		
		logger.info("Start validateLogin");
		
		/// if token is null, unable to login
		if(token == null) {
			throw new MyException("Invalid Token. Access denied");
		}	
		
		/// token did not match any token in repository
		// something wrong with this statement tho??
		if(authenticationRepository.findByToken(token)== null) {
			throw new MyException("Invalid Token. Access denied");
		}	
		
		/// valid token
		return joinpoint.proceed();
	
		

	}

	
	
	
	
	
	
	
	
	

}
