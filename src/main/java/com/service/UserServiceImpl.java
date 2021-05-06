package com.service;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    /*private static Long COUNTER = 1l;
    public static List<User> list = new ArrayList<>();*/

    @Autowired
    UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        if(findById(id) != null) {
            repository.deleteById(id);
            return true;
        }
        else
            return false;
    }

    @Override
    public User update(User user) {
        User toUpdateUser = repository.findById(user.getId()).get();
        if(user!=null) {
            if (user.getUsername() != null)
                toUpdateUser.setUsername(user.getUsername());
            if (user.getPassword() != null)
                toUpdateUser.setPassword(encoder.encode(user.getPassword()));
            if (user.getFirstName() != null)
                toUpdateUser.setFirstName(user.getFirstName());
            if (user.getLastName() != null)
                toUpdateUser.setLastName(user.getLastName());
            if (user.getCountry() != null)
                toUpdateUser.setCountry(user.getCountry());
            if (user.getAge() != null)
                toUpdateUser.setAge(user.getAge());
        }
        /*list.stream().filter(u -> u.getId() == toUpdateUser.getId()).forEach(u -> {
            u.setfName(user.getfName());
            u.setlName(user.getlName());
            u.setCountry(user.getCountry());
            u.setAge(user.getAge());
        });*/
        return repository.save(toUpdateUser);
    }

    @Override
    public List<User> findByCriteria(String criteria, String searchStr) {
        switch (criteria) {
            case "username":
                return this.repository.findByUsername(searchStr);
            case "firstname":
                return this.repository.findByFirstName(searchStr);
            case "lastname":
                return this.repository.findByLastName(searchStr);
            case "age":
                try {
                    Integer age = Integer.valueOf(searchStr);
                    return this.repository.findByAge(age);
                } catch (NumberFormatException e) {
                    System.out.println("Error...");
                }
                return new ArrayList<>();
            case "country":
                return this.repository.findByCountry(searchStr);
        }
        return new ArrayList<>();
    }
}
