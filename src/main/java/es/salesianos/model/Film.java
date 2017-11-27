package es.salesianos.model;

public class Film implements IEntity<Integer> {

	private Integer codFilm;
	private String tittle;

	public Integer getCodFilm() {
		return codFilm;
	}

	public void setCodFilm(Integer codFilm) {
		this.codFilm = codFilm;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	@Override
	public Integer getId() {
		return getCodFilm();
	}

	@Override
	public void setId(Integer id) {
		setCodFilm(id);
	}

}
