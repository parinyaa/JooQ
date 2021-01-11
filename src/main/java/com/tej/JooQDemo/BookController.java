package com.tej.JooQDemo;


import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/getBook")
    public ResponseEntity<List<Book>> getBooks(){

        return ResponseEntity.ok(this.bookService.getBooks());
    }

    @PostMapping("/saveBook")
    public ResponseEntity <Void> postBook(@RequestBody BookReq req){
        this.bookService.insertBook(req.getBook(),req.getAuthor());
        return ResponseEntity.ok().build();
    }
}
