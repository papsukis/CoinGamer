package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Produit;
import com.lecoingamer.repository.CategorieRepository;
import com.lecoingamer.repository.ProduitRepository;
import com.lecoingamer.services.CategorieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategorieServicesImpl implements CategorieServices {

    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    ProduitRepository produitRepository;


    @Override
    public Categorie findById(int id) {
        List<Categorie> categories = categorieRepository.findAll();
        for(Categorie categorie : categories){
            if(categorie.getId() == id){
                return categorie;
            }
        }
        return null;
    }

    @Override
    public Categorie findByName(String name) {
        return categorieRepository.findByName(name);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findProduitByCategorie(categorie);
    }

    @Override
    public void saveCategorie(Categorie categorie) {

        categorieRepository.save(categorie);
    }

    @Override
    public void updateCategorie(Categorie categorie) {
        Categorie categorieToUpdate=categorie;

        categorieToUpdate.setId(categorie.getId());

        saveCategorie(categorieToUpdate);

    }

    @Override
    public void deleteCategorie(int id) {
        categorieRepository.delete(findById(id));
    }

    @Override
    public List<Categorie> findAllCategorie() {
        return categorieRepository.findAll();
    }

    @Override
    public boolean isCategorieExist(Categorie categorie) {
        return findById(categorie.getId())!=null;
    }

    @Override
    public void addProduitToCategorie(Categorie categorie,Produit produit) {

        List<Produit>produits=findByCategorie(categorie);

        produits.add(produit);

        categorie.setProduits(produits);

        updateCategorie(categorie);
    }
}
