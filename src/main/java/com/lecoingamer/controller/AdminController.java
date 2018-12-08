package com.lecoingamer.controller;

import com.lecoingamer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    UserServices userServices;
    @Autowired
    RoleServices roleServices;

    @Autowired
    ProduitServices produitServices;
    @Autowired
    PanierServices panierServices;
    @Autowired
    ImageServices imageServices;


    @GetMapping("/admin/home")
    public String home(Model model)
    {

        return "/admin/home";
    }

}
