package com.google.server.impl;

import com.google.Mappers.BookMapper;
import com.google.entity.Book;
import com.google.entity.Borrow;
import com.google.server.BookServer;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServerImpl implements BookServer{
    @Resource
    BookMapper mapper;
    @Override
    public List<Borrow> getBorrowList() {
        return mapper.getBorrowList();
    }

    @Override
    public Map<Book,Boolean> getBookList() {
        Set<Integer> set = new HashSet<>();
        this.getBorrowList().forEach(borrow -> set.add(borrow.getBid()));
        Map<Book ,Boolean> map = new LinkedHashMap<>();
        mapper.getBookList().forEach(book -> map.put(book,set.contains(book.getBid())));
        return map;
    }

    @Override
    public void addBorrow(int sid, int bid) {
        mapper.addBorrow(sid, bid);
    }

    @Override
    public List<Book> getActiveBookList() {
       Set<Integer> set = new HashSet<>();
       this.getBorrowList().forEach(borrow -> set.add(borrow.getBid()));
       return mapper.getBookList()
               .stream()
               .filter(book -> !set.contains(book.getBid()))
               .toList();
    }

    @Override
    public void deleteBorrow(int id) {
        mapper.deleteBorrow(id);
    }

    @Override
    public void addBook(String title, String desc, double price) {
        mapper.addBook(title, desc, price);
    }

    @Override
    public void deleteBook(int bid) {
        mapper.deleteBook(bid);
    }
}
