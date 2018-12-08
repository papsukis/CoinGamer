package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Produit;
import com.lecoingamer.repository.ProduitRepository;
import com.lecoingamer.services.ProduitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public Page<Produit> findByName(String name, int page, int limit) {

        return produitRepository.findByNameContaining(name,PageRequest.of(page,limit));
    }


    public Page<Produit> containsSearchTerm(String searchTerm,int page,int limit){

        Pageable pageable=PageRequest.of(page,5);
        List<Produit> produits=contains(searchTerm);

        Page<Produit> p = new PageImpl<>(produits,pageable,produits.size());

        return p;
    }


    public List<Produit> contains(String contains){
        List<Produit> p=findAllProduit();
        List<Produit> pr=new ArrayList<>();

        for(Produit pro:p){

            if(pro.getName().contains(contains)){
                pr.add(pro);
            }
        }

        return pr;
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

    @Override
    public Page<Produit> findAllProduit(int page,int q) {
        return produitRepository.findAll(PageRequest.of(page, q) );
    }

    @Override
    public Page<Produit> findBySearchTerm(String nameSearch, String referenceSearch, int minSearch, int maxSearch, int page, int i) {

        if(minSearch==0 && maxSearch==0)
            return produitRepository.findByNameContainingAndReferenceContaining(nameSearch,referenceSearch,PageRequest.of(page,i));
        return produitRepository.findByNameContainingAndReferenceContainingAndPrixGreaterThanEqualAndPrixLessThanEqual(nameSearch,referenceSearch,minSearch,maxSearch,PageRequest.of(page,i));
    }

}
