package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Panier;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.User;
import com.lecoingamer.repository.PanierRepository;
import com.lecoingamer.services.PanierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PanierServicesImpl implements PanierServices {

    @Autowired
    PanierRepository panierRepository;


    @Override
    public Panier findById(int id) {
        List<Panier> paniers=panierRepository.findAll();
        for(Panier panier : paniers){
            if(panier.getId()== id)
                return panier;
        }

        return null;
    }

    @Override
    public Panier findByUser(User user) {
        return panierRepository.findPanierByUser(user);
    }

    @Override
    public Panier findByReference(String reference) {
        return panierRepository.findByReference(reference);
    }

    @Override
    public void savePanier(Panier panier) {
        panierRepository.save(panier);
    }

    @Override
    public void updatePanier(Panier panier) {
        Panier panierToUpdate=panier;

        panierToUpdate.setId(panier.getId());

        savePanier(panierToUpdate);

    }

    @Override
    public void deletePanier(int id) {
        panierRepository.delete(findById(id));

    }

    @Override
    public boolean isPanierExist(Panier panier) {
        return findById(panier.getId())!= null;
    }

    @Override
    public void addProduitToPanier(Panier panier,Produit produit) {

        if(contains(produit,panier))
        {
            panier.setQuantity(updateQuantity(panier,produit,panier.getQuantity().get(produit)+1));

        }
        else {

        List<Produit> produits = findById(panier.getId()).getProduit();
        produits.add(produit);
        panier.setQuantity(updateQuantity(panier,produit,1));
        panier.setProduit(produits);
        }

        savePanier(panier);

    }

    @Override
    public void deleteProduitFromPanier(Panier panier,Produit produit) {

        List<Produit>produits=panier.getProduit();
        Map<Produit,Integer> q=panier.getQuantity();

        produits.remove(produit);
        q.remove(produit);
        panier.setQuantity(q);
        panier.setProduit(produits);

        savePanier(panier);
    }
    @Override
    public boolean isEmpty(Panier panier) {
        return panier.getProduit().isEmpty();
    }

    @Override
    public boolean contains(Produit produit,Panier panier) {

        if(panier.getQuantity().containsKey(produit))
        {
            return true;
        }
        return false;
    }


    public Map<Produit,Integer> updateQuantity(Panier panier,Produit produit,int quantity){

        Map<Produit,Integer> q=panier.getQuantity();
        q.put(produit,quantity);
        return q;
    }

    public void emptyPanier(Panier panier,List<Produit> produits)
    {
        Map<Produit,Integer> q=panier.getQuantity();
        List<Produit> pr=new ArrayList<>();
        for(Produit p:produits)
        {
            q.remove(p);
        }

        panier.setQuantity(q);
        panier.setProduit(pr);
        savePanier(panier);
    }
}
