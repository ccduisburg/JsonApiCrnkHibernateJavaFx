package com.codenotfound.crnk.client;

import com.codenotfound.crnk.domain.model.Address;
import com.codenotfound.crnk.domain.model.Book;
import com.codenotfound.crnk.domain.model.BookCategory;
import com.codenotfound.crnk.domain.model.Library;

import java.util.List;

public class Tester {
    public static void main(String[] args) {
        BlogClient client = new BlogClient();
        client.init();
        System.out.println("<<Addresses>>");
        List<Address> addresses = client.findAllAddresses();
        addresses.forEach(System.out::println);
        System.out.println("\n<<Books>>");
        List<Book> books = client.findAllBooks();
        books.forEach(System.out::println);

        System.out.println("\n<<BookCategories>>");
        List<BookCategory> bookCategories =  client.findAllBookCategories();
        bookCategories.forEach(System.out::println);

        System.out.println("\n<<Libraries>>");
        List<Library> libraries= client.findAllLibraries();
        libraries.forEach(System.out::println);

        System.out.println("\n<<Libraries address through relationships>>");
        libraries.forEach(l-> {
            System.out.println(client.findLibraryAddress(l.getId()));
        });
    }
}
