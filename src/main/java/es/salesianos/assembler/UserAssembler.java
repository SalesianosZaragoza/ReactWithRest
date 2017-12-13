package es.salesianos.assembler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import es.salesianos.model.User;

@Component
public class UserAssembler {

	public User createUserFromRequest(HttpServletRequest request) {

		User user = new User();
		user.setDni(request.getParameter("dni"));
		user.setNombre(request.getParameter("nombre"));
		user.setApellido(request.getParameter("apellido"));
		return user;
	}

}
