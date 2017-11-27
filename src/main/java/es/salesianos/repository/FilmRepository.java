package es.salesianos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.salesianos.connection.AbstractConnection;
import es.salesianos.model.Film;

public class FilmRepository {

	
	private AbstractConnection connection = new AbstractConnection() {

		@Override
		public String getDriver() {
			return "org.h2.Driver";
		}

		@Override
		public String getDatabaseUser() {
			return "sa";
		}

		@Override
		public String getDatabasePassword() {
			return "";
		}
	};

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test;INIT=RUNSCRIPT FROM 'classpath:scripts/create.sql'";

	public void insert(Film filmFormulario) {
		Connection conn = connection.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO FILM (id,tittle)" + "VALUES (?, ?)");
			preparedStatement.setInt(1, filmFormulario.getId());
			preparedStatement.setString(2, filmFormulario.getTittle());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
		}

		connection.close(conn);
	}





	public Optional<Film> search(Film film) {
		Film person = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;

		try {
			conn = connection.open(jdbcUrl);
			preparedStatement = conn.prepareStatement("SELECT * FROM Film WHERE dni = ?");
			preparedStatement.setInt(1, film.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				person = new Film();
				person.setId(resultSet.getInt("id"));
				person.setTittle(resultSet.getString("tittle"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
			connection.close(conn);
		}

		return Optional.ofNullable(person);

	}

	public void update(Film user) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = connection.open(jdbcUrl);
			preparedStatement = conn.prepareStatement("UPDATE film SET " + "nombre = ?, apellido = ? WHERE dni = ?");

			preparedStatement.setInt(1, user.getId());
			preparedStatement.setString(2, user.getTittle());
			preparedStatement.executeUpdate();

			System.out.println("UPDATE film SET " + "tittle = ? WHERE codFilm = ?");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			connection.close(preparedStatement);
			connection.close(conn);
		}
	}

	public List<Film> listAllUsers() {
		List<Film> users = new ArrayList<Film>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			conn = connection.open(jdbcUrl);
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM film");

			while (resultSet.next()) {
				Film person = new Film();
				person.setId(resultSet.getInt("codFilm"));
				person.setTittle(resultSet.getString("nombre"));
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
