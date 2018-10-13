package com.lecoingamer.controller;


import com.lecoingamer.model.UserLog;
import com.lecoingamer.services.UserLogServices;
import com.lecoingamer.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserLogController {

    @Autowired
    UserServices userServices;
    @Autowired
    UserLogServices userLogServices;

    @RequestMapping(value="/userLogsPage",method = RequestMethod.GET)
    public String showAllLogs (Model model){

        model.addAttribute("logs",userLogServices.findAllLogs());
        model.addAttribute("log",new UserLog());
        //model.addAttribute("users",u)

        return "userLogsPage";

    }

}
