package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/")
    public String userForm(Locale locale, Model model){
        model.addAttribute("utenti", utenteService.getAllUtenti());
        return "utente";
    }

}
