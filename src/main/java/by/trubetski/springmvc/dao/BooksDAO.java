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


    public Optional<Person> getUserBook(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Books JOIN Person ON Books.person_id = Person.id WHERE Books.id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
    public void freedomBook(int id){
        jdbcTemplate.update("update Books set person_id=null where id=?", id);
    }
    public void joinBook(int id, Person selectPersonId){
        jdbcTemplate.update("update Books set person_id=? where id=?", selectPersonId.getId(), id);
    }
}
