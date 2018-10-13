package com.lecoingamer.repository;


import com.lecoingamer.model.Privilege;
import com.lecoingamer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


/**
 * @author Ali Belemlih
 * Interface PrivilegeRepository. Allows CRUD operations on entity Privilege in database
 * */

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege,Integer>{

    Privilege findByName(String name);
    List<Privilege> findByRoles(Role role);
}
