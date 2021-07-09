package com.rentalcar.webapp.service;

import com.rentalcar.webapp.dao.TipologiaUtenteDao;
import com.rentalcar.webapp.dao.UtenteDao;
import com.rentalcar.webapp.entity.TipologiaUtente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("TipologiaUtenteService")
@Transactional
public class TipologiaUtenteServiceImpl implements TipologiaUtenteService{

    @Autowired
    private TipologiaUtenteDao tipologiaUtenteDao;

    @Override
    public List<TipologiaUtente> getAllTipologie() {
        return tipologiaUtenteDao.getAllTipologie();
    }

    @Override
    public TipologiaUtente getRuolo(String mioRuolo) {
        return tipologiaUtenteDao.getRuolo(mioRuolo);
    }
}
