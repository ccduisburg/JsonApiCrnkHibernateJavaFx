package com.codenotfound.crnk.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="library")
@Getter
@Setter
@JsonApiResource(type="libraries")
public class Library implements Serializable {
    @Id
    @JsonApiId
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToOne
    @JsonApiRelation(opposite = "library")
    private Address address;

    @OneToMany(mappedBy = "library", fetch = FetchType.EAGER)
    @JsonIgnore
    @JsonApiRelation(opposite = "library")
    private List<Book> books;


    public String toString() {
        return String.format("Library(%d,%s)", id, name);
    }
}
