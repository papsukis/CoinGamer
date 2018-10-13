package com.lecoingamer.controller;

import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Disponibilite;
import com.lecoingamer.model.Produit;
import com.lecoingamer.services.CategorieServices;
import com.lecoingamer.services.ProduitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProduitController {


    public Disponibilite disp=Disponibilite.Disponible;

    @Autowired
    ProduitServices produitServices;
    @Autowired
    CategorieServices categorieServices;

    @GetMapping(value = "/produitsPage")
    public String showProduit(Model model){


        model.addAttribute("Produit",new Produit());
        model.addAttribute("Produits",produitServices.findAllProduit());
        model.addAttribute("categorie",categorieServices.findAllCategorie());


        return "produitsPage";
    }

    @GetMapping(value =  "/addProduit" )
    public String addProduit(Model model) {
        Disponibilite disponibilite;

        model.addAttribute("produit", new Produit());
        model.addAttribute("categories",categorieServices.findAllCategorie());
        model.addAttribute("categorie",new Categorie());


        return "addProduit";

    }
    @PostMapping(value="/saveProduit")
    public String saveProduit(Model model, @ModelAttribute("produit") Produit produit){


        produitServices.saveProduit(produit);

        return "redirect:/produitsPage";
    }

    @RequestMapping(value = { "/updateProduit/{id}" }, method = RequestMethod.GET)
    public String updateProduit(Model model,@PathVariable("id") int id) {

        model.addAttribute("produit",produitServices.findById(id));
        model.addAttribute("allproduit",produitServices.findAllProduit());
        model.addAttribute("categories",categorieServices.findAllCategorie());

        return "updateProduit";
    }
    @RequestMapping(value = { "/updateProduit" }, method = RequestMethod.POST)
    public String saveUpdatedProduit(Model model,@ModelAttribute Produit produit) {

        produitServices.saveProduit(produit);

        return "redirect:/produitsPage";
    }
    @RequestMapping(value="/deleteProduit", method = RequestMethod.GET)
    public String deleteUser (@RequestParam("id") int id) {

        produitServices.deleteProduit(id);

        return "redirect:/produitsPage";
    }



}
