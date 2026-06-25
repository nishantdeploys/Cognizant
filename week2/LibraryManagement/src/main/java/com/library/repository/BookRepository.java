package com.library.repository;

public class BookRepository {
    
    public void addBook(String title) {
        System.out.println("[BookRepository] Successfully saved book: \"" + title + "\" to the database.");
    }
    
    public void deleteBook(String title) {
        System.out.println("[BookRepository] Successfully deleted book: \"" + title + "\" from the database.");
    }
}
