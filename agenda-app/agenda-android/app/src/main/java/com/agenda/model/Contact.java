package com.agenda.model;


/**
 * Created by Carlos on 30/08/2014.
 */
public class Contact {

    public final static String TABLE_NAME = "contact";
    public final static String FLD_ID = "_id";
    public final static String FLD_NAME = "name";
    public final static String FLD_PHONE = "phone";
    public final static String FLD_EMAIL = "email";
    public final static String FLD_URL_PHOTO = "url_photo";

    public final static String[] columns = {FLD_ID, FLD_NAME, FLD_PHONE, FLD_EMAIL, FLD_URL_PHOTO};

    private String nome;
    private String telefone;
    private String email;
    private String photo;
    private String id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
