package com.test.demo.controller;

import com.test.demo.pojo.Book;
import com.test.demo.pojo.Search;
import com.test.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class LibraryController {
    @Autowired
    BookService bookService;


    @GetMapping("/api/books")
    @ResponseBody
    public List<Book> list() throws Exception {
        return bookService.list();
    }
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception {
        bookService.addOrUpdate(book);
        return book;
    }
    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception {
        bookService.deleteById(book.getId());
    }
    @GetMapping("/api/categories/{cid}/books")
    @ResponseBody
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return bookService.listByCategory(cid);
        } else {
            return list();
        }
    }
    @PostMapping("/api/search")
    public List<Book> searchResult(@RequestBody Search s) throws Exception {
        // 关键字为空时查询所有书籍
        if ("".equals(s.getKeywords())) {
            return bookService.list();
        } else {
            return bookService.Search(s.getKeywords());
        }
    }
}
