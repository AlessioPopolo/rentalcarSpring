package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.entity.TipologiaAutomezzo;
import com.rentalcar.webapp.entity.TipologiaUtente;
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

    @RequestMapping(value = "auto/addAuto")
    public String addCustomerForm(Locale locale, Model model){
        model.addAttribute("command", new Automezzo());
        model.addAttribute("listaCategorie", tipologiaAutoService.getAllTipologie());
        return "add-auto-form";
    }

    @RequestMapping(value= "/auto/addAuto/save", method = RequestMethod.POST)
    public String addAuto(@ModelAttribute("auto") Automezzo p){

        TipologiaAutomezzo miaCategoria = this.tipologiaAutoService.getCategoria(p.getCategoria().getCategoria());
        Automezzo nuovoAutomezzo;
        if (p.getId()== null){
            nuovoAutomezzo = new Automezzo(p.getTarga(), p.getCasacostruttrice(), p.getModello(), p.getImmatricolazione(), miaCategoria);
            this.autoService.save(nuovoAutomezzo);
        }
        else {
            nuovoAutomezzo = new Automezzo(p.getId(), p.getTarga(), p.getCasacostruttrice(), p.getModello(), p.getImmatricolazione(), miaCategoria);
            this.autoService.update(nuovoAutomezzo);
        }


        return "redirect:/auto";

    }

}
