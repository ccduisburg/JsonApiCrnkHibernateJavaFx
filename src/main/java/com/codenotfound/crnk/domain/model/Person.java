package com.codenotfound.crnk.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="person")
@Getter
@Setter
@JsonApiResource(type = "person")
public class Person implements Serializable {
@Id
@JsonApiId
@GeneratedValue
  private Long id;
  @Column
  private String name;
  @Column
  private String vorname;
  @Column
  private String beruf;

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL,
  fetch = FetchType.LAZY, optional = false)
  @JsonApiRelation(opposite = "person")
  private Address address;

  @ManyToMany(mappedBy = "people", cascade = CascadeType.ALL,
          fetch = FetchType.LAZY)
  @JsonApiRelation(opposite = "people")
  @JsonIgnore
  private List<Book> books;

  public Person() {
      }

  public Person(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Person(String name, String vorname, String beruf) {
    this.name = name;
    this.vorname = vorname;
    this.beruf = beruf;
  }

  @Override
  public String toString() {
    return "person[id=" + id + ", name=" + name + "]"+ vorname+", Vorname= [" +beruf+"]";
  }
}
