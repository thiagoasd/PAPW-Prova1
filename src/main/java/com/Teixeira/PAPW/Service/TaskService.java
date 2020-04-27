package com.Teixeira.PAPW.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teixeira.PAPW.Domain.Task;
import com.Teixeira.PAPW.Repository.TaskRepository;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import javassist.NotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task salvar(Task task) {
		return taskRepository.save(task);
	}

	public Task consultaPorId(int id) throws NotFoundException {

		Optional<Task> taskOpt = taskRepository.findById(id);

		if (taskOpt.isEmpty()) {
			throw new NotFoundException("Task não localizada");
		}

		return taskRepository.findById(id).get();
	}

	public List<Task> listar() {
		return taskRepository.findAll();
	}

	public Task update(int id, Task task) throws NotFoundException {
		consultaPorId(id); // Para checar se task existe mesmo, se não existir lança exception

		if (task != null) {
			task.setID(id); // Salva objeto com a id do path, não do objeto
			return taskRepository.save(task);
		} else {
			throw new RuntimeException("ID precisar ser informado");
		}
	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id);
		taskRepository.deleteById(id);
	}

	public List<Task> getTaskByDate(String start, String end) {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);

		return taskRepository.findAllByStartDateBetween(startDate, endDate);
	}

	public List<Task> getTaskByDateAndStatus(String start, String end, Long status) {
		LocalDate startDate = LocalDate.parse(start);
		LocalDate endDate = LocalDate.parse(end);

		return taskRepository.findAllByStatusAndStartDateBetween(status, startDate, endDate);
	}
	
	public List<Task> getTaskByPoints(Long start, Long end) {

		return taskRepository.findAllByTaskPointsBetween(start, end);
	}
	
	
}
