package com.lecoingamer.model;

import javax.persistence.*;

@Entity
public class ImageModel {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(length = 100000,columnDefinition = "TEXT")
    private String img;
    @OneToOne(fetch=FetchType.LAZY)
    Produit produit;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public ImageModel() {
    }

    public ImageModel(String name, String img) {
        this.name = name;
        this.img = img;
    }

    public ImageModel(String name, String type, String img) {
        this.name = name;
        this.type = type;
        this.img = img;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return img;
    }

    public void setPic(String img) {
        this.img = img;
    }
}
