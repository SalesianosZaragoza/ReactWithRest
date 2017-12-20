package es.salesianos.connection;

import java.sql.Connection;

import es.salesianos.model.User;

public interface ConnectionManager {
	
	public Connection open(String jdbcUrl);
	
	
	public void close(Connection conn);

	void insert(User user);

}
