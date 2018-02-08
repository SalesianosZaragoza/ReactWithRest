package es.salesianos.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.salesianos.assembler.UserAssembler;
import es.salesianos.model.User;
import es.salesianos.repository.UserRepository;

@Component
@Profile("huesca")
public class ErrorService implements Service {

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

	@Override
	public List<User> listAllUser() {
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

	@Override
	public void delete(Integer id) {
		throw new RuntimeException("he reventado");
	}

	@Override
	public void delete(String tablename, Integer id) {
		throw new RuntimeException("he reventado");

	}

}
