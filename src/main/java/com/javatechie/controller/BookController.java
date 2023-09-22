package com.javatechie.controller;

import com.javatechie.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private JdbcClient jdbcClient;

    @PostMapping
    public String addNewBook(@RequestBody Book book) {
        var updated = jdbcClient.sql("INSERT INTO book(id,name,title) values(?,?,?)")
                .params(List.of(book.id(),book.name(),book.title()))
                .update();
        return "New Record added to the system";
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return jdbcClient
                .sql("SELECT id, name, title FROM book")
                .query(Book.class)
                .list();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable int id) {
        return jdbcClient
                .sql("SELECT id, name, title FROM book WHERE id = :id")
                .param("id", id)
                .query(Book.class)
                .optional();
    }

    @PutMapping("/{id}")
    public String updateBook(@RequestBody Book book, @PathVariable int id) {
        var updated = jdbcClient
                .sql("update book set title = ?, name = ? where id = ?")
                .params(List.of(book.title(),book.name(),id))
                .update();
        return "Record modified !";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        var updated = jdbcClient.sql("delete from book where id = :id")
                .param("id", id)
                .update();
        return "Record removed !";
    }
}
