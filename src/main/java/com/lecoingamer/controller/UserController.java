package com.lecoingamer.controller;


import com.lecoingamer.model.User;
import com.lecoingamer.services.RoleServices;
import com.lecoingamer.services.UserLogServices;
import com.lecoingamer.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@ComponentScan(basePackageClasses=UserController.class)

//@RequestMapping(value="/admin")
public class UserController {

    @Autowired
    UserServices userServices;
    @Autowired
    RoleServices roleServices;
    @Autowired
    UserLogServices userLogServices;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value="/admin/users",method = RequestMethod.GET)
    public String getAllUsers(Model model,HttpServletRequest request){


    model.addAttribute("newUser", new User());
    model.addAttribute("Users",userServices.findAllUsers());

    return "/admin/usersPage";

    }



    @RequestMapping(value = { "/admin/addUser" }, method = RequestMethod.GET)
    public String saveUer(Model model) {

        User user=new User();

        model.addAttribute("user",user);
        model.addAttribute("allroles",roleServices.findAllRoles());

        return "/admin/addUser";

    }

    @RequestMapping(value = { "/admin/saveUser" }, method = RequestMethod.POST)
    public String saveUser(Model model, @ModelAttribute("user") User user) {

        if(userServices.isUserExist(user)){

        }

        {user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));}
    userServices.saveUser(user);


    return "redirect:/admin/usersPage";

    }

    @RequestMapping(value = { "/admin//updateUser/{id}" }, method = RequestMethod.GET)
    public String saveUser(Model model,@PathVariable("id") int id) {

        model.addAttribute("user",userServices.findById(id));
        model.addAttribute("allRoles",roleServices.findAllRoles());

        return "/admin/updateUser";
    }

    @RequestMapping(value = { "/admin/updateUser" }, method = RequestMethod.POST)
    public String updateUser(Model model,@ModelAttribute("user") User user) {

        if(user.getPassword()== null)
        {
            user.setPassword(userServices.findByName(user.getName()).getPassword());}
            else{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));}

        userServices.updateUser(user);

        return "redirect:/admin/usersPage";
    }


    @RequestMapping(value="/admin/deleteUser", method = RequestMethod.GET)
    public String deleteUser (@RequestParam("id") int id) {

        userServices.deleteUserById(id);

        return "redirect:/admin/usersPage";
    }



}
