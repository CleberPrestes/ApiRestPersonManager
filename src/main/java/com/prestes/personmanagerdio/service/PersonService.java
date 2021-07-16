package com.prestes.personmanagerdio.service;


import com.prestes.personmanagerdio.dto.request.PersonDTO;
import com.prestes.personmanagerdio.dto.response.MessageResponseDTO;
import com.prestes.personmanagerdio.entity.Person;
import com.prestes.personmanagerdio.exception.PersonNotFoundException;
import com.prestes.personmanagerdio.mapper.PersonMapper;
import com.prestes.personmanagerdio.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {


	private PersonRepository personRepository;

	private final PersonMapper mapper = PersonMapper.INSTANCE;



	@PostMapping
	public MessageResponseDTO createPerson(PersonDTO personDTO) {

		Person personToSave = mapper.toModel(personDTO);//convert PersonDTO to Person

		Person savedPerson = personRepository.save(personToSave);

		return createMessageResponse(savedPerson.getId(), "Create person by Id ");

	}


	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();

		return allPeople.stream().map(mapper::toDTO).collect(Collectors.toList());

	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);

		return mapper.toDTO(person);
	}




	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
	}

	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

		verifyIfExists(id);
		Person personToUpdate = mapper.toModel(personDTO);//convert PersonDTO to Person

		Person updatePerson = personRepository.save(personToUpdate);

		return createMessageResponse(updatePerson.getId(), "Updated person by Id ");

	}
	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}


	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}
}
