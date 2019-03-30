/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.dao;

import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.model.Organisation;
import com.mycompany.thesuperherosighting.model.Superhero;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface SuperheroDao {
    
    // superheroDao
    public void addSuperhero(Superhero superhero);

    public void deleteSuperhero(int superheroId);

    public void updateSuperhero(Superhero superhero);

    public Superhero getSuperheroById(int superheroId);

    public List<Superhero> getAllSuperheros();
    
    public List<Superhero> getAllSuperherosByOrganisation(int organisationId);
    
    public List<Organisation> getAllOrganisationsByHero(int heroId);
    
    public List<Superhero> getAllSuperherosByLocation(Location location);
}
