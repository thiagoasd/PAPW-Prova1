package com.Teixeira.PAPW.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Teixeira.PAPW.DTO.ProjectHistoryDTO;
import com.Teixeira.PAPW.Domain.Person;
import com.Teixeira.PAPW.Domain.ProjectHistory;
import com.Teixeira.PAPW.Repository.ProjectHistoryRepository;

import javassist.NotFoundException;

@Service
public class ProjectHistoryService {
	private ProjectHistoryRepository projectHistoryRepository;
	private PersonService personService;

	public ProjectHistoryDTO salvar(ProjectHistoryDTO DTO) {

		try {
			personService.consultaPorId(DTO.getManager().getID()); // Se o manager ja existe, Ok. Se não existe, crie.
		} catch (NotFoundException e) {
			Person tmp = personService.salvar(DTO.getManager());
			DTO.setManager(tmp); // por causa da possivel atualização de ID
		}

		ProjectHistory project = getProjectHistory(DTO);
		projectHistoryRepository.save(project);

		return DTO;
	}

	public ProjectHistoryDTO consultaPorId(int id) throws NotFoundException {
		Optional<ProjectHistory> projectOpt = projectHistoryRepository.findById(id);

		if (projectOpt.isEmpty()) {
			throw new NotFoundException("Departamento não localizado");
		}
		ProjectHistory proj = projectOpt.get();
		ProjectHistoryDTO DTO = new ProjectHistoryDTO(proj);
		DTO.setManager(personService.consultaPorId(proj.getManagerId()));

		return DTO;

	}

	public List<ProjectHistoryDTO> listar() {
		List<ProjectHistory> projs = projectHistoryRepository.findAll();
		List<ProjectHistoryDTO> DTOs = new ArrayList<ProjectHistoryDTO>();

		for (ProjectHistory project : projs) {
			DTOs.add(new ProjectHistoryDTO(project));
		}

		return DTOs;
	}

	public ProjectHistoryDTO update(int id, ProjectHistoryDTO project) throws NotFoundException {
		consultaPorId(id); // Para checar se dept existe mesmo, se não existir lança exception

		project.setID(id); // Salva objeto com a id do path, não do objeto
		return salvar(project);

	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id); // checa se existe
		projectHistoryRepository.deleteById(id);
	}

	private ProjectHistory getProjectHistory(ProjectHistoryDTO DTO) {
		ProjectHistory project = new ProjectHistory();
		project.setManagerId(DTO.getManager().getID());
		project.setStartDate(DTO.getStartDate());
		project.setEndDate(DTO.getEndDate());
		project.setManagerId(DTO.getManager().getID());
		return project;
	}

}
