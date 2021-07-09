package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.TipologiaUtenteService;
import com.rentalcar.webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private TipologiaUtenteService tipologiaUtenteService;

    @GetMapping("/")
    public String userForm(Locale locale, Model model){
        model.addAttribute("utenti", utenteService.getAllCustomers());
        return "utente";
    }

    @RequestMapping(value = "utente/addCustomer")
    public String addCustomerForm(Locale locale, Model model){
        model.addAttribute("command", new Utente());
        model.addAttribute("listaRuoli", tipologiaUtenteService.getAllTipologie());
        return "add-customer-form";
    }

    //For add and update person both
    @RequestMapping(value= "/utente/addCustomer/save", method = RequestMethod.POST)
    public String addUtente(@ModelAttribute("utente") Utente p){

        this.utenteService.save(p);
        return "redirect:/utente";

    }

    @PostMapping("/utente/delete/{userId}")
    private String deleteCustomer(@PathVariable("userId") String customerId) {
        Long id = Long.parseLong(customerId);
        utenteService.delete(id);
        return "redirect:/";
    }

}
