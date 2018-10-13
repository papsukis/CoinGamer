package com.lecoingamer.services;



import com.lecoingamer.model.Produit;

import java.util.List;

public interface ProduitServices {

    Produit findById(int id);
    Produit findByName(String name);
    void saveProduit(Produit produit);
    void updateProduit(Produit produit);
    void deleteProduit(int id);
    List<Produit> findAllProduit();
    boolean isProduitExist(Produit produit);


}
