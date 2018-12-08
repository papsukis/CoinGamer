package com.lecoingamer.controller;

import com.lecoingamer.Utils.ListWrapper;
import com.lecoingamer.model.Categorie;
import com.lecoingamer.model.Disponibilite;
import com.lecoingamer.model.FicheTechnique;
import com.lecoingamer.model.Produit;
import com.lecoingamer.services.CategorieServices;
import com.lecoingamer.services.ImageServices;
import com.lecoingamer.services.ProduitServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProduitController {


    public Disponibilite disp=Disponibilite.Disponible;

    @Autowired
    ProduitServices produitServices;
    @Autowired
    CategorieServices categorieServices;
    @Autowired
    ImageServices imageServices;
    String img="";
    String nameSearch="";
    String referenceSearch="";
    int minSearch=0;
    int maxSearch=0;

    @GetMapping(value = "/admin/produitsPage")
    public String showProduit(Model model,@RequestParam(defaultValue = "0") int page ){

        nameSearch="";
        referenceSearch="";
        minSearch=0;
        maxSearch=0;

        model.addAttribute("Produit",new Produit());
        model.addAttribute("Produits",produitServices.findAllProduit(page,5));
        model.addAttribute("categorie",categorieServices.findAllCategorie());
        model.addAttribute("currentPage",page);

        return "/admin/produitsPage";
    }

    @GetMapping(value =  "/admin/addProduit" )
    public String addProduit(Model model) {
        Disponibilite disponibilite;

        model.addAttribute("produit", new Produit());
        model.addAttribute("categories",categorieServices.findAllCategorie());
        model.addAttribute("categorie",new Categorie());

        return "/admin/addProduit";

    }
    @PostMapping(value="/saveProduit")
    public String saveProduit(Model model, @ModelAttribute("produit") Produit produit ,@RequestParam("uploadfile") MultipartFile file) throws IOException {


        List<FicheTechnique> f=produit.getFicheTechnique();


        produit.setImage(imageServices.createImage(file));


        produitServices.saveProduit(produit);

        return "redirect:/admin/produitsPage";
    }


    @RequestMapping(value = { "/admin/updateProduit/{id}" }, method = RequestMethod.GET)
    public String updateProduit(Model model,@PathVariable("id") int id) {

        model.addAttribute("produit",produitServices.findById(id));
        model.addAttribute("allproduit",produitServices.findAllProduit());
        model.addAttribute("categories",categorieServices.findAllCategorie());

        return "common/modal :: update";
    }
    @RequestMapping(value = { "/admin/updateProduit" }, method = RequestMethod.POST)
    public String saveUpdatedProduit(Model model,@ModelAttribute Produit produit) {

        produitServices.saveProduit(produit);

        return "redirect:/admin/produitsPage";
    }
    @RequestMapping(value="/admin/deleteProduit", method = RequestMethod.GET)
    public String deleteUser (@RequestParam("id") int id) {

        produitServices.deleteProduit(id);

        return "redirect:/admin/produitsPage";
    }

    @RequestMapping("/produit/{id}")
    public String produit(@PathVariable int id,Model model)
    {
        model.addAttribute(produitServices.findById(id));
        return "common/modal :: modalContents";

    }

    @RequestMapping("/updateproduit/{id}")
    public String updateProduit(@PathVariable int id,Model model)
    {
        Produit p=produitServices.findById(id);
        model.addAttribute(p);
        model.addAttribute("fiche",new FicheTechnique());
        model.addAttribute("categorie",categorieServices.findAllCategorie());
        return "common/modal :: update";

    }

    @RequestMapping(value="/admin/search", method = RequestMethod.GET)
    public String findProduit (Model model,@RequestParam(defaultValue = "") String nom,
                               @RequestParam(defaultValue = "") String reference,
                               @RequestParam(defaultValue = "0") int prixMin,
                               @RequestParam(defaultValue = "0") int prixMax
                                ,@RequestParam(defaultValue = "0") int page ) {


        model.addAttribute("Produit",new Produit());
        model.addAttribute("produits",produitServices.findBySearchTerm(nom,reference,prixMin,prixMax,page,5));
        model.addAttribute("categorie",categorieServices.findAllCategorie());
        model.addAttribute("currentPage",page);

        return "admin/search";
    }

}
