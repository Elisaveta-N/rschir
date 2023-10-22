package org.example.service;

import org.example.model.Book;
import org.example.repo.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public boolean create(Book book) {
        try {
            bookRepository.save(book);
            try {
                //emailService.sendNotification(book);
            } catch (Exception e) {
                //log.error("Failed to send email: " + e.getMessage());
            }
            return true;
        }
        catch (Exception e)  {
            //log.error("Failed to save book: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public Book create2(Book book) {
        try {
            var b = bookRepository.save(book);
            try {
                //emailService.sendNotification(book);
            } catch (Exception e) {
                //log.error("Failed to send email: " + e.getMessage());
            }
            return b;
        }
        catch (Exception e)  {
            //log.error("Failed to save book: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<Book> readAll() {
        try {
            //log.info("Read all books");
            return bookRepository.findAll();
        }
        catch (Exception e)  {
            //log.error("Failed to read all books: " + e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Optional<Book> read(long id) {
        try {
            //log.info("Read book by id = {}", id);
            return bookRepository.findById(id);
        }
        catch (jakarta.persistence.EntityNotFoundException e)  {
            //log.error("Failed to read book by id: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    public boolean update(Book book, long id) {
        try {
            //log.info("Update book by id = {}", id);
            book.setId(id);
            bookRepository.save(book);
            return true;
        }
        catch (Exception e)  {
            //log.error("Failed to update book by id: " + e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean delete(long id) {
        //log.info("Delete book by id = {}", id);
        bookRepository.deleteById(id);
        return true;
    }

}
