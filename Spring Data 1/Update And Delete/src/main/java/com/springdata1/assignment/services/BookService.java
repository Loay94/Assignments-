package com.springdata1.assignment.services;

import com.springdata1.assignment.models.Book;
import com.springdata1.assignment.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    // adding the book repository as a dependencycopy
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // returns all the books
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    // creates a book
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    // retrieves a book
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }

    public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
        Book findBook= findBook(id);
        if(findBook != null){findBook.setTitle(title);
            findBook.setDescription(desc);
            findBook.setLanguage(lang);
            findBook.setNumberOfPages(numOfPages);
            return bookRepository.save(findBook);
        }
        else return null;

    }

    public void deleteBook(Long id) {
        Book deleteBook= findBook(id);
        if(deleteBook != null){
            bookRepository.deleteById(id);
        }
    }
}