package com.lecoingamer.services;

import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.SousCategorie;
import com.lecoingamer.model.Produit;

import java.util.List;

public interface SousCategorieServices {

    SousCategorie findById(int id);
    SousCategorie findByName(String name);
    List<Produit> findBySousCategorie(SousCategorie SousCategorie);
    void saveSousCategorie(SousCategorie SousCategorie);
    void deleteSousCategorie(int id);
    List<SousCategorie> findAllSousCategorie();
    boolean isSousCategorieExist(SousCategorie SousCategorie);
    void addProduitToSousCategorie(SousCategorie SousCategorie, Produit produit);

    void addProduitToSousCategorie(SousCategorie cat, List<Produit> produit);

    Categorie findCategorie(SousCategorie sc);

    List<Produit> findProduits(int id);
}
