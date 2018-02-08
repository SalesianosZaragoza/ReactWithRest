package es.salesianos.repository;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import es.salesianos.model.User;

@Component
public class UserRepository {

	private static Logger log = LogManager.getLogger(UserRepository.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;

	public void insert(User userFormulario) {
		log.debug("el log funciona");
		String sql = "INSERT INTO USER (dni,nombre,apellido)" + "VALUES ( :dni, :nombre, :apellido)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("dni", userFormulario.getDni());
		params.addValue("nombre", userFormulario.getNombre());
		params.addValue("apellido", userFormulario.getApellido());
		namedJdbcTemplate.update(sql, params);
	}

	public Optional<User> search(User user) {
		String sql = "SELECT * FROM USER WHERE dni = ?";
		log.debug("ejecutando la consulta: " + sql);
		User person = null;
		try {
			person = (User) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(User.class), user.getDni());
		} catch (EmptyResultDataAccessException e) {
			log.error("error", e);
		}
		return Optional.ofNullable(person);

	}

	public void update(User user) {
		String sql = "UPDATE user SET " + "nombre = ?, apellido = ? WHERE dni = ?";
		jdbcTemplate.update(sql, user.getNombre(), user.getApellido(), user.getDni());
	}

	public List<User> listAllUsers() {
		String sql = "SELECT * FROM USER";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
		return users;
	}

	public void delete(String tablename, Integer id) {
		// log.debug("id: " + id.toString());
		log.debug("tablename: " + tablename);
		String sql = "DELETE FROM " + tablename + " WHERE dni = '?'";
		log.debug(sql);
		// jdbcTemplate.update(sql, id.toString());
	}


}
