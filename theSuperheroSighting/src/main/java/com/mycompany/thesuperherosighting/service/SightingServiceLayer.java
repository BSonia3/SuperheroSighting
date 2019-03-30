/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.model.Sighting;
import com.mycompany.thesuperherosighting.model.Superhero;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface SightingServiceLayer {
   public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingId);
    
    public void updateSighting(Sighting sighting);

    Sighting getSighting(int sightingId);
    
    List<Sighting> getAllSightings();
    
    List<Sighting> getSightingsByDate(LocalDate date);

    public List<Superhero> getAllSuperherosByLocation(int locationId);
   
    public List<Location> getAllLocsByHero(int heroId);
    
    List<Sighting> getAllSightingsOrdredByLast10(); 
}
