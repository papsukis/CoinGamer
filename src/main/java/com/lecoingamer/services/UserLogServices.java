package com.lecoingamer.services;



import com.lecoingamer.model.User;
import com.lecoingamer.model.UserLog;

import java.util.List;

/**
 * @author Ali Belemlih
 * Interface UserLogServices. Abstract methods for manipulation of entity Privilege in database
 *
 * */

public interface UserLogServices {

    UserLog findById(int id);
    UserLog findByUser(User user);
    void saveUserLog(UserLog userLog);
    void deleteUserLog(int id);
    List<UserLog> findAllLogs();
    boolean isLogExist(UserLog userLog);
}
