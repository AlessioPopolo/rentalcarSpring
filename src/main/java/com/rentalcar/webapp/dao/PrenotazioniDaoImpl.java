package com.rentalcar.webapp.dao;

import com.rentalcar.webapp.entity.Prenotazioni;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Date;
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
        session.merge(prenotazione);
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
    public void approve(Prenotazioni prenotazione) {
        Session session = this.sessionFactory.getCurrentSession();
        prenotazione.setApproved(true);
        session.update(prenotazione);
        session.flush();
    }

    @Override
    public Prenotazioni getPrenotazione(Long id) {
        TypedQuery<Prenotazioni> query = sessionFactory.getCurrentSession().createQuery("FROM Prenotazioni WHERE id = '" + id + "'");
        return query.getSingleResult();
    }

    @Override
    public List<Prenotazioni> getAllPrenotazioni() {
        TypedQuery<Prenotazioni> query = sessionFactory.getCurrentSession().createQuery("FROM Prenotazioni ORDER BY startdate");
        return query.getResultList();
    }

    @Override
    public boolean checkPrenotazioniSameDate(Long idPrenotazione, Long idAuto, Date start, Date end) {
        TypedQuery<Prenotazioni> query;
        query = sessionFactory.getCurrentSession()
                .createQuery("FROM Prenotazioni WHERE id NOT IN (FROM Prenotazioni where (startdate < '" + start + "' AND enddate > '" + start + "') OR (startdate < '" + end + "' AND enddate > '" + end + "') OR ('" + start + "' <= startdate AND '" + end + "' >= startdate))");
        TypedQuery<Prenotazioni> query1 = sessionFactory.getCurrentSession()
                .createQuery("FROM Prenotazioni WHERE ((startdate BETWEEN '" + start + "' AND '" + end + "' ) OR (enddate BETWEEN '" + start + "' AND '" + end + "')) AND approved = true AND automezzo.id = '" + idAuto + "'");
        List<Prenotazioni> p = query1.getResultList();
        //SE VUOTO È SEMPRE POSSIBILE INSERIRE (CASI ADD E APPROVE O UPDATE SE NON ANCORA APPROVATA)
        if (query1.getResultList().isEmpty()){
            return true;
        }
        //SE C'È UNA TUPLA SOLTANTO, CONTROLLO SE È UNA UPDATE SU QUELL'ID
        if (query1.getResultList().size()==1 && query1.getSingleResult().getId() == idPrenotazione){
            return true;
        }
        //SE NON CADO NEI 2 IF SOPRA, ALLORA FALSE
        return false;
    }
}