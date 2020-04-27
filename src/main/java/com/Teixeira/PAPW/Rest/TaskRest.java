package com.Teixeira.PAPW.Rest;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Teixeira.PAPW.Domain.Task;
import com.Teixeira.PAPW.Service.TaskService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/task")
public class TaskRest {

	@Autowired
	private TaskService taskService;

	// CREATE
	@PostMapping("")
	public ResponseEntity<Task> salvar(@RequestBody @Valid Task task) {

		return ResponseEntity.ok(taskService.salvar(task));
	}

	// READ
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable int id) {
		try {
			return ResponseEntity.ok(taskService.consultaPorId(id));
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

	// READALL
	@GetMapping("")
	public ResponseEntity<List<Task>> getAllTask() {
		return ResponseEntity.ok(taskService.listar());
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@RequestBody @Valid Task task, @PathVariable int id) {
		if (task == null) {
			return null;
		} else {
			try {
				return ResponseEntity.ok(taskService.update(id, task));
			} catch (NotFoundException e) {
				System.out.println(e.getMessage() + " com ID " + id);
				return ResponseEntity.notFound().build();
			}
		}
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Task> deletePorId(@PathVariable int id) {
		try {
			taskService.deletePorId(id);
			return ResponseEntity.ok().build();
		} catch (NotFoundException e) {
			System.out.println(e.getMessage() + " com ID " + id);
			return ResponseEntity.notFound().build();
		}
	}

	// CUSTOMQUERY
	@GetMapping("/{start}/{end}")
	public ResponseEntity<List<Task>> getTaskByDate(@PathVariable("start") String start,
			@PathVariable("end") String end) {
		System.out.println(start + end);

		return ResponseEntity.ok(taskService.getTaskByDate(start, end));

	}

	// CUSTOMQUERY
	@GetMapping("/{start}/{end}/{status}")
	public ResponseEntity<List<Task>> getTaskByDateAndStatus(@PathVariable("start") String start,
			@PathVariable("end") String end, @PathVariable("status") Long status) {

		if (status != 1 && status != 2) {
			return new ResponseEntity<List<Task>>(HttpStatus.BAD_REQUEST);

		}

		return ResponseEntity.ok(taskService.getTaskByDateAndStatus(start, end, status));

	}

	// CUSTOMQUERY
	@GetMapping("/points/{start}/{end}")
	public ResponseEntity<List<Task>> getTaskByPoints(@PathVariable("start") Long start,
			@PathVariable("end") Long end) {

		return ResponseEntity.ok(taskService.getTaskByPoints(start, end));

	}

}
