package com.lecoingamer.controller;

import com.lecoingamer.model.Panier;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.User;
import com.lecoingamer.services.PanierServices;
import com.lecoingamer.services.ProduitServices;
import com.lecoingamer.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class PanierController {

    @Autowired
    PanierServices panierServices;
    @Autowired
    ProduitServices produitServices;
    @Autowired
    UserServices userServices;


    @RequestMapping(value = "/addToPanier/{id}", method = RequestMethod.GET)
    public String addProduitToPanier(Model model, @PathVariable("id") int id) {

        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User logedUser = userServices.findByName(auth.getName());

        Panier panier = new Panier();

        if (logedUser.getPanier() == null) {

            logedUser.setPanier(panier);
            userServices.saveUser(logedUser);
        }

        panier = panierServices.findByUser(logedUser);

        Produit produit = produitServices.findById(id);

        // List<Produit> produits=logedUser.getPanier().getProduit();

        panierServices.addProduitToPanier(panier, produit);

        return "redirect:/produitsPage";
    }

    @RequestMapping(value = "/panierPage", method = RequestMethod.GET)
    public String showPanier(Model model) {

        User logedUser = userServices.findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        Panier panier = panierServices.findByUser(logedUser);

        Map<Produit, Integer> produits = panier.getQuantity();

        int total = 0;
        int quantity=0;
        for (Map.Entry<Produit, Integer> p : produits.entrySet()) {
            total = total + (p.getKey().getPrix() * p.getValue());
            quantity++;
        }

        model.addAttribute("produit", produits.entrySet());
        model.addAttribute("panier", new Panier());
        model.addAttribute("produits", panier.getProduit());
        model.addAttribute("total", total);
        model.addAttribute("quantity",quantity);

        return "panierPage";
    }

    @RequestMapping(value = "/deleteProduitFromPanier/{id}", method = RequestMethod.GET)
    public String deleteProduitFromPanier(Model model, @PathVariable("id") int id) {

        User logedUser = userServices.findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        panierServices.deleteProduitFromPanier(logedUser.getPanier(), produitServices.findById(id));

        return "redirect:/panierPage";
    }

    @RequestMapping(value = "/emptyPanier", method = RequestMethod.GET)
    public String emptyPanier(Model model) {

        User logedUser = userServices.findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        panierServices.emptyPanier(logedUser.getPanier(), logedUser.getPanier().getProduit());

        return "redirect:/panierPage";
    }
}
