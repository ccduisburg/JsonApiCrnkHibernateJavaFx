package com.codenotfound.crnk.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="address")
@Getter
@Setter
@JsonApiResource(type="adresses")
public class Address implements Serializable {
    @Id
    @GeneratedValue
    @JsonApiId
    private long id;

    @Column(nullable = false)
    private String location;

    @Column
    private Integer hnummer;

    @Column
    private String PLZ;

    @Column
    private String stadt;


    @OneToOne(mappedBy = "address")
    @JsonIgnore
    @JsonApiRelation(opposite = "address")
    private Library library;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JsonApiRelation(opposite = "address")
    private Person person;

    public Address(String location, Integer hnummer, String PLZ, String stadt) {
        this.location = location;
        this.hnummer = hnummer;
        this.PLZ = PLZ;
        this.stadt = stadt;
    }

    public Address() {
    }

    public String toString() {
        return String.format("Address(%d,%s,%d,%s,%s)", id, location, hnummer, PLZ, stadt);
    }
}
