package com.rentalcar.webapp.controller;

import com.rentalcar.webapp.entity.Utente;
import com.rentalcar.webapp.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping(value = {"/", "/login"})
    public String loginForm(){
        return "login";
    }

    @RequestMapping(value = "/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/redirectHomepage")
    public String userForm(Authentication authentication){
        if (authentication.getAuthorities()!=null){
            if (authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")){
                return "redirect:/utente/lista-customers";
            }
            else if (authentication.getAuthorities().toString().equals("[ROLE_USER]")){
                Utente u = utenteService.findUserBySSO(authentication.getName());
                return "redirect:/prenotazioni/visualizza/"+u.getId();
            }
            else {
                return "redirect:/login";
            }
        }
        else {
            return "redirect:/login";
        }
    }

}
