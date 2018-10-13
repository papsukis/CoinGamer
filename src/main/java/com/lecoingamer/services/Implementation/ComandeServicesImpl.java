package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Comande;
import com.lecoingamer.model.Panier;
import com.lecoingamer.model.User;
import com.lecoingamer.repository.ComandeRepository;
import com.lecoingamer.services.ComandeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ComandeServicesImpl implements ComandeServices {

    @Autowired
    ComandeRepository comandeRepository;

    @Override
    public Comande findById(int id) {
        List<Comande> comandes = comandeRepository.findAll();
        for(Comande comande : comandes){
            if(comande.getId() == id){
                return comande;
            }
        }
        return null;
    }

    @Override
    public Comande findByUser(User user) {
        return comandeRepository.findComandeByUser(user);
    }

    @Override
    public void saveComande(Comande comande) {
        comandeRepository.save(comande);
    }

    @Override
    public void updateComande(Comande comande) {
        Comande comandeToUpdate=comande;

        comandeToUpdate.setId(comande.getId());

        saveComande(comandeToUpdate);
    }

    @Override
    public void deleteComande(int id) {
        comandeRepository.delete(findById(id));

    }

    @Override
    public List<Comande> findAllComande() {
        return comandeRepository.findAll();
    }

    @Override
    public boolean isComandeExist(Comande comande) {
        return findById(comande.getId())!=null;
    }

    @Override
    public void addPanierToComande(Comande comande, Panier panier) {

        comande.setPanier(panier);

        updateComande(comande);

    }
}
