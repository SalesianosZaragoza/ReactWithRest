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
@Profile("zaragoza")
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

	@Override
	public List<User> listAllUser() {
		return repository.listAllUsers();
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
		String defaultTableName = "user";
		this.delete(defaultTableName, id);
	}

	@Override
	public void delete(String tablename, Integer id) {
		repository.delete(tablename, id);
	}

}
