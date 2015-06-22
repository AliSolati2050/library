package com.library.managedbeans;

import com.library.hibernate.Book;
import com.library.hibernate.HibernateUtil;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.NativeUploadedFile;
import org.primefaces.model.UploadedFile;
import sun.misc.IOUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Ali on 2015-05-29.
 */
@ManagedBean(name = "bookManagedBean")
@ViewScoped
public class BookManagedBean implements Serializable{
    String name;

    String author;

    FileUpload uploadedFile;

    static String picturePath = "";

    public BookManagedBean(){}

    public BookManagedBean(String name, String author , String path ) {
        this.name = name;
        this.author = author;
    }

    UIComponent bookRegisterButton;

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

    public FileUpload getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(FileUpload uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public UIComponent getBookRegisterButton() {
        return bookRegisterButton;
    }

    public void setBookRegisterButton(UIComponent bookRegisterButton) {
        this.bookRegisterButton = bookRegisterButton;
    }

    public void bookRegister(){
        FacesContext context = FacesContext.getCurrentInstance();
        Book book = new Book(name , author , Calendar.getInstance().getTime() , picturePath);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try{
            session.persist(book);
            tx.commit();
            reset();
            context.addMessage(bookRegisterButton.getClientId(context), new FacesMessage("کتاب با موفقیت ثبت شد"));
        }catch (Exception ex){
            tx.rollback();
            context.addMessage(bookRegisterButton.getClientId(context), new FacesMessage("امکان ثبت کتاب وجود ندارد"));
        }
    }

    public void ali(ActionEvent event){
        bookRegisterButton.setRendered(false);
        System.out.println("Ali");
    }
    public void fileUploadManager(FileUploadEvent event) throws Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        String baseAddress = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/recources/images");
        String generatedAddress = UUID.randomUUID().toString()+ "." + FilenameUtils.getExtension(event.getFile().getFileName());
        picturePath = baseAddress + "\\" + generatedAddress;

        byte[] image = org.apache.commons.io.IOUtils.toByteArray(event.getFile().getInputstream());

        BufferedImage bi = ImageIO.read(new ByteArrayInputStream(image));
        int width = bi.getWidth();
        int height = bi.getHeight();
        if(width <500 && height < 450 && width >150 && height>100){
            event.getFile().write(picturePath);
            picturePath ="recources/images/" + generatedAddress;
            context.addMessage(bookRegisterButton.getClientId(context) , new FacesMessage("تصویر با موفقیت آپلود شد"));


        }

        else {
            context.addMessage( bookRegisterButton.getClientId(context)  , new FacesMessage("ابعاد تصویر نباید از حد مجاز کمتر یا بیشتر باشد"));
        }


        /*InputStream inputStream = event.getFile().getInputstream();
        OutputStream outputStream = new FileOutputStream( new File(path + uuid ));event.getFile().
        int read = 0 ;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes))!= -1){
              outputStream.write(bytes , 0 , read);
        }
         inputStream.close();
        outputStream.flush();
        outputStream.close();*/
    }
    void reset(){
        setName(null);
        setAuthor(null);
    }

}
