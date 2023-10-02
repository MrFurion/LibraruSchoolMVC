package by.trubetski.springmvc.models;
import javax.validation.constraints.*;

public class People {
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 30, message="Surname should be between 2 and 30 characters")
    private String surname;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message="Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Patronymic should not be empty")
    @Size(min = 2, max = 30, message="Patronymic should be between 2 and 30 characters")
    private String patronymic;
    @Min(value = 0, message = "Age should be greater then 0")
    @Max(value = 150, message = "Age should be before 150")
    private int age;

    public People(String surname, String name, String patronymic, int age) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
