package com.Teixeira.PAPW.DTO;

import com.Teixeira.PAPW.Domain.Person;
import com.Teixeira.PAPW.Domain.Project;

public class ProjectDTO {

	int ID;
	String projectTitle;
	Person manager;
	Long minSalary;
	Long maxSalary;

	public ProjectDTO(int iD, String projectTitle, Person manager, Long minSalary, Long maxSalary) {
		ID = iD;
		this.projectTitle = projectTitle;
		this.manager = manager;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
	}

	public ProjectDTO(Project project) {

		this.projectTitle = project.getProjectTitle();
		this.minSalary = project.getMinSalary();
		this.maxSalary = project.getMaxSalary();
		this.ID = project.getID();

	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

	public Long getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Long minSalary) {
		this.minSalary = minSalary;
	}

	public Long getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Long maxSalary) {
		this.maxSalary = maxSalary;
	}

}
