package com.lecoingamer.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="produit_id")
    private int id;
    private String name;
    private String reference;
    private int prix;
    private int promotion;
    String description;
    @Lob
    @Column(length = 100000,columnDefinition = "TEXT")
    private String img;
    //private List<String> img;
    @ManyToOne
    @JoinColumn(name="souscategorie_id",nullable = true)
    private SousCategorie sousCategorie;
    @Enumerated(EnumType.STRING)
    private Disponibilite disponibilite;
    @ManyToOne
    private Panier panier;
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private List<FicheTechnique> ficheTechnique;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<FicheTechnique> getFicheTechnique() {
        return ficheTechnique;
    }

    public void setFicheTechnique(List<FicheTechnique> ficheTechnique) {
        this.ficheTechnique = ficheTechnique;
    }

    public Produit() {
        this.promotion=0;
    }

    public String getImage() {
        return img;
    }

    public void setImage(String img) {
        this.img = img;
    }

    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public Produit( String name, String reference, int prix, int promotion, Disponibilite disponibilite) {

        this.name = name;
        this.reference = reference;
        this.prix = prix;
        this.promotion = promotion;
        this.disponibilite = disponibilite;
    }

    public Produit(String name, String reference, int prix, int promotion, String img, Disponibilite disponibilite) {
        this.name = name;
        this.reference = reference;
        this.prix = prix;
        this.promotion = promotion;
        this.img = img;
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

    public SousCategorie getCategorie() {
        return sousCategorie;
    }

    public void setCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
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


}
