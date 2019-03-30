/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.dao;

import com.mycompany.thesuperherosighting.model.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sonia
 */
public class LocationJdbcDaoImpl implements LocationDao{
  //Location prepared statement
    private static final String SQL_INSERT_LOCATION = "insert into location "
            + "(loc_name, loc_descreption, loc_address, loc_city, loc_state,loc_zipcode,longitude ,latitude) "
            + "values (?,?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_LOCATION_BY_ID = "select * from location where location_id = ?";
    private static final String SQL_SELECT_ALL_LOCATIONS = "select * from location";
    
    private static final String SQL_UPDATE_LOCATION_BY_ID = "update location "
            + "set loc_name = ?, loc_descreption = ?, loc_address = ?, loc_city = ?, loc_state = ?, loc_zipcode = ?, latitude = ?, longitude = ? "
            + "where location_id = ?";
    private static final String SQL_DELETE_LOCATION_BY_ID = "delete from location where location_id = ?";
    
   
    
    
    //setup the jdbcTemplate
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate( JdbcTemplate jdbcTemplate)  {
     this.jdbcTemplate=  jdbcTemplate;
    }       
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
               
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                    location.getLocName(),
                    location.getLocDescription(),
                    location.getStreet(),
                    location.getCity(),
                    location.getState(),
                    location.getZipCode(),
                    location.getLongitude(),
                    location.getLatitude()
                    
            );
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
            location.setLocationId(id);
        
    }

    
    @Override
    public void deleteLocation(int locationId) {
      //delete first from Sighting table
     // jdbcTemplate.update(SQL_DELETE_SIGHTINGS_BY_LOCATION_ID, locationId); 
      jdbcTemplate.update(SQL_DELETE_LOCATION_BY_ID, locationId);  
    }

    @Override
    public void updateLocation(Location location) {
      jdbcTemplate.update(SQL_UPDATE_LOCATION_BY_ID,
              location.getLocName(),
              location.getLocDescription(),
              location.getStreet(),
              location.getCity(),
              location.getState(),
              location.getZipCode(),
              location.getLatitude(),
              location.getLongitude(),
              location.getLocationId());
    }

    @Override
    public Location getLocationById(int locationId) {
       try {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ID, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
       return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS , new LocationMapper());
       
    }

    //location mapper
    
    private static final class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("location_id"));
            location.setLocName(rs.getString("loc_name"));
            location.setLocDescription(rs.getString("loc_descreption"));
            location.setStreet(rs.getString("loc_address"));
            location.setCity(rs.getString("loc_city"));
            location.setState(rs.getString("loc_state"));
            location.setZipCode(rs.getString("loc_zipcode"));
            location.setLatitude(rs.getBigDecimal("latitude"));
            location.setLongitude(rs.getBigDecimal("longitude"));
            return location;
        }
    }
  
}
