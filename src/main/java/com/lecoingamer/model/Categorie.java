package com.lecoingamer.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "categorie")
    private List<SousCategorie> sousCategories;

    public List<SousCategorie> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(List<SousCategorie> sousCategories) {
        this.sousCategories = sousCategories;
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

   /* public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }*/
}
