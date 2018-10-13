package com.lecoingamer.services;


import com.lecoingamer.model.Privilege;
import com.lecoingamer.model.Role;

import java.util.List;


/**
 * @author Ali Belemlih
 * Interface PrivilegeServices. Abstract methods for manipulation of entity Privilege in database
 *
 * */

public interface PrivilegeServices {

    Privilege findById(int id);
    Privilege findByName(String name);
    List<Privilege> findByRole(Role role);
    void savePrivilege(Privilege privilege);
    void UpdatePrivilege(Privilege privilege);
    void deletePrivilege(int id);
    Boolean isPrivilegeExist(Privilege privilege);
    List<Privilege> findAllPrivileges();

}
