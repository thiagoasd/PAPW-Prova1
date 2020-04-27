package com.Teixeira.PAPW.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teixeira.PAPW.Domain.Department;
import com.Teixeira.PAPW.Repository.DepartmentRepository;

import javassist.NotFoundException;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department salvar(Department department) {
		return departmentRepository.save(department);
	}

	public Department consultaPorId(int id) throws NotFoundException {
		Optional<Department> deptOpt = departmentRepository.findById(id);

		if (deptOpt.isEmpty()) {
			throw new NotFoundException("Departamento não localizado");
		}

		return deptOpt.get();

	}

	public List<Department> listar() {
		return departmentRepository.findAll();
	}

	public Department update(int id, Department department) throws NotFoundException {
		consultaPorId(id); // Para checar se dept existe mesmo, se não existir lança exception
		department.setID(id); // Salva objeto com a id do path, não do objeto
		return departmentRepository.save(department);
	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id);
		departmentRepository.deleteById(id);
	}

}
