package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.AutoService;
import com.rentalcar.webapp.service.TipologiaAutoService;
import com.rentalcar.webapp.service.TipologiaUtenteService;
import com.rentalcar.webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class AutoController {

    @Autowired
    private AutoService autoService;

    @Autowired
    private TipologiaAutoService tipologiaAutoService;

    @GetMapping("/auto")
    public String userForm(Locale locale, Model model){
        model.addAttribute("auto", autoService.getAllAutomezzi());
        return "lista-auto";
    }

}
