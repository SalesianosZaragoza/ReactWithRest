package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.salesianos.connection.AbstractConnection;
import es.salesianos.model.User;

public class UserRepository {

	AbstractConnection connection;

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insert(User userFormulario) {
		return;
		/*
		 * Connection conn = connection.open(jdbcUrl); PreparedStatement
		 * preparedStatement = null; try { preparedStatement =
		 * conn.prepareStatement("INSERT INTO USER (dni,nombre,apellido)" +
		 * "VALUES (?, ?, ?)"); preparedStatement.setString(1, userFormulario.getDni());
		 * preparedStatement.setString(2, userFormulario.getNombre());
		 * preparedStatement.setString(3, userFormulario.getApellido());
		 * preparedStatement.executeUpdate(); } catch (SQLException e) {
		 * e.printStackTrace(); throw new RuntimeException(e); } finally {
		 * connection.close(preparedStatement); }
		 * 
		 * connection.close(conn);
		 */
	}





	public Optional<User> search(User user) {
		return Optional.empty();
		/*
		 * User person = null; PreparedStatement preparedStatement = null; ResultSet
		 * resultSet = null; Connection conn = null;
		 * 
		 * try { conn = connection.open(jdbcUrl); preparedStatement =
		 * conn.prepareStatement("SELECT * FROM USER WHERE dni = ?");
		 * preparedStatement.setString(1, user.getDni()); resultSet =
		 * preparedStatement.executeQuery();
		 * 
		 * while (resultSet.next()) { person = new User();
		 * person.setDni(resultSet.getString("dni"));
		 * person.setNombre(resultSet.getString("nombre"));
		 * person.setApellido(resultSet.getString("apellido")); }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); throw new RuntimeException(e); }
		 * finally { connection.close(preparedStatement); connection.close(conn); }
		 * 
		 * return Optional.ofNullable(person);
		 */
	}

	public void update(User user) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = connection.open(jdbcUrl);
			preparedStatement = conn.prepareStatement("UPDATE user SET " + "nombre = ?, apellido = ? WHERE dni = ?");

			preparedStatement.setString(1, user.getNombre());
			preparedStatement.setString(2, user.getApellido());
			preparedStatement.setString(3, user.getDni());
			preparedStatement.executeUpdate();

			System.out.println("UPDATE user SET " + "nombre = ?, apellido = ? WHERE dni = ?");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
			connection.close(conn);
		}
	}

	public List<User> listAllUsers() {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = connection.open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM user");

			while (resultSet.next()) {
				User person = new User();
				person.setDni(resultSet.getString("dni"));
				person.setNombre(resultSet.getString("nombre"));
				person.setApellido(resultSet.getString("apellido"));

				users.add(person);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(resultSet);
			connection.close(statement);
			connection.close(conn);
		}

		return users;
	}

}
