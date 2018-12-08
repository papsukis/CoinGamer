package com.lecoingamer.model;

import javax.persistence.*;

@Entity

public class FicheTechnique {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="fiche_technique_id")
    private int id;
    private String description;
    private String cle;
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
