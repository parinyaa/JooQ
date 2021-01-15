package com.tej.JooQDemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {

        this.bookRepository = bookRepository;
    }

    @GetMapping("/getBook")
    public ResponseEntity<List<Book>> getBooks(){

        return ResponseEntity.ok(this.bookService.getBooks());
    }

    @GetMapping("/getBooks")
    public ResponseEntity list() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        try{

            List<BookInnerJoin>listData =  bookRepository.findOrderByIdAsc();

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(listData);

            return (new ResponseEntity<String>(json, headers, HttpStatus.OK));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/saveBook")
    public ResponseEntity postBook(@RequestBody BookReq req){

        String response = "";
        try{
            response = this.bookService.insertBook(req.getBook(),req.getAuthor());
        } catch (Exception err) {
            err.printStackTrace();
            response = "500";
        }
        return ResponseEntity.ok(response);
    }
}
