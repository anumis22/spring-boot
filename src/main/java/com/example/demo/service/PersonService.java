package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonDao dao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao dao) {
        this.dao = dao;
    }

    public int addPerson(Person p){
        return dao.insertPerson(p);
    }
}
