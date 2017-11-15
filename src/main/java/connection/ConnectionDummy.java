package connection;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import es.salesianos.model.User;

@Component
public class ConnectionDummy implements ConnectionManager {

	public Connection open(String jdbcUrl) {
		return null;
	}

	public void close(Connection conn) {

	}

	public void insert(User user) {
		System.out.println("insert ficticio");

	}

	public Optional<User> search(User user) {
		return Optional.empty();
	}

	public void update(User user) {

	}

	public List<User> listAllUsers() {
		return Collections.EMPTY_LIST;
	}

}
