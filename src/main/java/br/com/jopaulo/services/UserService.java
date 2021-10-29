package br.com.jopaulo.services;

import java.util.List;

import br.com.jopaulo.domain.User;

public interface UserService {
 
	User findById(Integer id);

	List<User> findAll();
}
