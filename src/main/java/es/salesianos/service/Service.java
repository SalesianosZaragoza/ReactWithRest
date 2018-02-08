package es.salesianos.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.User;

public interface Service {

	void createNewUserFromRequest(HttpServletRequest req);

	void insertOrupdateUser(User user);

	List<User> listAllUser();

	void delete(Integer id);

	void delete(String tablename, Integer id);

}
