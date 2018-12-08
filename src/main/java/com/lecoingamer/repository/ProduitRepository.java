package com.lecoingamer.repository;


import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Panier;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.SousCategorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Integer> {

    List<Produit> findByNameContaining(String name);
    Page<Produit> findByNameContaining(String name, Pageable pageable);
    Page<Produit> findByNameContainingAndReferenceContainingAndPrixGreaterThanEqualAndPrixLessThanEqual(String name,String reference,int min,int max, Pageable pageable);
    Page<Produit> findByNameContainingAndReferenceContaining(String name,String reference,Pageable pageable);
    List<Produit> findProduitByPanier(Panier panier);
    List<Produit> findProduitBySousCategorie(SousCategorie sousCategorie);
}
