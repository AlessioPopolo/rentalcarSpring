package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.Utente;
import org.hibernate.SessionFactory;
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
        return null;
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

    }

    @Override
    public void update(Utente theCustomer) {

    }

    @Override
    public void delete(Long id) {

    }
}
