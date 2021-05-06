package com.component;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("anuM", UUID.randomUUID().toString(), "Anu","Mishra","Czechoslovakia",29);
        User user2 = new User("swatiG", UUID.randomUUID().toString(), "Swati","Gangwar","UK",29);
        User user3 = new User("krantiD", UUID.randomUUID().toString(), "Krantisinh","Deshmukh","Finland",32);
        User user4 = new User("junaidA", UUID.randomUUID().toString(), "Junaid","Alam","Australia",27);
        User user5 = new User("ayushS", UUID.randomUUID().toString(), "Ayush","Somani","India",31);
        User user6 = new User("nishil", UUID.randomUUID().toString(), "Nishil","Shah","Romania",31);
        List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6);
        usersList.stream().map(user -> {
            user.setPassword(encoder.encode(user.getPassword()));
            return user;
        }).collect(Collectors.toList());
        repository.saveAll(usersList);
    }
}
