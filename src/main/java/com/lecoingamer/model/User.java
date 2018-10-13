package com.lecoingamer.model;



import javax.persistence.*;
import java.util.List;

/**
 * @author Ali Belemlih
 * Entity user, with getter and setters. Represents the user.
 * There is a Many to Many relationship with entity @see Role
 * and there is a One to Many relationship with the entity UserLog
*/
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    private String name;
    private String email;
    private String password;
    private String usual_IP;
    private int logged;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @OneToMany
    private List<UserLog> userLog;
    boolean firstLog;
    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Panier panier;
    @OneToMany
    private List<Comande> comande;


    public List<Comande> getComande() {
        return comande;
    }

    public void setComande(List<Comande> comande) {
        this.comande = comande;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public User() {
        this.usual_IP="0:0:0:0:0:0:0:1";
        firstLog=true;
    }

    public List<UserLog> getUserLog() {
        return userLog;
    }

    public void setUserLog(List<UserLog> userLog) {
        this.userLog = userLog;
    }

    public boolean isFirstLog() {
        return firstLog;
    }

    public void setFirstLog(boolean firstLog) {
        this.firstLog = firstLog;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsual_IP() {
        return usual_IP;
    }

    public void setUsual_IP(String usual_IP) {
        this.usual_IP = usual_IP;
    }

    public int getLogged() {
        return logged;
    }

    public void setLogged(int logged) {
        this.logged = logged;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
