package com.lecoingamer.repository;


import com.lecoingamer.model.Panier;
import com.lecoingamer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanierRepository extends JpaRepository<Panier,Integer> {

    Panier findByReference(String reference);
    Panier findPanierByUser(User user);
}
