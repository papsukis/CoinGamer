package com.lecoingamer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int reference;
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Produit> produit;
    @OneToOne(fetch=FetchType.LAZY)
    private Comande comande;
    @OneToOne(mappedBy = "panier")
    private User user;
    @ElementCollection
    @JoinTable(name="Produit_Quantity", joinColumns=@JoinColumn(name="Produit_ID"))
    @MapKeyColumn (name="Produit_Quantity")
    @Column(name="Quantity")
    private Map<Produit,Integer> Quantity;

    public Panier() {
        this.produit=new ArrayList<>();
    }

    public Panier(User user) {
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public List<Produit> getProduit() {
        return produit;
    }

    public void setProduit(List<Produit> produit) {
        this.produit = produit;
    }


    public Map<Produit, Integer> getQuantity() {
        return Quantity;
    }

    public void setQuantity(Map<Produit, Integer> quantity) {
        Quantity = quantity;
    }

    public Comande getComande() {
        return comande;
    }

    public void setComande(Comande comande) {
        this.comande = comande;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
