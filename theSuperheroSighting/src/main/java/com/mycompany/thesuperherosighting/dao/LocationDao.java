/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.dao;

import com.mycompany.thesuperherosighting.model.Location;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface LocationDao {
      //locationDao 
    public void addLocation(Location location);

    public void deleteLocation(int locationId);

    public void updateLocation(Location location);

    public Location getLocationById(int locationId);

    public List<Location> getAllLocations(); 
}
