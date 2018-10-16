package com.codenotfound.crnk.domain.repository;


import com.codenotfound.crnk.domain.model.Book;
import com.codenotfound.crnk.domain.model.BookCategory;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class BookToBookCategoryRepositoryImpl extends RelationshipRepositoryBase<Book, Long, BookCategory, Long> {
    protected BookToBookCategoryRepositoryImpl(Class<Book> sourceResourceClass, Class<BookCategory> targetResourceClass) {
        super(sourceResourceClass, targetResourceClass);
    }
    public BookToBookCategoryRepositoryImpl() {
        super(Book.class, BookCategory.class);
    }

}
