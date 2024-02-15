package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity(name = "BOOK")
public class Book {


    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public Book() {

    }
// standard constructors

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // standard getters and setters
}
