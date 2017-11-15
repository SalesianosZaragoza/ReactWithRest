package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

import connection.ConnectionH2;
import connection.ConnectionManager;
import es.salesianos.assembler.UserAssembler;
import es.salesianos.model.User;

public class UserService implements Service {

	UserAssembler assembler = new UserAssembler();
	ConnectionManager manager = new ConnectionH2();

	public void createNewUserFromRequest(HttpServletRequest req) {
		User user = assembler.createUserFromRequest(req);

		if (!manager.search(user).isPresent()) {
			manager.insert(user);
		} else {
			manager.update(user);
		}
	}

}
