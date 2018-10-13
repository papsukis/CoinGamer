package com.lecoingamer.model;


import javax.persistence.*;

@Entity
public class Comande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int total;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @ManyToOne
    private User user;
    @OneToOne(fetch=FetchType.LAZY)
    private  Panier panier;

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
