package by.trubetski.springmvc.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Books {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message="Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 30, message="Author should be between 2 and 30 characters")
    private String author;
    @Min(value = 0, message = "Age should be greater then 0")
    @Max(value = 3000, message = "Age should be before 3000")
    private int age;

    public Books() {
    }

    public Books(int id, String name, String author, int age) {
        this.name = name;
        this.author = author;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
