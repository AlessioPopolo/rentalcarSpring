package com.rentalcar.webapp.service;

import com.rentalcar.webapp.dao.AutoDao;
import com.rentalcar.webapp.dao.UtenteDao;
import com.rentalcar.webapp.entity.Automezzo;
import com.rentalcar.webapp.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("AutoService")
@Transactional
public class AutoServiceImpl implements AutoService{

    @Autowired
    private AutoDao autoDao;


    @Override
    public List<Automezzo> getAllAutomezzi() {
        return autoDao.getAllAutomezzi();
    }

    @Override
    public List<Automezzo> getAllAutoFromCategoria(String categoria) {
        return autoDao.getAllAutoFromCategoria(categoria);
    }

    @Override
    public Automezzo getAutomezzo(Long id) {
        return autoDao.getAutomezzo(id);
    }

    @Override
    public void save(Automezzo automezzo) {
        autoDao.save(automezzo);
    }

    @Override
    public void update(Automezzo automezzo) {
        autoDao.update(automezzo);
    }

    @Override
    public void delete(Long id) {
        autoDao.delete(id);
    }
}
