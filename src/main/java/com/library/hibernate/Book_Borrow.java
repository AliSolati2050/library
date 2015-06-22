package com.library.hibernate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 6/8/2015.
 */
@Entity
@Table(name = "borrowed_books")
public class Book_Borrow {
    public Book_Borrow(){}
    @EmbeddedId
    Book_Borrow_PK pk = new Book_Borrow_PK();


    @Column
    Date record_date;

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public Book_Borrow_PK getPk() {
        return pk;
    }

    public void setPk(Book_Borrow_PK pk) {
        this.pk = pk;
    }

    @Transient
    public Member getMember(){
        return getPk().getMember();
    }
     public void setMember(Member member){
         getPk().setMember(member);
     }

    @Transient
    public Book getBook(){
        return getPk().getBook();
    }

    public void setBook(Book book){
        getPk().setBook(book);
    }
}
