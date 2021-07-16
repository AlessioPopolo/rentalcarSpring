package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.TipologiaUtente;
import com.rentalcar.webapp.entity.Utente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UtenteDaoImpl implements UtenteDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Utente> getAllCustomers() {
        TypedQuery<Utente> query = sessionFactory.getCurrentSession().createQuery("FROM Utente WHERE ruolo.ruolo='customer' ORDER BY id");
        return query.getResultList();
    }

    @Override
    public List<Utente> getAllUtenti() {
        TypedQuery<Utente> query = sessionFactory.getCurrentSession().createQuery("FROM Utente ORDER BY id");
        return query.getResultList();
    }

    @Override
    public Utente getCustomer(Long id) {
        TypedQuery<Utente> query = sessionFactory.getCurrentSession().createQuery("FROM Utente WHERE id = '" + id + "'");
        return query.getSingleResult();
    }

    @Override
    public List<Utente> searchCustomers(String theSearchName) {
        TypedQuery<Utente> query;
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            query = sessionFactory.getCurrentSession().createQuery("FROM Utente WHERE (nome like '%" + theSearchName + "%' OR cognome like '%" + theSearchName + "%') AND ruolo.ruolo = 'customer' ORDER BY id");
        }
        else {
            query = sessionFactory.getCurrentSession().createQuery("FROM Utente WHERE ruolo.ruolo='customer' ORDER BY id");
        }
        return query.getResultList();
    }

    @Override
    public void save(Utente theCustomer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(theCustomer);
        session.flush();
    }

    @Override
    public void update(Utente theCustomer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(theCustomer);
        session.flush();
    }

    @Override
    public void delete(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Utente utente = session.load(Utente.class,id);
        session.delete(utente);
        //This makes the pending delete to be done
        session.flush() ;
    }

    @Override
    public Utente findUserByStringId(String id) {
        Long longId = Long.parseLong(id);
        TypedQuery<Utente> query = sessionFactory.getCurrentSession().createQuery("FROM Utente WHERE id = '" + longId + "'");
        return query.getSingleResult();
    }
}
