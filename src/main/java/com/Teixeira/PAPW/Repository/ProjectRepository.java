package com.Teixeira.PAPW.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Teixeira.PAPW.Domain.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
