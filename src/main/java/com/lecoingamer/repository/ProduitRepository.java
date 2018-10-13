package com.lecoingamer.repository;


import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Panier;
import com.lecoingamer.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Integer> {

    Produit findByName(String name);
    List<Produit> findProduitByPanier(Panier panier);
    List<Produit> findProduitByCategorie(Categorie categorie);
}
