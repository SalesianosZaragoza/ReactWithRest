package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.salesianos.assembler.UserAssembler;
import es.salesianos.model.User;
import es.salesianos.repository.UserRepository;

@Component
@Profile("huesca")
public class UserService implements Service {

	@Autowired
	private UserAssembler assembler;
	@Autowired
	private UserRepository repository;

	public void createNewUserFromRequest(HttpServletRequest req) {
		throw new RuntimeException("he reventado");
	}

	public void insertOrupdateUser(User user) {
		throw new RuntimeException("he reventado");
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
