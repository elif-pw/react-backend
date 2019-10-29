package pw.react.backend.reactbackend;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

// id, login, first name, last name, date of birth, an indicator saying if a user is active or not.
@Entity
@Table(name = "user", schema = "my_schema")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String last_name;


    @Temporal(TemporalType.DATE)
    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "is_active", nullable = false)
    private Boolean is_active;

    public User() {
    }

    public User(String login, String name, String last_name, Date dob, Boolean is_active) {
        this.login = login;
        this.name = name;
        this.last_name = last_name;
        this.dob = dob;
        this.is_active = is_active;
    }

    public Long getId() {
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

    public Date getDob() {
        return dob;
    }

    public Boolean getIs_active() {
        return is_active;
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
