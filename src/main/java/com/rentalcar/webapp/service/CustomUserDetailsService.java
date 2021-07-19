package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Utente;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UtenteService utenteService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
        Utente utente = utenteService.findUserBySSO(ssoId);
        logger.debug("User : {}", utente);
        if(utente==null){
            logger.debug("Utente non trovato");
            throw new UsernameNotFoundException("Utente non trovato");
        }
        return new org.springframework.security.core.userdetails.User(utente.getSsoId(), utente.getPassword(),
                true, true, true, true, getGrantedAuthorities(utente));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Utente utente){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        String ruolo = utente.getRuolo().getRuolo();

        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        logger.debug("authorities : {}", authorities);
        return authorities;
    }
}
