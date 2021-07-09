package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.TipologiaUtente;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TipologiaUtenteDaoImpl implements TipologiaUtenteDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TipologiaUtente> getAllTipologie() {
        TypedQuery<TipologiaUtente> query = sessionFactory.getCurrentSession().createQuery("FROM TipologiaUtente ORDER BY id");
        return query.getResultList();
    }

    @Override
    public TipologiaUtente getRuolo(String mioRuolo) {
        return null;
    }
}
