package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.Prenotazioni;

import java.util.List;

public interface PrenotazioniDao {
    List<Prenotazioni> getPrenotazioniByUser(Long id);
}
