package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Setter method for Spring XML configuration dependency injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void registerBook(String title) {
        System.out.println("[BookService] Processing registration for book: \"" + title + "\"");
        bookRepository.addBook(title);
    }
    
    public void removeBook(String title) {
        System.out.println("[BookService] Processing removal for book: \"" + title + "\"");
        bookRepository.deleteBook(title);
    }
}
