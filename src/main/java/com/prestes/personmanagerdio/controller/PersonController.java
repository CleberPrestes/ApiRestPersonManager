package com.prestes.personmanagerdio.controller;


import com.prestes.personmanagerdio.dto.request.PersonDTO;
import com.prestes.personmanagerdio.dto.response.MessageResponseDTO;
import com.prestes.personmanagerdio.dto.response.MessageResponseDTO;
import com.prestes.personmanagerdio.exception.PersonNotFoundException;
import com.prestes.personmanagerdio.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller
@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {


	private PersonService personService;



	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}

	@GetMapping
	public List<PersonDTO> getAll() {

		return personService.listAll();

	}

	@GetMapping("/{id}")
	public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
		return personService.findById(id);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
		personService.delete(id);
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
		return personService.updateById(id, personDTO);
	}





}