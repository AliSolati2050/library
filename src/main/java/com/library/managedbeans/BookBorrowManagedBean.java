package com.library.managedbeans;

import com.library.hibernate.Book;
import com.library.hibernate.HibernateManager;
import com.library.hibernate.HibernateUtil;
import com.library.hibernate.Member;
import com.sun.faces.spi.FaceletConfigResourceProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.management.Query;
import javax.servlet.http.HttpSession;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ali on 2015-06-06.
 */
@ManagedBean(name = "bookBorrowManagedBean")
@ViewScoped
public class BookBorrowManagedBean {
    Member member;
    Set<Book> books = new HashSet<Book>(0);
    String book_record_number;
    UIComponent bookRecordButton;
    Book selectedBook;
    static Book book;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getBook_record_number() {
        return book_record_number;
    }

    public void setBook_record_number(String book_record_number) {
        this.book_record_number = book_record_number;
    }

    public UIComponent getBookRecordButton() {
        return bookRecordButton;
    }

    public void setBookRecordButton(UIComponent bookRecordButton) {
        this.bookRecordButton = bookRecordButton;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    @PostConstruct
    public void init(){
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        member = (Member) httpSession.getAttribute("member");
    }

    public void bookRegeister(ActionEvent event){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    }

    public void borrow(){
        FacesContext context = FacesContext.getCurrentInstance();
         if(book == null){
             context.addMessage(bookRecordButton.getClientId(context) , new FacesMessage("dfdfdf"));
         }

    }
    public List<Book> showBooks(String query){
        List<Book> bookList = new ArrayList<Book>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
             book = (Book) session.get(Book.class , Long.parseLong(query));
             if(book != null){
                 bookList.add(book);
                 HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

                 session1.setAttribute("book" , book);
             }
            tx.commit();
        }catch (Exception ex){
            tx.rollback();
        }
        return bookList;
    }


}
