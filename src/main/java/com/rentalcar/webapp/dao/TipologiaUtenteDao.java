package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.TipologiaUtente;

import java.util.List;

public interface TipologiaUtenteDao {

    List<TipologiaUtente> getAllTipologie();

    TipologiaUtente getRuolo(String mioRuolo);

}
