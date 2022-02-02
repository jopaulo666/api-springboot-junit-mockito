package br.com.jopaulo.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.jopaulo.domain.User;
import br.com.jopaulo.domain.dto.UserDTO;
import br.com.jopaulo.repositories.UserRepository;
import br.com.jopaulo.services.exceptions.DataIntegrationViolationException;
import br.com.jopaulo.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UserServiceImplTest {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	private static final String E_MAIL_JA_CADASTRADO_NO_SISTEMA = "E-mail já cadastrado no sistema";
	private static final int INDEX = 0;
	private static final String MESSAGE = "Usuário não encontrado";
	private static final Integer ID = 1;
	private static final String NAME = "João Paulo";
	private static final String EMAIL = "jp_cbc@hotmail.com";
	private static final String PASSWORD = "1234";

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@Mock
	private ModelMapper mapper;

	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnAnUserInstance() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

		User response = service.findById(ID);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
	}

	@Test
	void whenFindByIdThenReturnAnUserNotFoundException() {
		when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(MESSAGE));

		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(MESSAGE, e.getMessage());
		}
	}

	@Test
	void whenFindAllThenReturnAnListOfUsers() {
		when(repository.findAll()).thenReturn(List.of(user));

		List<User> response = service.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(User.class, response.get(INDEX).getClass());

		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NAME, response.get(INDEX).getName());
		assertEquals(EMAIL, response.get(INDEX).getEmail());
		assertEquals(PASSWORD, response.get(INDEX).getPassword());
	}

	@Test
	void whenCreateThenReturnSuccess() {
		when(repository.save(Mockito.any())).thenReturn(user);

		User response = service.create(userDTO);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
	}

	@Test
	void whenCreateThenReturnAnDataIntegrityViolationException() {
		when(repository.findByEmail(Mockito.any())).thenReturn(optionalUser);

		try {
			optionalUser.get().setId(2);
			service.create(userDTO);
		} catch (Exception e) {
			assertEquals(DataIntegrationViolationException.class, e.getClass());
			assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, e.getMessage());
		}
	}

	@Test
	void whenUpdateThenReturnSuccess() {
		when(repository.save(Mockito.any())).thenReturn(user);

		User response = service.update(userDTO);

		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(PASSWORD, response.getPassword());
	}

	@Test
	void whenUpdateThenReturnAnDataIntegrityViolationException() {
		when(repository.findByEmail(Mockito.any())).thenReturn(optionalUser);

		try {
			optionalUser.get().setId(2);
			service.create(userDTO);
		} catch (Exception e) {
			assertEquals(DataIntegrationViolationException.class, e.getClass());
			assertEquals(E_MAIL_JA_CADASTRADO_NO_SISTEMA, e.getMessage());
		}
	}

	@Test
	void deleteWithSuccess() {
		when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
		doNothing().when(repository).deleteById(Mockito.anyInt());
		service.delete(ID);
		verify(repository, times(1)).deleteById(Mockito.anyInt());
	}

	@Test
	void deleteWithObjectNotFoundException() {
		when(repository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

		try {
			service.delete(ID);
		} catch (Exception e) {
			assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
		}
	}

	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
	}
}
