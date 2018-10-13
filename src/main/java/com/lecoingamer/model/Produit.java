package com.lecoingamer.model;

import javax.persistence.*;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String reference;
    private int prix;
    private int promotion;
    //private String image;
    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="categorie_id")
    private Categorie categorie;
    @Enumerated(EnumType.STRING)
    private Disponibilite disponibilite;
    @ManyToOne
    private Panier panier;
    private String ficheTechnique;

    public Produit() {
        this.promotion=0;
    }

   /* public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }*/

    public Produit(int id, String name, String reference, int prix, int promotion, Disponibilite disponibilite) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.prix = prix;
        this.promotion = promotion;
        this.disponibilite = disponibilite;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Disponibilite getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(Disponibilite disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public String getFicheTechnique() {
        return ficheTechnique;
    }

    public void setFicheTechnique(String ficheTechnique) {
        this.ficheTechnique = ficheTechnique;
    }
}
