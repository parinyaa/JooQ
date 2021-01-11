package com.tej.JooQDemo;

import com.tej.JooQDemo.jooq.sample.model.Tables;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Book;
import com.tej.JooQDemo.jooq.sample.model.tables.pojos.Author;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class BookService {

    @Autowired
    DSLContext context;

    public List<Book> getBooks() {
        return context
                .selectFrom(Tables.BOOK)
                .fetchInto(Book.class);
    }

    public void insertBook(Book book,Author author) {
        System.out.println("gggggggggggggggggggggggggggggggggg");
        log.info("RequestInsertBook: {},{} ",book,author);
//        try {
            context
                    .insertInto(Tables.AUTHOR, Tables.AUTHOR.FIRSTNAME, Tables.AUTHOR.LASTNAME)
                    .values(author.getFirstname(), author.getLastname())
                    .execute();
            context
                    .insertInto(Tables.BOOK, Tables.BOOK.TITLE, Tables.BOOK.AUTHOR_ID)
                    .values(book.getTitle(),book.getAuthorId())
                    .execute();

//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
    }
}
