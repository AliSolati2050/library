package com.library.hibernate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ali on 2015-05-28.
 */
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_number")
    long id;

    @Column(name = "book_name")
    String name;

    @Column(name = "author")
    String author;

    @Column(name = "record_date")
    Date record_date;

    @Column(name = "picture_address")
    String picture_address;

    @OneToMany(mappedBy = "pk.book" , cascade = CascadeType.ALL)
    Set<Book_Borrow> Borrowed_books = new HashSet<Book_Borrow>();

    public Book(){}

    public Book(String name, String author, Date record_date , String picture_address) {
        this.name = name;
        this.author = author;
        this.record_date = record_date;
        this.picture_address = picture_address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public String getPicture_address() {
        return picture_address;
    }

    public void setPicture_address(String picture_address) {
        this.picture_address = picture_address;
    }

    public Set<Book_Borrow> getBorrowed_books() {
        return Borrowed_books;
    }

    public void setBorrowed_books(Set<Book_Borrow> borrowed_books) {
        Borrowed_books = borrowed_books;
    }
}
