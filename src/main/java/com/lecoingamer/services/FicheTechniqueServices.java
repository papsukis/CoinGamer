package com.lecoingamer.services;

import com.lecoingamer.model.FicheTechnique;
import com.lecoingamer.model.Produit;

import java.util.List;

public interface FicheTechniqueServices {

    FicheTechnique findById(int id);
    void save(FicheTechnique ficheTechnique);
    void delete(int id);
    List<FicheTechnique> findByProduit(Produit produit);
}
