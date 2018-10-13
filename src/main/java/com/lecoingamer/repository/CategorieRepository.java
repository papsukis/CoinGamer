package com.lecoingamer.repository;


import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

    Categorie findByName(String name);
    Categorie findByProduits(Produit produit);


}
