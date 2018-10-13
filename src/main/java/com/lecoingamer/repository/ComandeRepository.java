package com.lecoingamer.repository;


import com.lecoingamer.model.Comande;
import com.lecoingamer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandeRepository extends JpaRepository<Comande,Integer> {

    Comande findComandeByUser(User user);


}
