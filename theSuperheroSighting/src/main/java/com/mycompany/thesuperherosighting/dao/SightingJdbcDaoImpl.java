/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.dao;

import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.model.Sighting;
import com.mycompany.thesuperherosighting.model.Superhero;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class SightingJdbcDaoImpl implements SightingDao {
  private static final String SQL_INSERT_SIGHTING = "insert into sighting "
            + "(sighting_date,location_id ) values (?,?)";
    private static final String SQL_UPDATE_SIGHTING_BY_ID = "update sighting set sighting_date = ?,location_id=?"
            + " where sighting_id = ?";
    private static final String SQL_DELETE_SIGHTING_BY_ID = "delete from sighting where sighting_id = ?";
    private static final String SQL_SELECT_SIGHTING_BY_ID ="select * from sighting where sighting_id=?";
    private static final String SQL_SELECT_ALL_SIGHTINGS ="select * from sighting"; 
    private static final String SQL_SELECT_SIGHTINGS_BY_DATE =
            "select superhero.*, location.*, sighting.sighting_id, sighting.sighting_date from location" +
            " inner join sighting on location.location_id = sighting.location_id" +
            " inner join superhero_sighting on superhero_sighting.sighting_id = sighting.sighting_id" +
            " inner join superhero on superhero.superhero_id=superhero_sighting.superhero_id"+
            " where sighting.sighting_date = ?";
   
    private static final String SQL_SELECT_SIGHTINGS_BY_LOCATION_ID =
            "select h.superhero_id, h.superhero_name, h.superhero_descreption, h.superpower_id, location.* from location" +
            " inner join sighting on location.location_id = sighting.location_id" +
            " inner join superhero_sighting on superhero_sighting.superhero_id "
            + "inner join superhero h on h.superhero_id = superhero_sighting.superhero_id" +
            " where location.location_id= ? "
            + "Group By h.superhero_id, h.superhero_name, h.superhero_descreption, h.superpower_id";
    
    private static final String SQL_SELECT_LOCS_BY_HERO_ID
           = "select l.location_id , l.loc_name, l.loc_descreption, l.loc_address, l.loc_city, l.loc_state,l.loc_zipcode,l.longitude ,l.latitude from location l" +
            " inner join sighting on l.location_id = sighting.location_id" +
            " inner join superhero_sighting on superhero_sighting.superhero_id "
            + "inner join superhero on superhero.superhero_id = superhero_sighting.superhero_id"+
            " where superhero.superhero_id = ?"
            + " Group By l.location_id , l.loc_name, l.loc_descreption, l.loc_address, l.loc_city, l.loc_state,l.loc_zipcode,l.longitude ,l.latitude";
    
    //Superhero-sighting prepared statement 
    private static final String SQL_INSERT_HERO_SIGHTING
            = "insert into superhero_sighting (superhero_id,sighting_id)"
            + " values(?, ?)";
    private static final String SQL_DELETE_HEROS_SIGHTING
            = "delete from superhero_sighting where sighting_id = ?";
    private static final String SQL_SELECT_HEROS_BY_SIGHTING_ID
            = "select h.superhero_id, h.superhero_name, h.superhero_descreption, h.superpower_id "
            + "from superhero h join superhero_sighting hs on sighting_id "
            + "where h.superhero_id = hs.superhero_id "
            + "and hs.sighting_id  = ?";
    
    //select location by sightingId
    private static final String SQL_SELECT_LOC_BY_SIGHT_ID
            = "select l.location_id, l.loc_name, l.loc_descreption, l.loc_address,"
            + "l.loc_city, l.loc_state,l.loc_zipcode,l.longitude ,l.latitude "
            + "from location l "
            + "join sighting on l.location_id = sighting.location_id where "
            + "sighting.sighting_id = ?";
   
    // select last 10 sighting 
    
    private static final String SQL_SELECT_10_LAST_SIGHTINGS_ORDER_BY_DATE =
            " select * from sighting"+
            " order by sighting.sighting_date desc limit 0,10";
    JdbcTemplate jdbcTemplate;
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,

                 sighting.getSightingDate().toString(),
                 sighting.getLocation().getLocationId());
       int sightingId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                        Integer.class);
        sighting.setSightingId(sightingId);
        //update superhero_sighting table  
        insertSuperheroSighting(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void deleteSighting(int sightingId) {
    jdbcTemplate.update(SQL_DELETE_HEROS_SIGHTING,sightingId);
    jdbcTemplate.update( SQL_DELETE_SIGHTING_BY_ID,sightingId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING_BY_ID,
                 sighting.getSightingDate().toString(),
                 sighting.getLocation().getLocationId(),
                 sighting.getSightingId()); 
        jdbcTemplate.update(SQL_DELETE_HEROS_SIGHTING,sighting.getSightingId());
        insertSuperheroSighting(sighting);
    }

    @Override
    public Sighting getSighting(int sightingId) {
       try {
            // get the properties from the books table
           Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING_BY_ID, 
                                                    new SightingMapper(), 
                                                    sightingId);
            // get the Authors for this book and set list on the book
           sighting.setHeros(findHerosForSighting(sighting));
            // get the Publisher for this book
            sighting.setLocation(findLocationForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        // get all the sights
       List<Sighting> sightingList = 
            jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, 
                               new SightingMapper());
        // set the loc and list of heros for each sight
        return associateLocationAndHerosWithsightings(sightingList);
        
     
    }
    
    @Override
    public List<Superhero> getAllSuperherosByLocation(int locationId) {
      List<Superhero> heroList = 
                jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_LOCATION_ID, 
                                   new SuperheroMapper(), 
                                   locationId);
        // set the powers and orgs for hero
        return heroList;  
    }
       
    
    @Override
    public List<Location> getAllLocsByHero(int heroId) {
        // heroId=hero.getSuperheroId();
       List<Location> locList = 
            jdbcTemplate.query(SQL_SELECT_LOCS_BY_HERO_ID, 
                               new LocationMapper(),heroId);
        
       return locList;
    }
    @Override
    public List<Sighting> getSightingsByDate(LocalDate date){
        List<Sighting> sightingList = 
            jdbcTemplate.query(SQL_SELECT_SIGHTINGS_BY_DATE, 
                               new SightingMapper(),date.toString());
        sightingList=associateLocationAndHerosWithsightings(sightingList);
      return sightingList;
         
    }
    
    @Override
    public List<Sighting> getLast10SightingsByDate() {
      // get all the sights
       List<Sighting> sightingList = 
            jdbcTemplate.query(SQL_SELECT_10_LAST_SIGHTINGS_ORDER_BY_DATE, 
                               new SightingMapper());
        // set the loc and list of heros for each sight
        return associateLocationAndHerosWithsightings(sightingList);
        
    
    }
    
    
    //helperMapper
    private void insertSuperheroSighting(Sighting sighting) {
    final int sightingId = sighting.getSightingId();
    final List<Superhero> heros = sighting.getHeros();

    // Update the SuperheroSighting bridge table with an entry for 
    // each hero of this sighting
    for (Superhero currentHero : heros) {
         jdbcTemplate.update(SQL_INSERT_HERO_SIGHTING,
                currentHero.getSuperheroId(),
                sightingId);
                    
         int id =  currentHero.getSuperheroId();
         int id2=sightingId;
     } 
      
      //return sighting;
    }
    
    private List<Superhero> findHerosForSighting(Sighting sighting){
       return jdbcTemplate.query(SQL_SELECT_HEROS_BY_SIGHTING_ID, 
                                  new SuperheroMapper(), 
                                  sighting.getSightingId());  
    }
    
    private Location findLocationForSighting(Sighting sighting){
        return jdbcTemplate.queryForObject(SQL_SELECT_LOC_BY_SIGHT_ID, 
                                           new LocationMapper(), 
                                           sighting.getSightingId());
    }
    
    private List<Sighting> associateLocationAndHerosWithsightings(List<Sighting> sightingList) {
        // set the complete list of hero ids for each sight
        for (Sighting currentSighting : sightingList) {
            // add heros to current sight
            currentSighting.setHeros(findHerosForSighting(currentSighting));
            // add the loc to current sight
            currentSighting.setLocation(
                findLocationForSighting(currentSighting));
        }
        return sightingList;
    } 

    

    // mappers
    
   // before implementing the methods,add row mapper which convert a row in table to an object 
    private static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
           Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sighting_id"));
            s.setSightingDate(rs.getTimestamp("sighting_date").
                              toLocalDateTime().toLocalDate());
   
            return s; 
        }
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
    //superhero mapper
    private static final class SuperheroMapper implements RowMapper<Superhero> {
        @Override
        public Superhero mapRow(ResultSet rs, int i) throws SQLException {
            Superhero superhero = new Superhero();
            superhero.setSuperheroId(rs.getInt("superhero_id"));
            superhero.setName(rs.getString("superhero_Name"));
            superhero.setDescription(rs.getString("superhero_descreption"));
   
            return superhero; 
        }
    }
    
    

     
}
