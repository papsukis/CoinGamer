package com.lecoingamer.services;



import com.lecoingamer.model.Privilege;
import com.lecoingamer.model.Role;
import com.lecoingamer.model.User;

import java.util.List;

/**
 * @author Ali Belemlih
 * Interface RoleServices. Abstract methods for manipulation of entity Role in database
 *
 * */

public interface RoleServices {

    Role findById(int id);
    Role findByName(String name);
    List<Role> findByUsers(User user);
    void saveRole(Role role);
    void updateRole(Role role);
    void deleteRole(int id);
    Boolean isRoleExist(Role role);
    List<Role> findAllRoles();
    void assignPrivilege(Role role, Privilege privilege);
    void assignPrivileges(Role role, List<Privilege> privileges);
    void deAssignPrivilege(Role role, Privilege privilege);
}
