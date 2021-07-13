package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.entity.Prenotazioni;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PrenotazioniDaoImpl implements PrenotazioniDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Prenotazioni> getPrenotazioniByUser(Long id) {
        TypedQuery<Prenotazioni> query = sessionFactory.getCurrentSession().createQuery("FROM Prenotazioni WHERE utente = '" + id + "' ORDER BY startdate");
        return query.getResultList();
    }
}
