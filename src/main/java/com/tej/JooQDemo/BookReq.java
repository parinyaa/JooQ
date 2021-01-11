package com.tej.JooQDemo;

import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Author;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Book;
import lombok.Data;

@Data
public class BookReq {
    private Book book;
    private Author author;
}
