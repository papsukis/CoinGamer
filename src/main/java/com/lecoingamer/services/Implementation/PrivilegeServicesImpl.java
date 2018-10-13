package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Privilege;
import com.lecoingamer.model.Role;
import com.lecoingamer.repository.PrivilegeRepository;
import com.lecoingamer.services.PrivilegeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Ali Belemlih
 * Implementation of Interface PrivilegServices. Provides implementation of methods for manipulation of entity Privilege in database
 *
 * */

@Service
@Transactional
public class PrivilegeServicesImpl implements PrivilegeServices {

    @Autowired
    PrivilegeRepository privilegeRepository;

    /**
     * Finds a privilege by id
     * @param id
     * @return privilege
     */
    @Override
    public Privilege findById(int id) {
        List<Privilege> privileges = privilegeRepository.findAll();
        for(Privilege privilege : privileges){
            if(privilege.getId() == id){
                return privilege;
            }
        }
        return null;
    }

    /**
     * Finds a parameter by name
     * @param name
     * @return privilege
     */
    @Override
    public Privilege findByName(String name) {
        return privilegeRepository.findByName(name);
    }

    /**
     * Returns a list of privilege by role
     * @param role
     * @return List<Privilege>
     */
    @Override
    public List<Privilege> findByRole(Role role) {
        List<Privilege> privileges= privilegeRepository.findByRoles(role);
        return privileges;
    }

    /**
     * Saves a privilege in the database
     * @param privilege
     */
    @Override
    public void savePrivilege(Privilege privilege) {
        privilegeRepository.save(privilege);

    }

    /**
     *Updates a privilege
     * @param privilege
     */
    @Override
    public void UpdatePrivilege(Privilege privilege) {
        Privilege privilegeToUpdate= privilege;

        privilegeToUpdate.setId(privilege.getId());

        savePrivilege(privilegeToUpdate);

    }

    /**
     * Deletes a privilege by id
     * @param id
     */
    @Override
    public void deletePrivilege(int id) {
        privilegeRepository.delete(findById(id));

    }

    /**
     * Returns true if a privilege exists, false if he doesn't
     * @param privilege
     * @return boolean
     */
    @Override
    public Boolean isPrivilegeExist(Privilege privilege) {
        return findByName(privilege.getName())!=null;
    }

    /**
     * Returns a List of all privileges currently in the database
     * @return List<Privilege>
     */
    @Override
    public List<Privilege> findAllPrivileges() {
        List<Privilege> privileges=privilegeRepository.findAll();
        return privileges;
    }

}
