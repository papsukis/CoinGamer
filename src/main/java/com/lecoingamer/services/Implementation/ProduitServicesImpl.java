package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Produit;
import com.lecoingamer.repository.ProduitRepository;
import com.lecoingamer.services.ProduitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProduitServicesImpl implements ProduitServices {

    @Autowired
    ProduitRepository produitRepository;

    @Override
    public Produit findById(int id) {
        List<Produit> produits = produitRepository.findAll();
        for(Produit produit : produits){
            if(produit.getId() == id){
                return produit;
            }
        }
        return null;
    }

    @Override
    public Produit findByName(String name) {
        return produitRepository.findByName(name);
    }

    @Override
    public void saveProduit(Produit produit) {
        produitRepository.save(produit);

    }

    @Override
    public void updateProduit(Produit produit) {
        Produit produitToUpdate=produit;

        produitToUpdate.setId(produit.getId());

        saveProduit(produitToUpdate);
    }

    @Override
    public void deleteProduit(int id) {
        produitRepository.delete(findById(id));
    }

    @Override
    public List<Produit> findAllProduit() {
        return produitRepository.findAll();
    }

    @Override
    public boolean isProduitExist(Produit produit) {
        return (findById(produit.getId())!=null);
    }
}
