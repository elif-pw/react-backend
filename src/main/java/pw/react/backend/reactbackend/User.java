package pw.react.backend.reactbackend;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

// id, login, first name, last name, date of birth, an indicator saying if a user is active or not.
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="login")
    private String login;

    @Column(name="name")
    private String name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="is_active")
    private Boolean is_active;

    public User() {
    }

    public User(String login, String name, String last_name, LocalDate dob, Boolean is_active) {
        this.login = login;
        this.name = name;
        this.last_name = last_name;
        this.dob = dob;
        this.is_active = is_active;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dob=" + dob +
                ", is_active=" + is_active +
                '}';
    }
}
