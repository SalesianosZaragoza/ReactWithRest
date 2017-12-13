package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.salesianos.assembler.UserAssembler;
import es.salesianos.model.User;
import es.salesianos.repository.UserRepository;

@Component
public class AuthorService implements Service {

	@Autowired
	private UserAssembler assembler;
	@Autowired
	private UserRepository repository;

	public void createNewUserFromRequest(HttpServletRequest req) {
		User user = assembler.createUserFromRequest(req);

		insertOrupdateUser(user);
	}

	public void insertOrupdateUser(User user) {
		if (!repository.search(user).isPresent()) {
			repository.insert(user);
		} else {
			repository.update(user);
		}
	}

	public UserAssembler getAssembler() {
		return assembler;
	}

	public void setAssembler(UserAssembler assembler) {
		this.assembler = assembler;
	}

	public UserRepository getRepository() {
		return repository;
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

}
