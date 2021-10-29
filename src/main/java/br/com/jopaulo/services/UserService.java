package br.com.jopaulo.services;

import br.com.jopaulo.domain.User;

public interface UserService {
 
	User findById(Integer id);
}
