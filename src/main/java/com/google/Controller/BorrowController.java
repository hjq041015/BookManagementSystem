package com.google.Controller;

import com.google.server.BookServer;
import com.google.server.UserServer;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BorrowController {
    @Resource
    BookServer bookServer;
    @Resource
    UserServer userServer;
    @GetMapping({"/","/borrow"})
    public String borrow(Model model) {
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       model.addAttribute("nickname",user.getUsername());
       model.addAttribute("borrow_list",bookServer.getBorrowList());
       model.addAttribute("book_count",bookServer.getBookList().size());
       model.addAttribute("student_count",userServer.getStudentList().size());
       return "index";
    }

    @GetMapping("/add-borrow")
    public String addBorrow(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname",user.getUsername());
        model.addAttribute("book_list",bookServer.getActiveBookList());
        model.addAttribute("student_list",userServer.getStudentList());
        return "add-borrow";
    }

    @PostMapping("/add-borrow")
    public String addBorrow(int student , int book){
        bookServer.addBorrow(student,book);
        return "redirect:/borrow";
    }

    @GetMapping("/return-book")
    public String returnBook(int id) {
        bookServer.deleteBorrow(id);
        return "redirect:/borrow";
    }
}
