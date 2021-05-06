package com.controller;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<Page<User>>(userService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if(user.isPresent())
            return user.get();
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<?> findByCriteria(@RequestParam(name="criteria") String criteria,
        @RequestParam(name="searchStr") String searchStr) {
        return new ResponseEntity<List<User>>(userService.findByCriteria(criteria, searchStr), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return userService.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> update(@Valid @RequestBody User user) {
        User toUpdate = userService.update(user);
        return toUpdate !=null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
