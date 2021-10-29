package br.com.jopaulo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.jopaulo.domain.User;
import br.com.jopaulo.repositories.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {

	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User user1 = new User(null, "João Paulo da Mata Mendes", "jp_cbc@hotmail.com", "1234");
		User user2 = new User(null, "Bruna Suellen", "bruna@hotmail.com", "1234");
		User user3 = new User(null, "Adnari Mendes", "adnari@hotmail.com", "1234");
		User user4 = new User(null, "Nadir Santos", "nadir@hotmail.com", "1234");
		
		repository.saveAll(List.of(user1, user2, user3, user4));
	}
}
