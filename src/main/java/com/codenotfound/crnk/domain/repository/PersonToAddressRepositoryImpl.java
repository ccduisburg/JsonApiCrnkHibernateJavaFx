package com.codenotfound.crnk.domain.repository;

import com.codenotfound.crnk.domain.model.Address;
import org.springframework.stereotype.Component;

import com.codenotfound.crnk.domain.model.Article;
import com.codenotfound.crnk.domain.model.Person;

import io.crnk.core.repository.RelationshipRepositoryBase;

@Component
public class PersonToAddressRepositoryImpl
    extends RelationshipRepositoryBase<Person, Long, Address, Long> {

  public PersonToAddressRepositoryImpl() {
    super(Person.class, Address.class);
  }
}
