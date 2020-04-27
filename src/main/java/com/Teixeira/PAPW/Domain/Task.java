package com.Teixeira.PAPW.Domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int ID;

	@NotBlank
	String title;
	
	@NotBlank
	String description;
	
	@Min(value = 0, message = "Valor deve ser positivo")
	long taskPoints;
	
	@Min(value = 1, message = "Valor deve ser entre 1 e 2 ")
	@Max(value = 2, message = "Valor deve ser entre 1 e 2 ")
	long status;
	
	@NotNull
	LocalDate startDate;
	
	@NotNull
	LocalDate endDate;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getTaskPoints() {
		return taskPoints;
	}

	public void setTaskPoints(long taskPoints) {
		this.taskPoints = taskPoints;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
