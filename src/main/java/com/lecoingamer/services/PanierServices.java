package com.lecoingamer.services;


import com.lecoingamer.model.Panier;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.User;

import java.util.List;

public interface PanierServices {

    Panier findById(int id);
    Panier findByReference(String reference);
    Panier findByUser(User user);
    void savePanier(Panier panier);
    void updatePanier(Panier panier);
    void deletePanier(int id);
    boolean isPanierExist(Panier panier);
    void addProduitToPanier(Panier panier, Produit produit);
    void deleteProduitFromPanier(Panier panier,Produit produit);
    boolean isEmpty(Panier panier);
    boolean contains(Produit produit,Panier panier);
    public void emptyPanier(Panier panier,List<Produit> produits);
}
