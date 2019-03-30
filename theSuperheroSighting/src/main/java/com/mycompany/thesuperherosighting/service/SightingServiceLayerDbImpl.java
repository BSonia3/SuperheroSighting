/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.dao.SightingDao;
import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.model.Sighting;
import com.mycompany.thesuperherosighting.model.Superhero;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author sonia
 */
public class SightingServiceLayerDbImpl implements SightingServiceLayer {
   SightingDao dao;
     @Inject
     SightingServiceLayerDbImpl(SightingDao dao){
         this.dao=dao;
     }
     
    @Override
    public void addSighting(Sighting sighting) {
       dao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
       dao.deleteSighting(sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
       dao.updateSighting(sighting);
    }

    @Override
    public Sighting getSighting(int sightingId) {
        return dao.getSighting(sightingId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        return dao.getSightingsByDate(date);
    }

    @Override
    public List<Superhero> getAllSuperherosByLocation(int locationId) {
        return dao.getAllSuperherosByLocation(locationId);
    }

    @Override
    public List<Location> getAllLocsByHero(int heroId) {
        return dao.getAllLocsByHero(heroId);
    }
    @Override
    public List<Sighting> getAllSightingsOrdredByLast10(){
       return dao.getLast10SightingsByDate();
    } 
}
