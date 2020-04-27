package com.Teixeira.PAPW.Rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Teixeira.PAPW.Domain.Person;
import com.Teixeira.PAPW.Service.PersonService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/person")
public class PersonRest {
	
	@Autowired
	private PersonService personService;

	// CREATE
	@PostMapping("")
	public ResponseEntity<Person> salvar(@RequestBody @Valid Person task) {

		return ResponseEntity.ok(personService.salvar(task));
	}

	// READ
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable int id) {
		try {
			return ResponseEntity.ok(personService.consultaPorId(id));
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

	// READALL
	@GetMapping("")
	public ResponseEntity<List<Person>> getAllPerson() {
		return ResponseEntity.ok(personService.listar());
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Person> update(@RequestBody @Valid Person task, @PathVariable int id) {
		if (task == null) {
			return null;
		} else {
			try {
				return ResponseEntity.ok(personService.update(id, task));
			} catch (NotFoundException e) {
				System.out.println(e.getMessage() + " com ID " + id);
				return ResponseEntity.notFound().build();
			}
		}
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Person> deletePorId(@PathVariable int id) {
		try {
			personService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}
	

}
