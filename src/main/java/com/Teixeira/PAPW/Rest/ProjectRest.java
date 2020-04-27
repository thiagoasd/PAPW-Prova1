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

import com.Teixeira.PAPW.DTO.ProjectDTO;
import com.Teixeira.PAPW.Service.ProjectService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/project")
public class ProjectRest {

	@Autowired
	private ProjectService projectService;

	// CREATE
	@PostMapping("")
	public ResponseEntity<ProjectDTO> salvar(@RequestBody @Valid ProjectDTO DTO) {

		return ResponseEntity.ok(projectService.salvar(DTO));
	}

	// READ
	@GetMapping("/{id}")
	public ResponseEntity<ProjectDTO> getProject(@PathVariable int id) {
		try {
			return ResponseEntity.ok(projectService.consultaPorId(id));
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

	// READALL
	@GetMapping("")
	public ResponseEntity<List<ProjectDTO>> getAllProject() {
		return ResponseEntity.ok(projectService.listar());
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<ProjectDTO> update(@RequestBody @Valid ProjectDTO DTO, @PathVariable int id) {
		if (DTO == null) {
			return null;
		} else {
			try {
				return ResponseEntity.ok(projectService.update(id, DTO));
			} catch (NotFoundException e) {
				System.out.println(e.getMessage() + " com ID " + id);
				return ResponseEntity.notFound().build();
			}
		}
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<ProjectDTO> deletePorId(@PathVariable int id) {
		try {
			projectService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

}
