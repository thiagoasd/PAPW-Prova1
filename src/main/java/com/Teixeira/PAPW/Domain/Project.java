package com.Teixeira.PAPW.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int ID;

	@NotBlank
	String projectTitle;

	@NotBlank
	int managerId;

	@NotNull
	Long minSalary;

	@NotNull
	Long maxSalary;

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

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
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
