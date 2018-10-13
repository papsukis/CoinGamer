package com.lecoingamer.repository;


import com.lecoingamer.model.User;
import com.lecoingamer.model.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Ali Belemlih
 * Interface UserLogRepository. Allows CRUD operations on entity UserLog in database
 * */

@Repository
public interface UserLogRepository extends JpaRepository<UserLog,Integer> {

    UserLog findUserLogByUser(User user);

}
