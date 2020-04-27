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

import com.Teixeira.PAPW.Domain.Department;
import com.Teixeira.PAPW.Service.DepartmentService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/department")
public class DepartmentRest {

	@Autowired
	private DepartmentService departmentService;

	// CREATE
	@PostMapping("")
	public ResponseEntity<Department> salvar(@RequestBody @Valid Department department) {

		return ResponseEntity.ok(departmentService.salvar(department));
	}

	// READ
	@GetMapping("/{id}")
	public ResponseEntity<Department> getDepartment(@PathVariable int id) {
		try {
			return ResponseEntity.ok(departmentService.consultaPorId(id));
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

	// READALL
	@GetMapping("")
	public ResponseEntity<List<Department>> getAllDepartment() {
		return ResponseEntity.ok(departmentService.listar());
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Department> update(@RequestBody @Valid Department department, @PathVariable int id) {
		if (department == null) {
			return null;
		} else {
			try {
				return ResponseEntity.ok(departmentService.update(id, department));
			} catch (NotFoundException e) {
				System.out.println(e.getMessage() + " com ID " + id);
				return ResponseEntity.notFound().build();
			}
		}
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Department> deletePorId(@PathVariable int id) {
		try {
			departmentService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

}
