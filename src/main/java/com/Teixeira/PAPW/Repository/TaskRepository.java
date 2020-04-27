package com.Teixeira.PAPW.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Teixeira.PAPW.Domain.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	
	public List<Task> findAllByStartDateBetween(LocalDate startTimeStart, LocalDate startTimeEnd);
	public List<Task> findAllByStatusAndStartDateBetween(Long status, LocalDate startTimeStart, LocalDate startTimeEnd);
	public List<Task> findAllByTaskPointsBetween(Long start, Long end);

}
