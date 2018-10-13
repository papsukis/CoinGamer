package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.Panier;
import com.lecoingamer.model.Role;
import com.lecoingamer.model.User;
import com.lecoingamer.repository.RoleRepository;
import com.lecoingamer.repository.UserRepository;
import com.lecoingamer.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author Ali Belemlih
 * Implementation of Interface UserServices. Provides implementation of methods for manipulation of entity User in database
 *
 * */

@Service
@Transactional
public class UserServicesimpl implements UserServices {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    /**
     * Finds a User in DB
     * @param Id
     * @return User
     */
    @Override
    public User findById(int Id) {
        List<User> users = userRepository.findAll();
        for(User user : users){
            if(user.getId() == Id){
                return user;
            }
        }
        return null;
    }

    /**
     * Assign Role to User
     * @param user
     * @param role
     */
    @Override
    public void assignRole(User user, Role role) {

        List<Role> roleToAssign=roleRepository.findByUsers(user);
        if(!roleToAssign.contains(role))
        {
            roleToAssign.add(role);
            user.setRoles(roleToAssign);
            updateUser(user);
        }

    }

    /**
     * Assigns a list of roles to User
     * @param user
     * @param roles
     */
    @Override
    public void assignRoles(User user, List<Role> roles) {
        for (Role tmp : roles) {
            assignRole(user,tmp);
        }
    }

    /**
     * Removes a roles from a user
     * @param user
     * @param role
     */
    @Override
    public void deAssignRole(User user, Role role) {
        List<Role> roleOfUser= roleRepository.findByUsers(user);

        if(!roleOfUser.isEmpty())
        roleOfUser.remove(roleOfUser.indexOf(role));

        user.setRoles(roleOfUser);
        updateUser(user);

    }

    @Override
    public void addPanierToUser(User user,Panier panier) {

        user.setPanier(panier);
        saveUser(user);

    }

    /**
     * Finds a User by his name
     * @param name
     * @return user
     */

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    /**
     * Saves a User in database
     * @param user
     */

    @Override
    public void saveUser(User user) {


        userRepository.save(user);
        assignRole(user,roleRepository.findByName("ROLE_USER"));
    }

    /**
     * Updates a User in database
     * @param user
     */

    @Override
    public void updateUser(User user) {

        User userToUpdate=user;
        userToUpdate.setId(user.getId());
        userRepository.save(userToUpdate);

    }

    /**
     * Deletes a User from database
     * @param Id
     */
    @Override
    public void deleteUserById(int Id) {
       userRepository.delete(findById(Id));

    }

    /**
     * return a list of all users currently in database
     * @return List<User>
     */
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {

    }

    /**
     * returns true if user exist and false if user doesn't exist
     * @param user
     * @return boolean
     */
    @Override
    public boolean isUserExist(User user) {
        return findById(user.getId())!=null;
    }
}
