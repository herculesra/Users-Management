package com.digitalinnovationone.personapi.services;

import com.digitalinnovationone.personapi.dto.request.PersonDTO;
import com.digitalinnovationone.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.personapi.entitys.Person;
import com.digitalinnovationone.personapi.mapper.PersonMapper;
import com.digitalinnovationone.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){

//        Para não ter toda essa trabalheira:
//        Person personToSave = Person.builder()
//                .firstName(personDTO.getFirstName())
//                .lastName(personDTO.getLastName())
//                .cpf((personDTO.getCpf()))
//                .birthDate(personDTO.getBirthDate())
//                .build();
//        Usa a lib MapStruct

        Person personToSave = personMapper.toModel(personDTO); // assim substituimos e fazemos a conversão do campo birthDate

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
