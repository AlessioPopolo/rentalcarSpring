package com.rentalcar.webapp.service;

import com.rentalcar.webapp.dao.PrenotazioniDao;
import com.rentalcar.webapp.entity.Prenotazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("PrenotazioniService")
@Transactional
public class PrenotazioniServiceImpl implements PrenotazioniService {

    @Autowired
    private PrenotazioniDao prenotazioniDao;

    @Override
    public List<Prenotazioni> getPrenotazioniByUser(Long id) {
        return prenotazioniDao.getPrenotazioniByUser(id);
    }

    @Override
    public void save(Prenotazioni prenotazione) {
        prenotazioniDao.save(prenotazione);
    }

    @Override
    public void update(Prenotazioni prenotazione) {
        prenotazioniDao.update(prenotazione);
    }

    @Override
    public void delete(Long id) {
        prenotazioniDao.delete(id);
    }

    @Override
    public Prenotazioni getPrenotazione(Long id) {
        return prenotazioniDao.getPrenotazione(id);
    }
}
