package com.google.Controller;

import com.google.server.BookServer;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class BookController {
    @Resource
    BookServer server;
    @GetMapping("/books")
    String books(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname",user.getUsername());
        model.addAttribute("book_list",server.getBookList().keySet());
        model.addAttribute("book_list_status",new ArrayList<>(server.getBookList().values()));
       return "books";
    }

    @GetMapping("/add-book")
    String addBook() {
        return "add-book";
    }

    @PostMapping("/add-book")
    String addBook(String title,String desc,double price) {
        server.addBook(title, desc, price);
        return "redirect:/books";
    }

    @GetMapping("/delete-book")
    String deleteBook(int bid) {
        server.deleteBook(bid);
        return "redirect:/books";
    }
}
