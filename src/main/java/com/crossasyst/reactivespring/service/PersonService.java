package com.crossasyst.reactivespring.service;

import com.crossasyst.reactivespring.dto.PersonRequest;
import com.crossasyst.reactivespring.entity.PersonEntity;
import com.crossasyst.reactivespring.mapper.PersonMapper;
import com.crossasyst.reactivespring.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;

    }

    public Flux<PersonEntity> getAllPerson() {

        return personRepository.findAll();
    }

    public Mono<PersonRequest> getById(Long id) {

        Mono<PersonEntity> personEntity = personRepository.findById(id);
        PersonRequest personRequest = personMapper.entityToModel(personEntity);

        return personRequest;
    }

    public Mono<PersonEntity> addPerson(PersonEntity person) {
        return personRepository.save(person);
    }

    public Mono<Void> deletePerson(Long id) {

        return personRepository.deleteById(id);
    }

    public Mono<PersonEntity> updatePerson(Long id, Mono<PersonEntity> person) {
        return this.personRepository.findById(id).flatMap(p-> person.map(u->{
            p.setFirstname(u.getFirstname());
            p.setLastname(u.getLastname());
            return p;
        })).flatMap(p-> this.personRepository.save(p));
    }
}
