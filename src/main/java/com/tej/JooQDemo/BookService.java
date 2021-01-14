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

    public String insertBook(Book book,Author author) throws RuntimeException {
        System.out.println("gggggggggggggggggggggggggggggggggg");
        log.info("RequestInsertBook: {},{} ",book,author);
        try {

            context.transaction(configuration -> {
                context
                        .insertInto(Tables.AUTHOR, Tables.AUTHOR.FIRSTNAME, Tables.AUTHOR.LASTNAME)
                        .values(author.getFirstname(), author.getLastname())
                        .execute();
                System.out.println("insert author success");

                context
                        .insertInto(Tables.BOOK, Tables.BOOK.TITLE, Tables.BOOK.AUTHOR_ID)
                        .values(book.getTitle(),book.getAuthorId())
                        .execute();
                System.out.println("insert book success");
            });

//            context
//                    .insertInto(Tables.AUTHOR, Tables.AUTHOR.FIRSTNAME, Tables.AUTHOR.LASTNAME)
//                    .values(author.getFirstname(), author.getLastname())
//                    .execute();
//            boolean a = true;         }
//
//            context
//                    .insertInto(Tables.BOOK, Tables.BOOK.TITLE, Tables.BOOK.AUTHOR_ID)
//                    .values(book.getTitle(),book.getAuthorId())
//                    .execute();

            return "200";

        } catch (RuntimeException e) {
            System.err.println("Rollback Success");
            throw new RuntimeException(e.getMessage());
        }
    }
}
