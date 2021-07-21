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
        if (checkDataEndAfterDataStart(prenotazione) && checkAvailableVehicleInDatePrenotazione(prenotazione)){
            prenotazioniDao.save(prenotazione);
        }
    }

    @Override
    public void update(Prenotazioni prenotazione) {
        if (checkEditableOrDeletableBeforeXDaysPrenotazione(prenotazione.getStartdate()) && checkDataEndAfterDataStart(prenotazione) && checkAvailableVehicleInDatePrenotazione(prenotazione)){
            prenotazione.setApproved(false);
            prenotazioniDao.update(prenotazione);
        }
    }

    @Override
    public void delete(Long id) {
        if(checkEditableOrDeletableBeforeXDaysPrenotazione(this.getPrenotazione(id).getStartdate())){
            prenotazioniDao.delete(id);
        }
    }

    @Override
    public void approve(Prenotazioni prenotazione) {
        if (checkAvailableVehicleInDatePrenotazione(prenotazione)){
            prenotazioniDao.approve(prenotazione);
        }
    }

    @Override
    public Prenotazioni getPrenotazione(Long id) {
        return prenotazioniDao.getPrenotazione(id);
    }

    @Override
    public boolean checkEditableOrDeletableBeforeXDaysPrenotazione(Date start) {
        LocalDateTime ldt = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()).minusDays(2);
        if (ldt.isAfter(LocalDateTime.now())){ return true;}
        else {return false;}
    }

    @Override
    public boolean checkAvailableVehicleInDatePrenotazione(Prenotazioni prenotazione) {
        return prenotazioniDao.checkPrenotazioniSameDate(prenotazione.getId(), prenotazione.getAutomezzo().getId(), prenotazione.getStartdate(), prenotazione.getEnddate());
    }

    @Override
    public boolean checkDataEndAfterDataStart(Prenotazioni prenotazione) {
        LocalDateTime ldtStart = LocalDateTime.ofInstant(prenotazione.getStartdate().toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtEnd = LocalDateTime.ofInstant(prenotazione.getEnddate().toInstant(), ZoneId.systemDefault());
        if (ldtEnd.isAfter(ldtStart)){
            return true;
        }
        return false;
    }


}
