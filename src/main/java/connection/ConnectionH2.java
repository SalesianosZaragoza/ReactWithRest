package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import es.salesianos.model.User;

public class ConnectionH2 implements ConnectionManager {

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insert(User userFormulario) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO USER (dni,nombre,apellido)" + "VALUES (?, ?, ?)");
			preparedStatement.setString(1, userFormulario.getDni());
			preparedStatement.setString(2, userFormulario.getNombre());
			preparedStatement.setString(3, userFormulario.getApellido());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			close(preparedStatement);
		}

		close(conn);
	}



	public Connection open(String jdbcUrl) {
		Connection conn = null;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(jdbcUrl + ";INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'", "sa",
					"");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}

	public void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
	private void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Optional<User> search(User user) {
		User person = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("SELECT * FROM person WHERE dni = ?");
			preparedStatement.setString(1, user.getDni());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				person = new User();
				person.setDni(resultSet.getString("dni"));
				person.setNombre(resultSet.getString("nombre"));
				person.setApellido(resultSet.getString("apellido"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
			close(conn);
		}

		return Optional.ofNullable(person);
		
	}

	public void update(User user) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = open(jdbcUrl);
			preparedStatement = conn.prepareStatement("UPDATE user SET " +
					"nombre = ?, apellido = ? WHERE dni = ?");

			preparedStatement.setString(1, user.getNombre());
			preparedStatement.setString(2, user.getApellido());
			preparedStatement.setString(3, user.getDni());
			preparedStatement.executeUpdate();

			System.out.println("UPDATE user SET " +
					"nombre = ?, apellido = ? WHERE dni = ?");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
			close(conn);
		}
	}

	public List<User> listAllUsers() {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = open(jdbcUrl);
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
		} finally {
			close(resultSet);
			close(statement);
			close(conn);
		}

		return users;
	}

}


