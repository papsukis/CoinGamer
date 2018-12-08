package com.lecoingamer.controller;

import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.SousCategorie;
import com.lecoingamer.services.CategorieServices;
import com.lecoingamer.services.ProduitServices;
import com.lecoingamer.services.SousCategorieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategorieController {

    @Autowired
    CategorieServices categorieServices;
    @Autowired
    ProduitServices produitServices;
    @Autowired
    SousCategorieServices sousCategorieServices;

    @GetMapping(value="/admin/categoriePage")
    public String showCategorie(Model model)
    {

        List<Categorie> c=categorieServices.findAllCategorie();

        model.addAttribute("categorie",new Categorie());
        model.addAttribute("categories",c);
        model.addAttribute("souscategorie",new SousCategorie());

        return "/admin/categoriePage";
    }

    @GetMapping(value="/addCategorie")
    public String addCategorie(Model model){

        model.addAttribute("categorie",new Categorie());

        return "/admin/addCategorie";

    }
    @PostMapping(value="/addCategorie")
    public String saveCategorie(Model model, @ModelAttribute Categorie categorie){

        categorieServices.saveCategorie(categorie);

        return "redirect:/admin/categoriePage";

    }

   /* @GetMapping(value="/admin/addSouscategorie")
    public String addSouscategorie(Model model)
    {




    }*/

    @GetMapping("/addSouscategorie/{id}")
    public String updateProduit(@PathVariable int id, Model model)
    {

        model.addAttribute("souscategorie",new SousCategorie());
        model.addAttribute(categorieServices.findById(id));

        return "common/modal :: addSouscategorie";

    }
    @PostMapping("/admin/addSouscategorie/{id}")
    public String updateProduit(@PathVariable int id,@ModelAttribute SousCategorie sc, Model model)
    {

        SousCategorie sce=sc;
        categorieServices.addSousCategorieToCategorie(categorieServices.findById(id),sce);

        return "redirect:/admin/categoriePage";

    }

    @GetMapping("/souscategorie/{id}")
    public String showProduits(@PathVariable int id, Model model)
    {

        List<Produit> l=sousCategorieServices.findProduits(id);


        model.addAttribute("produits",l);

        return "common/modal :: produits";

    }

    @GetMapping("/admin/Gproduit/{id}")
    public String addProduitToSousCategorie(Model model,@PathVariable int id)
    {

        model.addAttribute("produits",produitServices.findAllProduit());

        model.addAttribute("id",id);
        model.addAttribute("sc",sousCategorieServices.findById(id));

        return "/admin/Gproduit";
    }

    @PostMapping("/admin/Gproduit")
    public String addProduitToSousCategorie(@RequestParam List<Produit> produits,@ModelAttribute SousCategorie sc)
    {


        sousCategorieServices.addProduitToSousCategorie(sc,produits);

        return "redirect:/admin/categoriePage";

    }

    @GetMapping("/admin/delete")
    public String deleteSousCatégorie(@RequestParam int id)
    {
        sousCategorieServices.deleteSousCategorie(id);
        return "redirect:/admin/categoriePage";
    }
    @GetMapping("/admin/deletecat")
    public String deleteCatégorie(@RequestParam int id)
    {
        categorieServices.deleteCategorie(id);
        return "redirect:/admin/categoriePage";
    }
}
