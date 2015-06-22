package com.library.hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Administrator on 6/12/2015.
 */
@Embeddable
public class Book_Borrow_PK implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    Book book;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
