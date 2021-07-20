package com.rentalcar.webapp.controller;

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

    @GetMapping(value = {"/", "/login"})
    public String loginForm(){
        return "login";
    }

    /*@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }*/

    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPage() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication.getPrincipal() == "anonymousUser") {
            return "login";
        } else {
            return "redirect:/utente/lista-customers";
        }
    }*/

    @RequestMapping(value = "/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            /*persistentTokenBasedRememberMeServices.logout(request, response, auth);*/
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/redirectHomepage")
    public String userForm(Authentication authentication){
        if (authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")){
            return "redirect:/utente/lista-customers";
        }
        else {
            return "redirect:/prenotazioni/visualizza/1";
        }
    }

}
