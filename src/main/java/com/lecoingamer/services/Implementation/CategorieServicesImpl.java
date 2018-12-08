package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.SousCategorie;
import com.lecoingamer.repository.CategorieRepository;
import com.lecoingamer.repository.ProduitRepository;
import com.lecoingamer.repository.SousCategorieRepository;
import com.lecoingamer.services.CategorieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CategorieServicesImpl implements CategorieServices {

    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    SousCategorieRepository sousCategorieRepository;
    @Autowired
    ProduitRepository produitRepository;


    @Override
    public Categorie findById(int id) {
        return categorieRepository.findById(id).get();
    }

    @Override
    public Categorie findByName(String name) {
        return categorieRepository.findByName(name);
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

        Categorie sc=findById(id);

        List<SousCategorie> p=sc.getSousCategories();
        sc.setSousCategories(null);
        for(SousCategorie pr:p)
        {
            pr.setCategorie(null);
            sousCategorieRepository.save(pr);
        }

        categorieRepository.save(sc);
        categorieRepository.delete(sc);

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
    public void addSousCategorieToCategorie(Categorie cat, SousCategorie sc) {

        sc.setCategorie(cat);
        sousCategorieRepository.save(sc);

    }
}
