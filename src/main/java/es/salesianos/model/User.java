package es.salesianos.model;

public class User implements IEntity<String> {

	String dni;
	String nombre;
	String apellido;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String getId() {
		return getDni();
	}

	@Override
	public void setId(String id) {
		setDni(id);
	}

}
