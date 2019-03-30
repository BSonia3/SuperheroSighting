/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.dao.SuperpowerDao;
import com.mycompany.thesuperherosighting.model.Superpower;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author sonia
 */
public class  SuperpowerServiceLayerDbImpl implements SuperpowerServiceLayer {
  SuperpowerDao dao;
    
    @Inject
   SuperpowerServiceLayerDbImpl(SuperpowerDao dao){
     this.dao = dao;   
    }
    @Override
    public void addSuperpower(Superpower superpower) {
        dao.addSuperpower(superpower);
    }

    @Override
    public void deleteSuperpower(int superpowerId) {
        dao.deleteSuperpower(superpowerId);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        dao.updateSuperpower(superpower);
    }

    @Override
    public Superpower getSuperpower(int id) {
      return dao.getSuperpower(id);
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
       return dao.getAllSuperpowers();
    }  
}
