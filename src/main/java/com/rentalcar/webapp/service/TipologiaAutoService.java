package com.rentalcar.webapp.service;

import com.rentalcar.webapp.entity.TipologiaAutomezzo;

import java.util.List;

public interface TipologiaAutoService {

    List<TipologiaAutomezzo> getAllTipologie();

    TipologiaAutomezzo getCategoria(String categoria);

}
