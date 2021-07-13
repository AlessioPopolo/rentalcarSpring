package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.entity.Prenotazioni;
import com.rentalcar.webapp.entity.Utente;
import org.hibernate.Session;
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

    @Override
    public void save(Prenotazioni prenotazione) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(prenotazione);
        session.flush();
    }

    @Override
    public void update(Prenotazioni prenotazione) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(prenotazione);
        session.flush();
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Prenotazioni prenotazione = session.load(Prenotazioni.class,id);
        session.delete(prenotazione);
        //This makes the pending delete to be done
        session.flush() ;
    }

    @Override
    public Prenotazioni getPrenotazione(Long id) {
        TypedQuery<Prenotazioni> query = sessionFactory.getCurrentSession().createQuery("FROM Prenotazioni WHERE id = '" + id + "'");
        return query.getSingleResult();
    }
}
