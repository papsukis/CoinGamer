package com.lecoingamer.controller;

import com.lecoingamer.model.Categorie;
import com.lecoingamer.services.CategorieServices;
import com.lecoingamer.services.ProduitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategorieController {

    @Autowired
    CategorieServices categorieServices;
    @Autowired
    ProduitServices produitServices;

    @GetMapping(value="/categoriePage")
    public String showCategorie(Model model)
    {

        model.addAttribute("categories",categorieServices.findAllCategorie());


        return "categoriePage";
    }

    @GetMapping(value="/addCategorie")
    public String addCategorie(Model model){

        model.addAttribute("categorie",new Categorie());

        return "addCategorie";

    }
    @PostMapping(value="/addCategorie")
    public String saveCategorie(Model model, @ModelAttribute Categorie categorie){

        categorieServices.saveCategorie(categorie);

        return "redirect:/categoriePage";

    }

    @GetMapping(value="/header")
    public String header(Model model) {



        return "header";
    }

}
