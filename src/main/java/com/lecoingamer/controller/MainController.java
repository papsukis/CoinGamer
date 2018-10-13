package com.lecoingamer.controller;

import com.lecoingamer.model.*;
import com.lecoingamer.services.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    UserServices userServices;
    @Autowired
    RoleServices roleServices;
    @Autowired
    PrivilegeServices privilegeServices;
    @Autowired
    ProduitServices produitServices;
    @Autowired
    PanierServices panierServices;


    @Autowired
    CategorieServices categorieServices;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String welcomePage(Model model) {

        int total = 0;
        int quantity = 0;
        User logedUser = userServices.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if(logedUser!=null) {
            Panier panier = panierServices.findByUser(logedUser);

            Map<Produit, Integer> produits = panier.getQuantity();


            for (Map.Entry<Produit, Integer> p : produits.entrySet()) {
                total = total + (p.getKey().getPrix() * p.getValue());
                quantity++;
            }
        }
        model.addAttribute("categorie",categorieServices.findAllCategorie());
       // model.addAttribute("title", "Welcome");
        //model.addAttribute("message", "This is welcome page!");
       model.addAttribute("Produit",new Produit());
       model.addAttribute("Produits",produitServices.findAllProduit());
        model.addAttribute("quantity",quantity);

        return "welcomePage";
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public String adminPage(Model model, User user) {

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logedUser = userServices.findByName(auth.getName());

        String userInfo = logedUser.toString();
        model.addAttribute("userInfo", userInfo);

        return "adminPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessfulPage";
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, User user) {

        // After user login successfully.
        String userName = user.getName();

        System.out.println("User Name: " + userName);

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logedUser = userServices.findByName(auth.getName());

        String userInfo = logedUser.toString();
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("users",userServices.findAllUsers());
        return "userInfoPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, User user) {

        if (user != null) {
            User logedUser = user;

            String userInfo = user.toString();

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + user.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }



}