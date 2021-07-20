package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.TipologiaUtente;
import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.TipologiaUtenteService;
import com.rentalcar.webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/utente/lista-customers")
    public String userForm(Locale locale, Model model){
        model.addAttribute("utenti", utenteService.getAllCustomers());
        return "lista-customers";
    }

    @RequestMapping(value = "utente/addCustomer")
    public String addCustomerForm(Locale locale, Model model){
        model.addAttribute("command", new Utente());
        model.addAttribute("listaRuoli", tipologiaUtenteService.getAllTipologie());
        return "update-customer-form";
    }

    @PostMapping(value = "/utente/updateCustomer/{userId}")
    public String updateCustomerForm(@PathVariable("userId") String customerId, Locale locale, Model model){
        Long id = Long.parseLong(customerId);
        model.addAttribute("command", new Utente());
        model.addAttribute("listaRuoli", tipologiaUtenteService.getAllTipologie());
        model.addAttribute("updateUtente", utenteService.getCustomer(id));
        return "update-customer-form";
    }

    @RequestMapping(value= "/utente/addCustomer/save", method = RequestMethod.POST)
    public String addUtente(@ModelAttribute("utente") Utente p){

        TipologiaUtente mioRuolo = this.tipologiaUtenteService.getRuolo(p.getRuolo().getRuolo());
        Utente nuovoUtente;
        if (p.getId()== null){
            p.setPassword(passwordEncoder.encode(p.getPassword()));
            nuovoUtente = new Utente(p.getSsoId(), p.getPassword(), p.getNome(), p.getCognome(), p.getDatadinascita(), mioRuolo);
            this.utenteService.save(nuovoUtente);
        }
        else {
            p.setPassword(passwordEncoder.encode(p.getPassword()));
            nuovoUtente = new Utente(p.getId(), p.getSsoId(), p.getPassword(), p.getNome(), p.getCognome(), p.getDatadinascita(), mioRuolo);
            this.utenteService.update(nuovoUtente);
        }
        return "redirect:/utente/lista-customers";

    }

    @PostMapping("/utente/delete/{userId}")
    private String deleteCustomer(@PathVariable("userId") String customerId) {
        Long id = Long.parseLong(customerId);
        utenteService.delete(id);
        return "redirect:/utente/lista-customers";
    }

    @GetMapping(value = "/utente/search/")
    public String searchCustomers(@RequestParam (value = "theSearchName") String search, Model model){
        model.addAttribute("utenti", utenteService.searchCustomers(search));
        return "lista-customers";
    }

}
