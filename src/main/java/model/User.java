package model;

import java.io.Serializable;



public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;       // Логин
    private String password;    // Пароль


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public String login() {
        return login;
    }
    public String password() {
        return password;
    }


    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Object getLogin() {
        return login;
    }

    public Object getPassword() {
        return password;
    }
}