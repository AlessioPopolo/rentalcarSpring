package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.entity.Utente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AutoDaoImpl implements AutoDao{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Automezzo> getAllAutomezzi() {
        TypedQuery<Automezzo> query = sessionFactory.getCurrentSession().createQuery("FROM Automezzo ORDER BY id");
        return query.getResultList();
    }

    @Override
    public List<Automezzo> getAllAutoFromCategoria(String categoria) {
        return null;
    }

    @Override
    public Automezzo getAutomezzo(Long id) {
        return null;
    }

    @Override
    public void save(Automezzo automezzo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(automezzo);
        session.flush();
    }

    @Override
    public void update(Automezzo automezzo) {

    }

    @Override
    public void delete(Long id) {

    }
}
