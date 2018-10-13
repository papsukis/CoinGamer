package com.lecoingamer.repository;


import com.lecoingamer.model.Role;
import com.lecoingamer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * @author Ali Belemlih
 * Interface RoleRepository. Allows CRUD operations on entity Role in database
 * */

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByName(String name);
    List<Role> findByUsers(User user);
}
