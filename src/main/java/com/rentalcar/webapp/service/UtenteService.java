package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Utente;

import java.util.List;

public interface UtenteService {

    List<Utente> getAllCustomers();

    List<Utente> getAllUtenti();

    Utente getCustomer(Long id);

    List<Utente> searchCustomers(String theSearchName);

    void save(Utente theCustomer);

    void update(Utente theCustomer);

    void delete(Long id);

}
