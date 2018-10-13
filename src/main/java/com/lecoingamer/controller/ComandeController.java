package com.lecoingamer.controller;

import com.lecoingamer.model.Comande;
import com.lecoingamer.model.Etat;
import com.lecoingamer.model.Produit;
import com.lecoingamer.model.User;
import com.lecoingamer.services.ComandeServices;
import com.lecoingamer.services.PanierServices;
import com.lecoingamer.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ComandeController {

    @Autowired
    PanierServices panierServices;
    @Autowired
    UserServices userServices;
    @Autowired
    ComandeServices comandeServices;

    @RequestMapping(value="/validateComande", method = RequestMethod.GET)
    public String validateComande(Model model){

        User logedUser = userServices.findByName(SecurityContextHolder.getContext().getAuthentication().getName());

        Comande comande=new Comande();
        int total = 0;


        comande.setPanier(logedUser.getPanier());
        comande.setUser(logedUser);
        for (Map.Entry<Produit, Integer> p : comande.getPanier().getQuantity().entrySet()) {
            total = total + (p.getKey().getPrix() * p.getValue());
        }
        System.out.println(total);
        System.out.println(logedUser.getName());
        comande.setTotal(total);
        comande.setEtat(Etat.Nouvelle);


        comandeServices.saveComande(comande);

        return "redirect:/produitsPage";

    }

    @GetMapping(value="/comandePage")
    public String showComande(Model model){

        model.addAttribute("comandes",comandeServices.findAllComande());

        return "comandePage";
    }
    @GetMapping(value={"/comandeDetails/{id}"})
    public String showComandeDetails(Model model, @PathVariable("id") int id){

        Comande comande=comandeServices.findById(id);


        model.addAttribute("comande",comande);
        model.addAttribute("produitList",comande.getPanier().getQuantity().entrySet());

        return "comandeDetails";
    }
}
