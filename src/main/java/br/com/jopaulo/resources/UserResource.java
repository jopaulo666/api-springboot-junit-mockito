package br.com.jopaulo.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jopaulo.domain.User;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id){
		
		return ResponseEntity.ok().body(new User(1, "João Paulo da Mata Mendes", "jp_cbc@hotmail.com", "1234"));
	}
}
