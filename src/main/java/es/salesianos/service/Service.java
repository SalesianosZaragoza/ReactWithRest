package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

import es.salesianos.model.User;

public interface Service {

	void createNewUserFromRequest(HttpServletRequest req);

	void insertOrupdateUser(User user);

}
