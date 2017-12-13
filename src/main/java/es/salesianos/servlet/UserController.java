package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.salesianos.model.User;
import es.salesianos.service.Service;

@Controller
public class UserController {

	@Autowired
	private Service service;

	@GetMapping("welcome")
	public String getWelcome() {
		return "login";
	}

	@GetMapping("listado")
	public String getlistado() {
		return "listado";
	}

	@PostMapping("/welcome") // it only support port method
	public String saveUser(@ModelAttribute User user) {
		service.insertOrupdateUser(user);
		return "welcome";
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
