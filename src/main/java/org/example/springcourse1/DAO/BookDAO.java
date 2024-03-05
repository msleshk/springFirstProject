package org.example.springcourse1.DAO;

import org.example.springcourse1.models.Book;
import org.example.springcourse1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    public List<Book> showAll(){
        return jdbcTemplate.query("SELECT * FROM books", new BookMapper());
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM books WHERE id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }
    public void add(Book book){
        jdbcTemplate.update("INSERT INTO books(name, author, year_of_creation) VALUES (?, ?, ?)", book.getName(), book.getAuthor(), book.getYearOfCreation());
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }
    public void update(Book book){
        jdbcTemplate.update("UPDATE books SET name=?, author=?, year_of_creation=? WHERE id=?", book.getName(), book.getAuthor(), book.getYearOfCreation(), book.getId());
    }
    public void updatePeopleId(Book book){
        jdbcTemplate.update("UPDATE books SET people_id=? WHERE id=?", book.getPeople_id(), book.getId());
    }
    public void deletePeopleID(Book book){
        jdbcTemplate.update("UPDATE books SET people_id=null WHERE id=?", book.getId());
    }
    public List<Book> getOwnedBooks(int id){
        return jdbcTemplate.query("SELECT * FROM books WHERE people_id=?", new Object[]{id}, new BookMapper());
    }
}
