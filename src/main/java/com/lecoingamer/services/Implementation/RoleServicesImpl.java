package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Privilege;
import com.lecoingamer.model.Role;
import com.lecoingamer.model.User;
import com.lecoingamer.repository.PrivilegeRepository;
import com.lecoingamer.repository.RoleRepository;
import com.lecoingamer.repository.UserRepository;
import com.lecoingamer.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Ali Belemlih
 * Implementation of Interface RoleServices. Provides implementation of methods for manipulation of entity Role in database
 *
 * */

@Service
@Transactional
public class RoleServicesImpl implements RoleServices {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;


    /**
     * Finds a role in database from the name of the role
     * @param name
     * @return Role
     */
    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    /**
     * Returns a List of roles containing all roles in database currently
     * @return List<Role>
     */
    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Assign a privilege to a role
     * @param role
     * @param privilege
     */
    @Override
    public void assignPrivilege(Role role, Privilege privilege) {

        List<Privilege> privilegeToAssign=privilegeRepository.findByRoles(role);
        if(!privilegeToAssign.contains(privilege))
        {
            privilegeToAssign.add(privilege);
            role.setPrivileges(privilegeToAssign);

            updateRole(role);

        }
    }

    /**
     * Assign a List of privilege to a role
     * @param role
     * @param privileges
     */
    @Override
    public void assignPrivileges(Role role, List<Privilege> privileges) {
        for (Privilege tmp : privileges) {
            assignPrivilege(role,tmp);
        }
    }

    /**
     * Deassign a privilege to a role
     * @param role
     * @param privilege
     */
    @Override
    public void deAssignPrivilege(Role role, Privilege privilege) {
        List<Privilege> privilegeOfUser= privilegeRepository.findByRoles(role);

        if(!privilegeOfUser.isEmpty())
            privilegeOfUser.remove(privilegeOfUser.indexOf(role));

        role.setPrivileges(privilegeOfUser);
        updateRole(role);
    }

    /**
     * Finds the roles assigned to a user
     * @param user
     * @return List<Role>
     */
    @Override
    public List<Role> findByUsers(User user) {

        return roleRepository.findByUsers(user);
    }

    /**
     * Saves a role in the database
     * @param role
     */
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
        assignPrivilege(role,privilegeRepository.findByName("READ_PERMISSION"));
    }

    /**
     * Updates a role in the database
     * @param role
     */
    @Override
    public void updateRole(Role role) {
        Role roleToUpdate = role;

        roleToUpdate.setId(role.getId());

        saveRole(roleToUpdate);
    }

    /**
     * Finds a role by his ID
     * @param id
     * @return Role
     */
    @Override
    public Role findById(int id) {
        List<Role> roles = roleRepository.findAll();
        for(Role role : roles){
            if(role.getId() == id){
                return role;
            }
        }
        return null;
    }

    /**
     * Delete a role by his id
     * @param id
     */
    @Override
    public void deleteRole(int id) {
        roleRepository.delete(findById(id));
    }

    /**
     * Returns true if role exist, false if he doesn't
     * @param role
     * @return
     */
    @Override
    public Boolean isRoleExist(Role role) {
        return findByName(role.getName())!=null;
    }

}
