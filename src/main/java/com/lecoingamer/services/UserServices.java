package com.lecoingamer.services;


import com.lecoingamer.model.Panier;
import com.lecoingamer.model.Role;
import com.lecoingamer.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Ali Belemlih
 * Interface UserServices. Abstract methods for manipulation of entity User in database
 *
 * */

public interface UserServices {

    User findById(int Id);
    User findByName(String name);
    void saveUser(User user);
    void updateUser(User user);
    void deleteUserById(int Id);
    List<User> findAllUsers();
    void deleteAllUsers();
    public boolean isUserExist(User user);
    void assignRole(User user, Role role);
    void assignRoles(User user, List<Role> roles);
    void deAssignRole(User user, Role role);
    void addPanierToUser(User user,Panier panier);


}
