package com.lecoingamer.repository;

import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.SousCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SousCategorieRepository extends JpaRepository<SousCategorie,Integer> {

    SousCategorie findByName(String name);
    SousCategorie findByProduits(Produit produit);
}
