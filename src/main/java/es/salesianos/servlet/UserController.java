package es.salesianos.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import es.salesianos.model.User;
import es.salesianos.service.Service;

@Controller
public class UserController {

	@Autowired
	// @Qualifier(value = "authorService")
	private Service service;

	// public String getWelcome() {
	// return "login";
	// }
	// @RequestMapping(value = "/welcome", method = RequestMethod.GET)
	@GetMapping("/welcome")
	public ModelAndView user() {
		return new ModelAndView("login", "command", new User());
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
