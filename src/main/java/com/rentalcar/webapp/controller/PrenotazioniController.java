package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class PrenotazioniController {

    @Autowired
    private PrenotazioniService prenotazioniService;

    @RequestMapping(value ="/prenotazioni/visualizza/{userId}")
    public String visualizzaPrenotazioniUtente(@PathVariable("userId") String customerId, Locale locale, Model model){
        Long id = Long.parseLong(customerId);
        model.addAttribute("command", new Utente());
        model.addAttribute("listaPrenotazioni", prenotazioniService.getPrenotazioniByUser(id));
        return "lista-prenotazioni";
    }

}
