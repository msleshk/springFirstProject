package org.example.springcourse1.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;
    private int people_id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 1, max = 30, message = "Author should be between 1 and 30 characters")
    private String author;
    @Max(value = 2024, message = "Year shouldn't be greater than 2024")
    private int yearOfCreation;
    public Book(){}
    public Book(int id, String name, String author, int yearOfCreation) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfCreation = yearOfCreation;
    }
    public int getPeople_id(){ return people_id;}

    public void setPeople_id(int people_id){this.people_id=people_id;}

    public int getId() {return id;}

    public String getName() {return name;}

    public String getAuthor() {return author;}

    public int getYearOfCreation() {return yearOfCreation;}

    public void setId(int id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setAuthor(String author) {this.author = author;}

    public void setYearOfCreation(int yearOfCreation) {this.yearOfCreation = yearOfCreation;}
}
