package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/")
    public String userForm(Locale locale, Model model){
        model.addAttribute("utenti", utenteService.getAllCustomers());
        return "utente";
    }

    @RequestMapping(value = "utente/addCustomer")
    public String addCustomerForm(Locale locale, Model model){
        return "add-customer-form";
    }

    //For add and update person both
    @RequestMapping(value= "/utente/addCustomer/save", method = RequestMethod.POST)
    public String addUtente(@ModelAttribute("utente") Utente p){

        this.utenteService.save(p);
        return "redirect:/utente";

    }

}
