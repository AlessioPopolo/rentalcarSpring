package com.rentalcar.webapp.service;

import com.rentalcar.webapp.dao.PrenotazioniDao;
import com.rentalcar.webapp.entity.Prenotazioni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
    public List<Prenotazioni> getAllPrenotazioni() {
        return prenotazioniDao.getAllPrenotazioni();
    }

    @Override
    public void save(Prenotazioni prenotazione) {
        prenotazioniDao.save(prenotazione);
    }

    @Override
    public void update(Prenotazioni prenotazione) {
        if (checkDatePrenotazione(prenotazione.getStartdate())){
            prenotazioniDao.update(prenotazione);
        }
    }

    @Override
    public void delete(Long id) {
        if(checkDatePrenotazione(this.getPrenotazione(id).getStartdate())){
            prenotazioniDao.delete(id);
        }
    }

    @Override
    public Prenotazioni getPrenotazione(Long id) {
        return prenotazioniDao.getPrenotazione(id);
    }

    @Override
    public boolean checkDatePrenotazione(Date start) {
        LocalDateTime ldt = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()).minusDays(2);
        if (ldt.isAfter(LocalDateTime.now())){ return true;}
        else {return false;}
    }


}
