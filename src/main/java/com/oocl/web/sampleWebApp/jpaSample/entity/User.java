package com.oocl.web.sampleWebApp.jpaSample.entity;

import javax.persistence.*;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int age;

    @Column(nullable=false,length =64)
    private String name;
    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
