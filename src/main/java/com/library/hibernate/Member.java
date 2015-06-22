package com.library.hibernate;

import com.library.managedbeans.BookBorrowManagedBean;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ali on 2015-05-28.
 */
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_number")
    long membership_number;

    @Column(unique = true , nullable = false)
    String code_melli;

    @Column
    String name;

    @Column
    String family;

    @Column
    Date membershipdate;

    @Column
    String address;

    @OneToMany(mappedBy ="pk.member" , cascade = CascadeType.ALL)
    private Set<Book_Borrow> Borrowed_books = new HashSet<Book_Borrow>();

    /*@ManyToMany(targetEntity = Book.class , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable( name = "books_borrowed",
            joinColumns = {@JoinColumn(name = "membership_number")},
            inverseJoinColumns = {@JoinColumn(name = "record_number")}
    )*/

    public Member( String code_melli, String name, String family, Date membershipdate, String address) {
        this.code_melli = code_melli;
        this.name = name;
        this.family = family;
        this.membershipdate = membershipdate;
        this.address = address;

    }

    public Member() {
    }

    public long getMembership_number() {
        return membership_number;
    }

    public void setMembership_number(long membership_number) {
        this.membership_number = membership_number;
    }

    public String getCode_melli() {
        return code_melli;
    }

    public void setCode_melli(String code_melli) {
        this.code_melli = code_melli;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Date getMembershipdate() {
        return membershipdate;
    }

    public void setMembershipdate(Date membershipdate) {
        this.membershipdate = membershipdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Book_Borrow> getBorrowed_books() {
        return Borrowed_books;
    }

    public void setBorrowed_books(Set<Book_Borrow> borrowed_books) {
        Borrowed_books = borrowed_books;
    }
}
