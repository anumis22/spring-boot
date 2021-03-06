package com.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String country;
    @NotNull
    private Integer age;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String country, int age) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects
            .equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects
            .equals(lastName, user.lastName) && Objects.equals(country, user.country) && Objects
            .equals(age, user.age);
    }

    @Override public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, country, age);
    }

    @Override public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password
            + '\'' + ", fName='" + firstName + '\'' + ", lName='" + lastName + '\'' + ", country='"
            + country + '\'' + ", age=" + age + '}';
    }
}
