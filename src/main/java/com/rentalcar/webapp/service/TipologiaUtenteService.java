package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.TipologiaUtente;

import java.util.List;

public interface TipologiaUtenteService {

    List<TipologiaUtente> getAllTipologie();

    TipologiaUtente getRuolo(String mioRuolo);

}
