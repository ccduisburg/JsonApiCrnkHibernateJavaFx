package com.codenotfound.crnk.domain.repository;


import com.codenotfound.crnk.domain.model.Book;
import com.codenotfound.crnk.domain.model.Library;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class BookToLibraryRepositoryImpl extends RelationshipRepositoryBase<Book, Long, Library, Long> {
    protected BookToLibraryRepositoryImpl(Class<Book> sourceResourceClass, Class<Library> targetResourceClass) {
        super(sourceResourceClass, targetResourceClass);
    }
    public BookToLibraryRepositoryImpl() {
        super(Book.class, Library.class);
    }

}
