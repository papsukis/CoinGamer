package com.lecoingamer.services.Implementation;

import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Comande;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.SousCategorie;
import com.lecoingamer.repository.ProduitRepository;
import com.lecoingamer.repository.SousCategorieRepository;
import com.lecoingamer.services.ProduitServices;
import com.lecoingamer.services.SousCategorieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SousCategorieServicesImpl implements SousCategorieServices{

    @Autowired
    SousCategorieRepository sousCategorieRepository;
    @Autowired
    ProduitRepository produitRepository;

    @Override
    public SousCategorie findById(int id) {
        return sousCategorieRepository.findById(id).get();
    }

    @Override
    public SousCategorie findByName(String name) {
        return sousCategorieRepository.findByName(name);
    }

    @Override
    public List<Produit> findBySousCategorie(SousCategorie sousCategorie) {
        return sousCategorie.getProduits();
    }

    @Override
    public void saveSousCategorie(SousCategorie SousCategorie) {
            sousCategorieRepository.save(SousCategorie);
    }



    @Override
    public void deleteSousCategorie(int id) {

        SousCategorie sc=findById(id);

        List<Produit> p=sc.getProduits();
        sc.setProduits(null);
        for(Produit pr:p)
        {
            pr.setCategorie(null);
            produitRepository.save(pr);
        }

        sousCategorieRepository.save(sc);
        sousCategorieRepository.delete(sc);

    }

    @Override
    public List<SousCategorie> findAllSousCategorie() {
        return sousCategorieRepository.findAll();
    }

    @Override
    public boolean isSousCategorieExist(SousCategorie SousCategorie) {
        return (findById(SousCategorie.getId())!= null);
    }

    @Override
    public void addProduitToSousCategorie(SousCategorie cat,Produit produit) {

       produit.setCategorie(cat);
       produitRepository.save(produit);
    }

    @Override
    public void addProduitToSousCategorie(SousCategorie cat, List<Produit> produit) {

        List<Produit>produits=findBySousCategorie(cat);

       for(Produit p: produitRepository.findAll())
        {
            if(produits.contains(p))
            {
                p.setCategorie(cat);
                produitRepository.save(p);
            }

        }

        for(Produit p: produit)
        {
            if(!produits.contains(p)){
                produits.add(p);
            }
        }

        cat.setProduits(produits);
        saveSousCategorie(cat);
    }

    @Override
    public Categorie findCategorie(SousCategorie sc) {
        return sc.getCategorie();
    }

    @Override
    public List<Produit> findProduits(int id)
    {
        return produitRepository.findProduitBySousCategorie(findById(id));
    }
}
