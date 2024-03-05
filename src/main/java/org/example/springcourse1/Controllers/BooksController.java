package org.example.springcourse1.Controllers;

import jakarta.validation.Valid;
import org.example.springcourse1.DAO.BookDAO;
import org.example.springcourse1.DAO.PersonDAO;
import org.example.springcourse1.models.Book;
import org.example.springcourse1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    BookDAO bookDAO;
    PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO){
        this.personDAO=personDAO;
        this.bookDAO=bookDAO;
    }

    @GetMapping()
    public String showBooks(Model model){
        model.addAttribute("books", bookDAO.showAll());
        return "books/showAll";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        Book book=bookDAO.show(id);
        Person person=personDAO.show(book.getPeople_id());
        model.addAttribute("book", book);
        model.addAttribute("person", person);
        model.addAttribute("people", personDAO.showAll());
        return "books/show";
    }
    @PatchMapping("{id}/addBookOwner")
    public String addBookOwner(@ModelAttribute("book") Book book, @RequestParam("personId") int personId){
        book=bookDAO.show(book.getId());
        book.setPeople_id(personId);
        bookDAO.updatePeopleId(book);
        return "redirect:/books/{id}";
    }
    @PatchMapping("{id}/freeBook")
    public String freeTheBook(@PathVariable("id") int id, @ModelAttribute("book") Book book){
        bookDAO.deletePeopleID(book);
        return "redirect:/books/{id}";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        bookDAO.add(book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "/books/edit";
    }
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(book);
        return "redirect:/books";
    }
}
