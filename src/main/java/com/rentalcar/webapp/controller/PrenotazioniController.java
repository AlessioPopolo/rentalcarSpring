package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.entity.TipologiaAutomezzo;
import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.AutoService;
import com.rentalcar.webapp.service.PrenotazioniService;
import com.rentalcar.webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class PrenotazioniController {

    @Autowired
    private PrenotazioniService prenotazioniService;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private AutoService autoService;

    @RequestMapping(value ="/prenotazioni/visualizza/{userId}")
    public String visualizzaPrenotazioniUtente(@PathVariable("userId") String customerId, Locale locale, Model model){
        Long id = Long.parseLong(customerId);
        model.addAttribute("command", new Utente());
        model.addAttribute("listaPrenotazioni", prenotazioniService.getPrenotazioniByUser(id));
        return "lista-prenotazioni";
    }

    @PostMapping("/prenotazioni/delete/{prenotazioniId}/{userId}")
    private String deletePrenotazione(@PathVariable("prenotazioniId") String prenotazioneId, @PathVariable("userId") String userId) {
        Long id = Long.parseLong(prenotazioneId);
        prenotazioniService.delete(id);
        //TODO if admin reindirizza da una parte altrimenti da un'altra
        return "redirect:/prenotazioni/visualizza/" + userId;
    }

    @PostMapping(value = "/prenotazioni/updatePrenotazione/{prenotazioniId}/{categoria}")
    public String updateAutoForm(@PathVariable("prenotazioniId") String prenotazioniId, @PathVariable("categoria") String categoria, Model model, HttpServletRequest request){
        Long id = Long.parseLong(prenotazioniId);
        model.addAttribute("command", new Prenotazioni());
        model.addAttribute("updatePrenotazione", prenotazioniService.getPrenotazione(id));
        model.addAttribute("listaAutoCategoria", autoService.getAllAutoFromCategoria(categoria));
        if (prenotazioniService.checkDatePrenotazione(prenotazioniService.getPrenotazione(id).getStartdate())){
            return "update-prenotazione-form";
        }
        else{
            return "redirect:" + request.getHeader("Referer");
        }
    }

    @RequestMapping(value = "prenotazioni/addPrenotazione/{userId}")
    public String addPrenotazioneForm(@PathVariable("userId") String userId, Locale locale, Model model){
        model.addAttribute("command", new Prenotazioni());
        model.addAttribute("userId", userId);
        model.addAttribute("listaAuto", autoService.getAllAutomezzi());
        return "add-prenotazione-form";
    }

    @RequestMapping(value= "/prenotazioni/addPrenotazione/save", method = RequestMethod.POST)
    public String addPrenotazione(@ModelAttribute("prenotazione") Prenotazioni p, HttpServletRequest request){
        Utente utente = utenteService.getCustomer(p.getUtente().getId());
        Automezzo automezzo = autoService.getAutomezzo(p.getAutomezzo().getId());
        Prenotazioni prenotazione;

        if (p.getId()==null){
            prenotazione = new Prenotazioni(utente, automezzo, p.getStartdate(), p.getEnddate());
            this.prenotazioniService.save(prenotazione);
        }
        else {
            prenotazione = new Prenotazioni(p.getId(), utente, automezzo, p.getStartdate(), p.getEnddate());
            this.prenotazioniService.update(prenotazione);
        }
        //TODO if admin reindirizza da una parte altrimenti da un'altra
        return "redirect:/prenotazioni/visualizza/" + utente.getId();
    }

    @RequestMapping(value = "/prenotazioni/listaAllPrenotazioni")
    public String listaAllPrenotazioni(Model model){
        model.addAttribute("listaPrenotazioni", prenotazioniService.getAllPrenotazioni());
        return "lista-all-prenotazioni";
    }

}
