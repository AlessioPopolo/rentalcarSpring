package com.rentalcar.webapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    private static final String[] ADMIN_MATCHER = {"/utente/lista-customers", "/prenotazioni/listaAllPrenotazioni",
            "/utente/addCustomer", "/utente/delete/", "/auto/addAuto", "/auto/updateAuto/",
            "/auto/delete/", "/prenotazioni/approve/" };

    private static final String[] USER_MATCHER = {"/prenotazioni/addPrenotazione/**"};

    @Override
    protected void configure(final HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/prenotazioni/visualizza/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers(ADMIN_MATCHER).access("hasRole('ADMIN')")
                    .antMatchers(USER_MATCHER).access("hasRole('USER')")
                .and()
                    .formLogin()
                        .loginPage("/login/")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/redirectHomepage")
                        .failureUrl("/login?error")
                        .usernameParameter("ssoId")
                        .passwordParameter("password")
                .and()
                    .csrf()
                .and()
                    .exceptionHandling()
                        .accessDeniedPage("/accessDenied")
                .and()
                    .logout()
                        .deleteCookies("JSESSIONID")
                        .deleteCookies("remember-me")
                        .logoutUrl("/logout")
        ;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
