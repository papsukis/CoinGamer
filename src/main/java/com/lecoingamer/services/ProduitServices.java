package com.lecoingamer.services;



import com.lecoingamer.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProduitServices {

    Produit findById(int id);
    Page<Produit> findByName(String name,int page,int limit);
    void saveProduit(Produit produit);
    void updateProduit(Produit produit);
    void deleteProduit(int id);
    List<Produit> findAllProduit();
    boolean isProduitExist(Produit produit);
    public List<Produit> contains(String contains);



    public Page<Produit> containsSearchTerm(String searchTerm, int page, int limit);
    Page<Produit> findAllProduit(int page,int q);

    Page<Produit> findBySearchTerm(String nameSearch,String referenceSearch,int minSearch,int maxSearch, int page, int i);
}
