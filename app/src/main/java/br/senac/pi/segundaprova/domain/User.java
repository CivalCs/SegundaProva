package br.senac.pi.segundaprova.domain;

/**
 * Created by Aluno on 20/11/2015.
 */
public class User {
    private long id;
    private String usuario;
    private String pass;

    public User(){}

    public User(long id, String usuario, String pass){
        this.id = id;
        this.usuario = usuario;
        this.pass = pass;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

