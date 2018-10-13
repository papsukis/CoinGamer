package com.lecoingamer.services.Implementation;


import com.lecoingamer.model.User;
import com.lecoingamer.model.UserLog;
import com.lecoingamer.repository.UserLogRepository;
import com.lecoingamer.services.UserLogServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Ali Belemlih
 * Implementation of Interface UserLogServices. Provides implementation of methods for manipulation of entity UserLog in database
 */
@Service
@Transactional
public class UserLogServicesImpl implements UserLogServices {

    @Autowired
    UserLogRepository userLogRepository;

    /**
     * Find a UserLog by his ID
     * @param id
     * @return UserLog
     */
    @Override
    public UserLog findById(int id) {

        return userLogRepository.getOne(id);
    }

    /**
     * Find a UserLog of a User
     * @param user
     * @return UserLog
     */
    @Override
    public UserLog findByUser(User user) {
        return userLogRepository.findUserLogByUser(user);
    }

    /**
     * Saves a UserLog in database
     * @param userLog
     */
    @Override
    public void saveUserLog(UserLog userLog) {
        userLogRepository.save(userLog);

    }

    /**
     * Deletes a UserLog from database
     * @param id
     */
    @Override
    public void deleteUserLog(int id) {
        userLogRepository.deleteById(id);

    }

    /**
     * Returns a Listof UserLog containing all userLog currently in database
     * @return List<UserLog>
     */
    @Override
    public List<UserLog> findAllLogs() {
        return userLogRepository.findAll();
    }

    /**
     * Returns True if log exists, and false if he doesn't
     * @param userLog
     * @return boolean
     */
    @Override
    public boolean isLogExist(UserLog userLog) {
        return findById(userLog.getId())!=null;
    }
}
