package br.com.jopaulo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jopaulo.domain.dto.UserDTO;
import br.com.jopaulo.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

	@Autowired
	private UserService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UserDTO.class));
	}
}
