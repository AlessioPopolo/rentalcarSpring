package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.TipologiaAutomezzo;
import com.rentalcar.webapp.entity.TipologiaUtente;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TipologiaAutoDaoImpl implements TipologiaAutoDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<TipologiaAutomezzo> getAllTipologie() {
        return null;
    }

    @Override
    public TipologiaAutomezzo getCategoria(String categoria) {
        return null;
    }
}
