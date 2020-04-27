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

import com.Teixeira.PAPW.DTO.ProjectHistoryDTO;
import com.Teixeira.PAPW.Service.ProjectHistoryService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/projecthistory")
public class ProjectHistoryRest {

	@Autowired
	private ProjectHistoryService projectHistoryService;

	// CREATE
	@PostMapping("")
	public ResponseEntity<ProjectHistoryDTO> salvar(@RequestBody @Valid ProjectHistoryDTO DTO) {

		DTO.setID(0);
		return ResponseEntity.ok(projectHistoryService.salvar(DTO));
	}

	// READ
	@GetMapping("/{id}")
	public ResponseEntity<ProjectHistoryDTO> getProject(@PathVariable int id) {
		try {
			return ResponseEntity.ok(projectHistoryService.consultaPorId(id));
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

	// READALL
	@GetMapping("")
	public ResponseEntity<List<ProjectHistoryDTO>> getAllProject() {
		return ResponseEntity.ok(projectHistoryService.listar());
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<ProjectHistoryDTO> update(@RequestBody @Valid ProjectHistoryDTO DTO, @PathVariable int id) {
		if (DTO == null) {
			return null;
		} else {
			try {
				return ResponseEntity.ok(projectHistoryService.update(id, DTO));
			} catch (NotFoundException e) {
				System.out.println(e.getMessage() + " com ID " + id);
				return ResponseEntity.notFound().build();
			}
		}
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<ProjectHistoryDTO> deletePorId(@PathVariable int id) {
		try {
			projectHistoryService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

}
