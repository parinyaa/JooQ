package com.tej.JooQDemo;

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

    public List<BookInnerJoin> findOrderByIdAsc() {
//        List<Result<Record>> values = dslContext.selectDistinct()
//                .from(BOOK)
//                .collect(Collectors.toList());

        List<BookInnerJoin> listData = dslContext.select()
                .from(BOOK)
                .innerJoin(AUTHOR).on(BOOK.AUTHOR_ID.eq(AUTHOR.AUTHOR_ID))
                .fetch()
                .into(BookInnerJoin.class);

//        for(MyBook data : listData){
//
//            System.out.println(data.getFirstname());
//        }
        System.out.println("--------------------------------");
//        System.out.println(listData);//
//        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
//        try {
//            String jsonString = mapper.writeValueAsString(listData);
//            System.out.println(jsonString);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return listData;
    }
}
