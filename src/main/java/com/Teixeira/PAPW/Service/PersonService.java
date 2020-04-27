package com.Teixeira.PAPW.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Teixeira.PAPW.Domain.Person;
import com.Teixeira.PAPW.Repository.PersonRepository;

import javassist.NotFoundException;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person salvar(Person person) {
		return personRepository.save(person);
	}

	public Person consultaPorId(int id) throws NotFoundException {
		Optional<Person> personOpt = personRepository.findById(id);

		if (personOpt.isEmpty()) {
			throw new NotFoundException("Pessoa não localizada");
		}

		return personOpt.get();

	}

	public List<Person> listar() {
		return personRepository.findAll();
	}

	public Person update(int id, Person person) throws NotFoundException {
		consultaPorId(id); // Para checar se person existe mesmo, se não existir lança exception
		person.setID(id);
		return personRepository.save(person);
	}

	public void deletePorId(int id) throws NotFoundException {
		consultaPorId(id);
		personRepository.deleteById(id);
	}

}
