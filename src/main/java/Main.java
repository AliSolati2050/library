import com.library.hibernate.Book;
import com.library.hibernate.Book_Borrow;
import com.library.hibernate.HibernateUtil;
import com.library.hibernate.Member;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ali on 2015-05-27.
 */
public class Main {


    public static void main(final String[] args) throws Exception {

        Member member = new Member("15" , "fg" , "fg" , Calendar.getInstance().getTime(), "fgffg");
        Set<Book_Borrow> book_borrows = new HashSet<Book_Borrow>();
        Book_Borrow book_borrow = new Book_Borrow();
        Book book = new Book();
        book.setAuthor("solati");
        book.setName("computer");
        book.setRecord_date(Calendar.getInstance().getTime());
        book_borrow.setBook(book);
        book_borrow.setMember(member);
        book_borrow.setRecord_date(Calendar.getInstance().getTime());
        book_borrows.add(book_borrow);
        // member.setBorrowed_books(book_borrows);
        book.setBorrowed_books(book_borrows);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.persist(member);
            session.persist(book);
            session.persist(book_borrow);
            tx.commit();
        }catch (Exception ex) {
            tx.rollback();
        }
    }
}
