package by.trubetski.springmvc.dao;

import by.trubetski.springmvc.models.Books;
import by.trubetski.springmvc.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Books> index () {
        return jdbcTemplate.query("SELECT * FROM Books ", new BeanPropertyRowMapper<>(Books.class));
    }


    public Books show(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Books.class))
                .stream().findAny().orElse(null);
    }

    public void save(@Valid Books books) {

        jdbcTemplate.update("INSERT INTO Books(name, author, age) VALUES (?, ?, ?)", books.getName(), books.getAuthor(), books.getAge());
    }

    public void update(int id, Books books) {
        jdbcTemplate.update("UPDATE Books SET name=?, author=?, age=? WHERE id=?", books.getName(), books.getAuthor(), books.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    //    public List<Books> getBooksByPersonId(int id) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Books.class));
//    }
    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
