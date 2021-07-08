package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.config.HibernateUtil;
import com.rentalcar.webapp.entity.Utente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        return null;
    }

    @Override
    public List<Utente> searchCustomers(String theSearchName) {
        return null;
    }

    @Override
    public void save(Utente theCustomer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(theCustomer);
    }

    @Override
    public void update(Utente theCustomer) {

    }

    @Override
    public void delete(Long id) {

    }
}
