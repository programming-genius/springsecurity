package it.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springsecurity.users.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByUsername(String u);
}
