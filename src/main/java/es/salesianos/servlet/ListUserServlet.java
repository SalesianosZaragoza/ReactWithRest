package es.salesianos.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.ConnectionH2;
import connection.ConnectionManager;
import es.salesianos.model.User;

public class ListUserServlet extends HttpServlet {

	ConnectionManager manager = new ConnectionH2();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> listAllUsers = manager.listAllUsers();
		req.setAttribute("users", listAllUsers);
		redirect(req, resp);
	}

	protected void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listado.jsp");
		dispatcher.forward(req, resp);
	}
}
