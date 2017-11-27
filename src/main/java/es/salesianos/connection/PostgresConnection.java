package es.salesianos.connection;

public class PostgresConnection extends AbstractConnection {

	@Override
	public String getDriver() {
		return "org.postgresql.Driver";
	}

	@Override
	public String getDatabaseUser() {
		return "postgres";
	}

	@Override
	public String getDatabasePassword() {
		return "postgres";
	}

}
