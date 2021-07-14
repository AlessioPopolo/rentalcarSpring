package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.Prenotazioni;

import java.util.Date;
import java.util.List;

public interface PrenotazioniDao {
    List<Prenotazioni> getPrenotazioniByUser(Long id);

    void save(Prenotazioni prenotazione);

    void update(Prenotazioni prenotazione);

    void delete(Long id);

    Prenotazioni getPrenotazione(Long id);

    List<Prenotazioni> getAllPrenotazioni();

    boolean checkPrenotazioniSameDate(Long idPrenotazione, Long idAuto, Date start, Date end);
}
