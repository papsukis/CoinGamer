package com.lecoingamer.services;



import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Produit;

import java.util.List;

public interface CategorieServices {

    Categorie findById(int id);
    Categorie findByName(String name);
    List<Produit> findByCategorie(Categorie categorie);
    void saveCategorie(Categorie categorie);
    void updateCategorie(Categorie categorie);
    void deleteCategorie(int id);
    List<Categorie> findAllCategorie();
    boolean isCategorieExist(Categorie categorie);
    void addProduitToCategorie(Categorie categorie, Produit produit);
}
