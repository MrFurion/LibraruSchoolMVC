package by.trubetski.springmvc.util;

import by.trubetski.springmvc.dao.BooksDAO;
import by.trubetski.springmvc.dao.PersonDAO;
import by.trubetski.springmvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BooksValidator implements Validator {
    private final BooksDAO booksDAO;

    @Autowired
    public BooksValidator(BooksDAO booksDAO) {
        this.booksDAO = booksDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if(booksDAO.getPersonByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("email", "", "This email is already taken ");
        }
    }
}
