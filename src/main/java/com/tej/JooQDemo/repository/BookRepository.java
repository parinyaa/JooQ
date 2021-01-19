package com.tej.JooQDemo.repository;

import com.tej.JooQDemo.BookInnerJoin;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tej.JooQDemo.jooq.sample.model.tables.Author.AUTHOR;
import static com.tej.JooQDemo.jooq.sample.model.tables.Book.BOOK;

@Repository
public class BookRepository {

    private final DSLContext dslContext;

    public BookRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

//    public List<BookInnerJoin> findOrderByIdAsc() {

//            List<BookInnerJoin> listData = dslContext.select()
//                .from(BOOK)
//                .innerJoin(AUTHOR).on(BOOK.AUTHOR_ID.eq(AUTHOR.AUTHOR_ID))
//                .fetch()
//                .into(BookInnerJoin.class);

//        for(MyBook data : listData){
//
//            System.out.println(data.getFirstname());
//        }
//        System.out.println("--------------------------------");
//        System.out.println(listData);//
//        ObjectMapper mapper = new ObjectMapper();
    //Converting the Object to JSONString
//        try {
//            String jsonString = mapper.writeValueAsString(listData);
//            System.out.println(jsonString);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return listData;
//    }

    public List<BookInnerJoin> findOrderByIdAsc(Integer bookId) {

        List<BookInnerJoin> listData = dslContext.fetch("SELECT * FROM book b,author j where b.book_id = ? AND b.author_id = j.author_id ",bookId)
                .into(BookInnerJoin.class);
        return listData;
    }

}
