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

		public User searchByDni(String dni) {
		User userFormulario = new User();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionConfiguration.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM userFormulario WHERE dni = ?");
			preparedStatement.setString(1, dni);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				userFormulario.setId(resultSet.getString("dni"));
				userFormulario.setFirstName(resultSet.getString("nombre"));
				userFormulario.setLastName(resultSet.getString("apellido"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	public void update(User userFormulario) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = open(jdbcUrl);
			preparedStatement = connection.prepareStatement("UPDATE userFormulario SET " +
					"nombre = ?, apellido = ? WHERE dni = ?");

			preparedStatement.setString(1, userFormulario.getNombre());
			preparedStatement.setString(2, userFormulario.getApellido());
			preparedStatement.setString(3, userFormulario.getDni);
			preparedStatement.executeUpdate();

			System.out.println("UPDATE userFormulario SET " +
					"nombre = ?, apellido = ? WHERE dni = ?");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
