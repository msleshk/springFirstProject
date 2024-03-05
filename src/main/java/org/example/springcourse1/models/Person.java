package org.example.springcourse1.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

public class Person {
    private int id;
    @Size(max = 50, message = "Name shouldn't be more than 50 characters")
    @NotEmpty(message = "Name shouldn't be empty")
    private String name;
    @Min(value = 1, message = "Age should be more than 1 year")
    private int age;
    public Person(){};
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;}
    public void setId(int id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setAge(int age) {this.age = age;}
    public int getId() {return id;}
    public String getName() {return name;}
    public int getAge() {return age;}
}
