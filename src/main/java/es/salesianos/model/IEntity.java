package es.salesianos.model;

import java.io.Serializable;

public interface IEntity<T> extends Serializable {

	public T getId();

	public void setId(T id);


}
