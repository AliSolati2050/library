package com.library.converters;

import com.library.hibernate.Book;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 6/18/2015.
 */
@FacesConverter("com.library.converters.BookConverter")
public class BookConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        HttpSession session1 = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Book book = (Book) session1.getAttribute("book");

        return book;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Book book = (Book) o;
        return book.getName();
    }
}
