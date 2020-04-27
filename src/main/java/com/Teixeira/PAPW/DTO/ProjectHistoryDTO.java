package com.Teixeira.PAPW.DTO;

import java.time.LocalDate;

import com.Teixeira.PAPW.Domain.Person;
import com.Teixeira.PAPW.Domain.ProjectHistory;

public class ProjectHistoryDTO {

	LocalDate startDate;
	LocalDate endDate;
	Person manager;
	int ID;

	public ProjectHistoryDTO(LocalDate startDate, LocalDate endDate, Person manager) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.manager = manager;
	}

	public ProjectHistoryDTO(ProjectHistory projectHistory) {
		this.startDate = projectHistory.getStartDate();
		this.endDate = projectHistory.getEndDate();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

}
