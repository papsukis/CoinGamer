package com.lecoingamer.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class SousCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "sousCategorie")
    List<Produit> produits;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    Categorie categorie;

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
