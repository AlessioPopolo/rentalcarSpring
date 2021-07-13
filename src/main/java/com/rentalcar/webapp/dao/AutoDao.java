package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.Automezzo;

import java.util.List;

public interface AutoDao {

    List<Automezzo> getAllAutomezzi();

    List<Automezzo> getAllAutoFromCategoria(String categoria);

    Automezzo getAutomezzo(Long id);

    void save(Automezzo automezzo);

    void update(Automezzo automezzo);

    void delete(Long id);

}
