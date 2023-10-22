package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<Book>> list()
    {
        var books = bookService.readAll();
        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<Boolean> addBook(@RequestBody Book book)
    {
        return bookService.create(book) == true
                ? new ResponseEntity<>(true, HttpStatus.CREATED)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    private ResponseEntity<Book> getOneBook(@PathVariable Long id) {
        var book =  bookService.read(id);
        return book != null && !book.isEmpty()
                ? new ResponseEntity<>(book.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable(name="id") long id, @RequestBody Book book) {
        var bookFromDbOpt = bookService.read(id);
        if(bookFromDbOpt.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var bookFromDb = bookFromDbOpt.get();
        BeanUtils.copyProperties(book, bookFromDb, "id");
        var i = bookFromDb.getId();
        return bookService.update(book, i) == true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return bookService.delete(id) == true
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}