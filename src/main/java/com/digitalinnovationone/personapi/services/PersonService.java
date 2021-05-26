package com.digitalinnovationone.personapi.services;

import com.digitalinnovationone.personapi.dto.request.PersonDTO;
import com.digitalinnovationone.personapi.dto.response.MessageResponseDTO;
import com.digitalinnovationone.personapi.entitys.Person;
import com.digitalinnovationone.personapi.exceptions.PersonNotFoundException;
import com.digitalinnovationone.personapi.mapper.PersonMapper;
import com.digitalinnovationone.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }


    public List<PersonDTO> listAll(){
        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream()
                .map(personMapper::toDTO) // Vai converter
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException{
//        Pode fazer assim
//        Optional<Person> optionalPerson = personRepository.findById(id);
//        if(optionalPerson.isEmpty()){
//            throw new PersonNotFoundException(id);
//        }
//        ou assim:

        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
//      Se não exister uma pessoa com esse ID ele lança uma exception
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id,PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}
