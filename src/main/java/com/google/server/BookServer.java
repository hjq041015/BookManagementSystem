package com.google.server;

import com.google.entity.Book;
import com.google.entity.Borrow;

import java.util.List;
import java.util.Map;

public interface BookServer {
    List<Borrow> getBorrowList();
    Map<Book, Boolean> getBookList();
    void addBorrow(int sid,int bid);
    List<Book> getActiveBookList();
    void deleteBorrow(int id);
    void addBook(String title, String desc , double price);
    void deleteBook(int bid);
}
