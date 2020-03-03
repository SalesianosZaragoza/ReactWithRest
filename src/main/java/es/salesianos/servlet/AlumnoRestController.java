package es.salesianos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.salesianos.model.Alumno;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(value = "/api/ALUMNO")
public class AlumnoRestController {

	List<Alumno> alumnos = new ArrayList<Alumno>();
	int id = 1;

	@GetMapping
	@RequestMapping(value = "/INSERT")
	public ResponseEntity<Alumno> create(@RequestBody Alumno alumno) {
		alumno.setId(this.id);
		alumnos.add(alumno);
		id++;

		return new ResponseEntity<>(alumno, HttpStatus.CREATED);
	}

	@PostMapping
	@RequestMapping(value = "/LIST")
	public ResponseEntity<List<Alumno>> ListAll() {

		return new ResponseEntity<>(alumnos, HttpStatus.CREATED);
	}

	@GetMapping
	@RequestMapping(value = "DELETE/")
	public ResponseEntity<List<Alumno>> Delete(@RequestParam Integer id) {
		Alumno al = null;
		for (int i = 0; i <= alumnos.size(); i++) {
			if (i == id) {
				al = alumnos.get(i);
			}
		}
		alumnos.remove(al);

		// Se elimina al alumno y se devuelve la lista actual de alumnos
		return new ResponseEntity<>(alumnos, HttpStatus.CREATED);
	}

	@GetMapping
	@RequestMapping(value = "/{id}/")
	public ResponseEntity<List<Alumno>> UpdateAlumno(@PathVariable("id") int id,
			@RequestParam(value = "fct") String fct) {

		for (Alumno al : alumnos) {
			if (al.getId() == id) {
				al.setFct(fct);
			}
		}

		return new ResponseEntity<>(alumnos, HttpStatus.CREATED);
	}
}
