package com.Teixeira.PAPW.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teixeira.PAPW.DTO.ProjectDTO;
import com.Teixeira.PAPW.DTO.ProjectHistoryDTO;
import com.Teixeira.PAPW.Domain.Person;
import com.Teixeira.PAPW.Domain.ProjectHistory;
import com.Teixeira.PAPW.Repository.ProjectHistoryRepository;

import javassist.NotFoundException;

@Service
public class ProjectHistoryService {

	@Autowired
	private ProjectHistoryRepository projectHistoryRepository;

	@Autowired
	private PersonService personService;

	public ProjectHistoryDTO salvar(ProjectHistoryDTO DTO) {

		try {
			personService.consultaPorId(DTO.getManager().getID()); // Se o manager ja existe, Ok. Se não existe, crie.
		} catch (NotFoundException e) {
			Person tmp = personService.salvar(DTO.getManager());
			DTO.setManager(tmp); // por causa da possivel atualização de ID
		}

		ProjectHistory project = getProjectHistory(DTO);
		project = projectHistoryRepository.save(project);
		DTO = new ProjectHistoryDTO(project);
		try {
			DTO.setManager(personService.consultaPorId(project.getManagerId()));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return DTO;
	}

	public ProjectHistoryDTO consultaPorId(int id) throws NotFoundException {
		Optional<ProjectHistory> projectOpt = projectHistoryRepository.findById(id);

		if (projectOpt.isEmpty()) {
			throw new NotFoundException("ProjectHistory não localizado");
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
			ProjectHistoryDTO tmp = new ProjectHistoryDTO(project);
			try {
				tmp.setManager(personService.consultaPorId(project.getManagerId()));
				DTOs.add(tmp);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return DTOs;
	}

	public ProjectHistoryDTO update(int id, ProjectHistoryDTO DTO) throws NotFoundException {
		consultaPorId(id); // Para checar se dept existe mesmo, se não existir lança exception
		DTO.setID(id); // Salva objeto com a id do path, não do objeto
		return salvar(DTO);

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
		project.setID(DTO.getID());
		return project;
	}

}
