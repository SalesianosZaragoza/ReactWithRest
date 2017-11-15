package es.salesianos.service;

import javax.servlet.http.HttpServletRequest;

public interface Service {

	void createNewUserFromRequest(HttpServletRequest req);

}
