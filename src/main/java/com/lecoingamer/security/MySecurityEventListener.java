package com.lecoingamer.security;



import com.lecoingamer.model.User;
import com.lecoingamer.model.UserLog;
import com.lecoingamer.services.UserLogServices;
import com.lecoingamer.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Implementation of applicationListener. When a user is successfully authorized , a log of his connexion will be stored in the database.
 * Stores IP address of connexion, and date of connexion. Also sets his Usual_IP_OfConnexion in the database.
 */

public class MySecurityEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    UserServices userServices;
    @Autowired
    UserLogServices userLogServices;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

        User logedUser = userServices.findByName(event.getAuthentication().getName());

        WebAuthenticationDetails details = (WebAuthenticationDetails) event.getAuthentication().getDetails();
        if(logedUser.getUsual_IP()== null)
        {
            logedUser.setUsual_IP(details.getRemoteAddress());
            logedUser.setFirstLog(false);
            userServices.saveUser(logedUser);
        }

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        UserLog userLog=new UserLog(details.getRemoteAddress(),date,logedUser);

        userLogServices.saveUserLog(userLog);
    }
}
