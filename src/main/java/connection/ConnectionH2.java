package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.salesianos.model.User;

public class ConnectionH2 implements ConnectionManager {
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insert(User userFormulario) {
		Connection conn = open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn
					.prepareStatement("INSERT INTO USER (dni,nombre,apellido)" + "VALUES (?, ?, ?)");
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

	public User search(User user) {
		User user2 = new User();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;;
		try {
			connection = open(jdbcUrl);
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE dni = ?");
			preparedStatement.setString(3, user.getDni());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user2.setDni(resultSet.getString("dni"));
				user2.setNombre(resultSet.getString("nombre"));
				user2.setApellido(resultSet.getString("apellido"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement);
			close(connection);
		}
		// TODO Auto-generated method stub
		return null;
	}

	public void update(User user) {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = open(jdbcUrl);
			preparedStatement = connection.prepareStatement("UPDATE user SET " +
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
			close(connection);
		}
	}

}
