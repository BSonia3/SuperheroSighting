/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.dao.SuperheroDao;
import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.model.Organisation;
import com.mycompany.thesuperherosighting.model.Superhero;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author sonia
 */
public class SuperheroServiceLayerDbImpl implements SuperheroServiceLayer{
 
    SuperheroDao dao;
    
    @Inject
    
    SuperheroServiceLayerDbImpl(SuperheroDao dao){
       this.dao=dao; 
    }

    @Override
    public void addSuperhero(Superhero superhero) {
      dao.addSuperhero(superhero);
    }

    @Override
    public void deleteSuperhero(int superheroId) {
    dao.deleteSuperhero(superheroId);
    }

    @Override
    public void updateSuperhero(Superhero superhero) {
       dao.updateSuperhero(superhero);
    }

    @Override
    public Superhero getSuperheroById(int superheroId) {
       return dao.getSuperheroById(superheroId);
    }

    @Override
    public List<Superhero> getAllSuperheros() {
      return dao.getAllSuperheros();
    }

    @Override
    public List<Superhero> getAllSuperherosByOrganisation(int organisationId) {
       return dao.getAllSuperherosByOrganisation(organisationId);
    }

    @Override
    public List<Organisation> getAllOrganisationsByHero(int heroId) {
        return dao.getAllOrganisationsByHero(heroId);
    }

    @Override
    public List<Superhero> getAllSuperherosByLocation(Location location) {
       return dao.getAllSuperherosByLocation(location);
      
}
}

