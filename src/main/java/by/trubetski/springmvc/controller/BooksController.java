package by.trubetski.springmvc.controller;

import by.trubetski.springmvc.dao.BooksDAO;
import by.trubetski.springmvc.dao.PersonDAO;
import by.trubetski.springmvc.models.Books;
import by.trubetski.springmvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", booksDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksDAO.show(id));

        Optional<Person> bookUser = booksDAO.getUserBook(id);

        if (bookUser.isPresent()){
            model.addAttribute("user", bookUser.get());
        }else {
            model.addAttribute("people", personDAO.index());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBooks(@ModelAttribute("books") Books books) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("books") @Valid Books books,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";

        booksDAO.save(books);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Books books, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        booksDAO.update(id, books);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}/freedomBook")
    public String freedomBook(@PathVariable("id") int id){
        booksDAO.freedomBook(id);
        return "redirect:/books/" + id;
    }
    @PatchMapping("/{id}/joinBook")
    public String joinBook(@PathVariable("id")int id, @ModelAttribute("person") Person selectPerson){
        booksDAO.joinBook(id, selectPerson);
        return "redirect:/books/" + id;
    }
}
