package pw.io.booker.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import pw.io.booker.model.Token;

@Repository
public interface AuthenticationRepository extends CrudRepository<Token, Integer> {
	
	Token findByToken(String token);
	Token deleteByToken(String token);
	

}
