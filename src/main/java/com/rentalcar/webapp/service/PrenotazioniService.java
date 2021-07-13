package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.entity.Utente;

import java.util.List;

public interface PrenotazioniService {

    List<Prenotazioni> getPrenotazioniByUser(Long id);

    void save(Prenotazioni prenotazione);

    void update(Prenotazioni prenotazione);

    void delete(Long id);

    Prenotazioni getPrenotazione(Long id);
}
