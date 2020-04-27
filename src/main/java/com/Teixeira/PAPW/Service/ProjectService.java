package com.Teixeira.PAPW.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teixeira.PAPW.DTO.ProjectDTO;
import com.Teixeira.PAPW.Domain.Person;
import com.Teixeira.PAPW.Domain.Project;
import com.Teixeira.PAPW.Repository.ProjectRepository;

import javassist.NotFoundException;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	private PersonService personService;

	public ProjectDTO salvar(ProjectDTO DTO) {

		try {
			personService.consultaPorId(DTO.getManager().getID()); // Se o manager ja existe, Ok. Se não existe, crie.
		} catch (NotFoundException e) {
			Person tmp = personService.salvar(DTO.getManager());
			DTO.setManager(tmp); // por causa da possivel atualização de ID
		}

		Project project = getProject(DTO);
		projectRepository.save(project);

		return DTO;
	}

	public ProjectDTO consultaPorId(int id) throws NotFoundException {
		Optional<Project> projectOpt = projectRepository.findById(id);

		if (projectOpt.isEmpty()) {
			throw new NotFoundException("Departamento não localizado");
		}
		Project proj = projectOpt.get();
		ProjectDTO DTO = new ProjectDTO(proj);
		DTO.setManager(personService.consultaPorId(proj.getManagerId()));

		return DTO;

	}

	public List<ProjectDTO> listar() {
		List<Project> projs = projectRepository.findAll();
		List<ProjectDTO> DTOs = new ArrayList<ProjectDTO>();

		for (Project project : projs) {
			DTOs.add(new ProjectDTO(project));
		}

		return DTOs;
	}

	public ProjectDTO update(int id, ProjectDTO project) throws NotFoundException {
		consultaPorId(id); // Para checar se dept existe mesmo, se não existir lança exception

		project.setID(id); // Salva objeto com a id do path, não do objeto
		return salvar(project);

	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id); //checa se existe
		projectRepository.deleteById(id);
	}

	private Project getProject(ProjectDTO DTO) {
		Project project = new Project();
		project.setID(DTO.getID());
		project.setManagerId(DTO.getManager().getID());
		project.setMaxSalary(DTO.getMaxSalary());
		project.setMinSalary(DTO.getMinSalary());
		project.setProjectTitle(DTO.getProjectTitle());
		return project;
	}

}
