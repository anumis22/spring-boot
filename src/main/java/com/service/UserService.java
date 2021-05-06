package com.service;

import com.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long id);

    void add(User user);

    boolean delete(Long id);

    User update(User u);

    List<User> findByCriteria(String criteria, String searchStr);
}
