package it.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.springsecurity.users.Token;

public interface JpaTokenRepository extends JpaRepository<Token, Integer> {
	Optional<Token> findTokenBySessionid(String sessionid);
}