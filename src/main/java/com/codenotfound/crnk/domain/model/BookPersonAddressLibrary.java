package com.codenotfound.crnk.domain.model;


import lombok.Data;

@Data
public class BookPersonAddressLibrary {
    private String personName;
    private String personVorname;
    private String personBeruf;
    private String personStrasse;
    private int personHausnummer;
    private String personPlz;
    private String personStadt;
    private String bookTitle;
    private String bookDescription;
    private String libraryName;

    public BookPersonAddressLibrary(String personName, String personVorname, String personBeruf, String personStrasse, int personHausnummer, String personPlz, String personStadt, String bookTitle, String bookDescription, String libraryName) {
        this.personName = personName;
        this.personVorname = personVorname;
        this.personBeruf = personBeruf;
        this.personStrasse = personStrasse;
        this.personHausnummer = personHausnummer;
        this.personPlz = personPlz;
        this.personStadt = personStadt;
        this.bookTitle = bookTitle;
        this.bookDescription = bookDescription;
        this.libraryName = libraryName;
    }

    public BookPersonAddressLibrary() {
    }
}
