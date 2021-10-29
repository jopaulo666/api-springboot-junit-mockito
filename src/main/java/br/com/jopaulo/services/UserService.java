package br.com.jopaulo.services;

import java.util.List;

import br.com.jopaulo.domain.User;
import br.com.jopaulo.domain.dto.UserDTO;

public interface UserService {
 
	User findById(Integer id);

	List<User> findAll();
	
	User create(UserDTO obj);
}

