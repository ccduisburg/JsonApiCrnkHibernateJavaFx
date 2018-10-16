package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.Book;
import com.codenotfound.crnk.domain.model.Person;
import io.crnk.core.repository.RelationshipRepositoryBase;
import org.springframework.stereotype.Component;

@Component
public class BookToPersonRepositoryImpl
    extends RelationshipRepositoryBase<Book, Long, Person, Long> {

  public BookToPersonRepositoryImpl() {
    super(Book.class, Person.class);
  }
}
