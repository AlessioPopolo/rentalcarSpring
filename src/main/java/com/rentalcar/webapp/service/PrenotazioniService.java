package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.entity.Utente;

import java.util.Date;
import java.util.List;

public interface PrenotazioniService {

    List<Prenotazioni> getPrenotazioniByUser(Long id);

    List<Prenotazioni> getAllPrenotazioni();

    void save(Prenotazioni prenotazione);

    void update(Prenotazioni prenotazione);

    void delete(Long id);

    Prenotazioni getPrenotazione(Long id);

    boolean checkDatePrenotazione(Date start);
}
