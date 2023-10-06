package by.trubetski.springmvc.models;
import javax.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Author should be between 2 and 100 characters")
    private String fullName;
    @Min(value = 1900, message = "Age should be greater then 0")
    @Max(value = 3000, message = "Age should be before 150")
    private int age;
    public Person() {
    }

    public Person(int id, String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
