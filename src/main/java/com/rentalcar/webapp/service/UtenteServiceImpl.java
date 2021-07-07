package com.rentalcar.webapp.service;

import com.rentalcar.webapp.dao.UtenteDao;
import com.rentalcar.webapp.entity.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("UtenteService")
@Transactional
public class UtenteServiceImpl implements UtenteService{

    @Autowired
    private UtenteDao utenteDao;

    @Override
    public List<Utente> getAllCustomers() {
        return utenteDao.getAllCustomers();
    }

    @Override
    public List<Utente> getAllUtenti() {
        return utenteDao.getAllUtenti();
    }

    @Override
    public Utente getCustomer(Long id) {
        return utenteDao.getCustomer(id);
    }

    @Override
    public List<Utente> searchCustomers(String theSearchName) {
        return utenteDao.searchCustomers(theSearchName);
    }

    @Override
    public void save(Utente theCustomer) {
        utenteDao.save(theCustomer);
    }

    @Override
    public void update(Utente theCustomer) {
        utenteDao.update(theCustomer);
    }

    @Override
    public void delete(Long id) {
        utenteDao.delete(id);
    }
}
