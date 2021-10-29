package br.com.jopaulo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jopaulo.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
