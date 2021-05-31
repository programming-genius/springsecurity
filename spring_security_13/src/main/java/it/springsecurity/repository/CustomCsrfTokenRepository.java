package it.springsecurity.repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import it.springsecurity.users.Token;

public class CustomCsrfTokenRepository implements CsrfTokenRepository {

	@Autowired
	private JpaTokenRepository jpaTokenRepository;

	@Override
	public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
		String uuid = UUID.randomUUID().toString();
		return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid);
	}

	@Override
	public void saveToken(CsrfToken csrfToken, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		String sessionid = null;

		for (Cookie cookie : httpServletRequest.getCookies()) {
			if (cookie.getName().equals("JSESSIONID")) {
				sessionid = cookie.getValue();
				break;
			}
		}

		Optional<Token> existingToken = jpaTokenRepository.findTokenBySessionid(sessionid);
		if (existingToken.isPresent()) {
			Token token = existingToken.get();
			if (new Date().after(token.getExpired())) {
				token.setValue(csrfToken.getToken());
				token.setExpired(
						Date.from(LocalDateTime.now().plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant()));
				jpaTokenRepository.save(token);
			}
		} else {
			Token token = new Token();
			token.setSessionid(sessionid);
			token.setValue(csrfToken.getToken());
			token.setExpired(Date.from(LocalDateTime.now().plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant()));
			jpaTokenRepository.save(token);
		}
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
		String sessionid = null;
		for (Cookie cookie : httpServletRequest.getCookies()) {
			if (cookie.getName().equals("JSESSIONID")) {
				sessionid = cookie.getValue();
				break;
			}
		}
		Optional<Token> existingToken = jpaTokenRepository.findTokenBySessionid(sessionid);
		if (existingToken.isPresent()) {
			Token token = existingToken.get();
			if (new Date().before(token.getExpired()))
				return new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.getValue());
		}
		return null;
	}
}
