package org.example.springcourse1.DAO;

import org.example.springcourse1.models.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book=new Book();
        book.setId(rs.getInt("id"));
        book.setPeople_id(rs.getInt("people_id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setYearOfCreation(rs.getInt("year_of_creation"));
        return book;
    }
}
