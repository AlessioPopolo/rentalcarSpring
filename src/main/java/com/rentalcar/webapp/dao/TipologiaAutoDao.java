package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.TipologiaAutomezzo;

import java.util.List;

public interface TipologiaAutoDao {

    List<TipologiaAutomezzo> getAllTipologie();

    TipologiaAutomezzo getCategoria(String categoria);

}
