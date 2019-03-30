/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.dao.LocationDao;
import com.mycompany.thesuperherosighting.model.Location;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author sonia
 */
public class LocationServiceLayerDbImpl implements LocationServiceLayer{
    LocationDao dao;
    
    @Inject
     LocationServiceLayerDbImpl( LocationDao dao){
       this.dao = dao; 
     } 
   
    @Override
    public void addLocation(Location location) {
       dao.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) { 
       dao.deleteLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) {
       dao.updateLocation(location);
    }

    @Override
    public Location getLocationById(int locationId) {
        return dao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
       return dao.getAllLocations();
    }

}
