package es.salesianos.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@GetMapping("welcome")
	public String getWelcome() {
		return "login";
	}

	@GetMapping("listado")
	public String getlistado() {
		return "listado";
	}

	@PostMapping("/saveDetails") // it only support port method
	public String saveDetails(@RequestParam("employeeName") String employeeName,
			@RequestParam("employeeEmail") String employeeEmail, ModelMap modelMap) {
		// write your code to save details
		modelMap.put("employeeName", employeeName);
		modelMap.put("employeeEmail", employeeEmail);
		return "viewDetails"; // welcome is view name. It will call welcome.jsp
	}
}
