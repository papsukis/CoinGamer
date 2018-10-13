package com.lecoingamer.services;


import com.lecoingamer.model.Comande;
import com.lecoingamer.model.Panier;
import com.lecoingamer.model.User;

import java.util.List;

public interface ComandeServices {

    Comande findById(int id);
    Comande findByUser(User user);
    void saveComande(Comande comande);
    void updateComande(Comande comande);
    void deleteComande(int id);
    List<Comande> findAllComande();
    boolean isComandeExist(Comande comande);
    void addPanierToComande(Comande comande, Panier panier);
}
