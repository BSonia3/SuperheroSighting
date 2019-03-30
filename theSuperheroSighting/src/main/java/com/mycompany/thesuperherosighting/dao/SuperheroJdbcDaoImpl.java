/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.dao;

import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.model.Organisation;
import com.mycompany.thesuperherosighting.model.Superhero;
import com.mycompany.thesuperherosighting.model.Superpower;
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
public class SuperheroJdbcDaoImpl implements SuperheroDao {
   
    //Superhero prepared statement 
    private static final String SQL_INSERT_SUPERHERO
            = "insert into superhero (superhero_name, superhero_descreption,superpower_id  )"
            + " values (?, ?, ?)";
    private static final String SQL_DELETE_SUPERHERO
            = "delete from superhero where superhero_id = ?";
    private static final String SQL_SELECT_SUPERHERO
            = "select * from superhero where superhero_id = ?";
    private static final String SQL_UPDATE_SUPERHERO
            ="update superhero set superhero_name = ?, superhero_descreption = ?,  superpower_id=?"
            + " where superhero_id  = ?";
            
    private static final String SQL_SELECT_ALL_SUPERHEROS
            = "select * from superhero";
   
    private static final String SQL_SELECT_ALL_SUPERHEROS_BY_LOCATION
            = "select * from superhero"
            +"inner join superhero on superhero.superhero_id= superhero_sighting.superhero_id"
            +"inner join sighting on sighting.sighting_id= superhero_sighting.sighting_id"
            +"inner join location on location.location_id= sighting.location_id"
            +"where location.location_id= ?";
   
    //Superhero-Organisation prepared statement 
    private static final String SQL_INSERT_HERO_ORGS
            = "insert into superhero_organisation ( superhero_id, organisation_id) values(?, ?)";
    private static final String SQL_DELETE_HEROS_ORGS
            = "delete from superhero_organisation where superhero_id = ?";
    private static final String SQL_SELECT_HEROS_BY_ORG_ID
            = "select h.superhero_id, h.superhero_name, h.superhero_descreption, h.superpower_id "
            + "from superhero h join superhero_organisation ho on organisation_id "
            + "where h.superhero_id = ho.superhero_id "
            + "and ho.organisation_id  = ?";
    
    //select superpower by superheroId
    private static final String SQL_SELECT_SUPERPOWER_BY_HERO_ID
            = "select p.superpower_id, p.superpower "
            + "from superpower p "
            + "join superhero on p.superpower_id = superhero.superpower_id where "
            + "superhero.superhero_id = ?";
    //select orgs by heroId
    private static final String SQL_SELECT_ORGS_BY_HERO_ID
            = "select o.organisation_id, o.org_name, o.org_address , o.org_city, "
            + "o.org_state, o.org_zipcode, o.org_contact from organisation o "
            + "join superhero_organisation so  on o.organisation_id = so.organisation_id where "
            + "so.superhero_id = ?";
    
    
   
    //set up for setter injection of a JDBC Template
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperhero(Superhero superhero) {
        jdbcTemplate.update(SQL_INSERT_SUPERHERO,
                superhero.getName(),
                superhero.getDescription(),
                superhero.getSuperpower().getSuperpowerId());
        int superheroId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", 
                                         Integer.class);
        superhero.setSuperheroId(superheroId);
        insertSuperheroOrgs(superhero);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperhero(int superheroId) {
       // jdbcTemplate.update(SQL_DELETE_HEROS_SIGHTINGS,superheroId);
        jdbcTemplate.update(SQL_DELETE_HEROS_ORGS,superheroId);
        jdbcTemplate.update(SQL_DELETE_SUPERHERO,superheroId);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperhero(Superhero superhero) {
     
        jdbcTemplate.update(SQL_UPDATE_SUPERHERO,
                 superhero.getName(),
                 superhero.getDescription(),
                 superhero.getSuperpower().getSuperpowerId(),
                  
                 superhero.getSuperheroId()  );

        jdbcTemplate.update(SQL_DELETE_HEROS_ORGS,superhero.getSuperheroId());
        insertSuperheroOrgs(superhero);
    }

    @Override
    public Superhero getSuperheroById(int superheroId) {
        try {
     Superhero hero= jdbcTemplate.queryForObject(SQL_SELECT_SUPERHERO, 
                                           new SuperheroMapper(), 
                                           superheroId);
     // get the orgs for this hero and set list on the hero
     hero.setOrgs(findOrgsForHero(hero));
     // get the power for this hero
     hero.setSuperpower(findSuperpowerForHero(hero));
     return hero;   
    } catch (EmptyResultDataAccessException ex) {
        return null;
    }
    }

    @Override
    public List<Superhero> getAllSuperheros() {
       List<Superhero> heroList = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROS, new SuperheroMapper());
         return associateSuperpowerAndOrganisationsWithSuperheros(heroList);
    }

    @Override
    public List<Superhero> getAllSuperherosByOrganisation(int organisationId) {
       
        List<Superhero> heroList = 
                jdbcTemplate.query(SQL_SELECT_HEROS_BY_ORG_ID, 
                                   new SuperheroMapper(), 
                                   organisationId);
        // set the powers and orgs for hero
        return associateSuperpowerAndOrganisationsWithSuperheros(heroList);
    }

    @Override
    public List<Organisation> getAllOrganisationsByHero(int heroId) {
       List<Organisation>orgList  =
               jdbcTemplate.query(SQL_SELECT_ORGS_BY_HERO_ID,
                            new OrganisationMapper(),
                            heroId);
        return orgList;
    }
    
    @Override
    public List<Superhero> getAllSuperherosByLocation(Location location) {
       List<Superhero> heroList = 
                jdbcTemplate.query(SQL_SELECT_ALL_SUPERHEROS_BY_LOCATION, 
                                   new SuperheroMapper(), 
                                   location.getLocationId());
        // set the powers and orgs for hero
        return associateSuperpowerAndOrganisationsWithSuperheros(heroList);
    
            }
    

  

    
    //helper Mapper
    
    private void insertSuperheroOrgs(Superhero hero) {
    final int heroId = hero.getSuperheroId();
    final List<Organisation> orgs = hero.getOrgs();

    // Update the superhero_aorganisation bridge table with an entry for 
    // each author of this hero
    for (Organisation currentOrg : orgs) {
        jdbcTemplate.update(SQL_INSERT_HERO_ORGS, heroId,
                     currentOrg.getOrganisationId());
     }
    }

    private List<Organisation> findOrgsForHero(Superhero hero) {
    return jdbcTemplate.query(SQL_SELECT_ORGS_BY_HERO_ID, 
                              new OrganisationMapper(), 
                              hero.getSuperheroId());
    }

  private Superpower findSuperpowerForHero(Superhero hero) {
    return jdbcTemplate.queryForObject(SQL_SELECT_SUPERPOWER_BY_HERO_ID, 
                                       new SuperpowerMapper(), 
                                       hero.getSuperheroId());
  }

   private List<Superhero> 
        associateSuperpowerAndOrganisationsWithSuperheros(List<Superhero> heroList) {
    // set the complete list of org ids for each  hero
    for (Superhero currentHero :heroList) {
        // add orgs to current hero
        currentHero.setOrgs(findOrgsForHero(currentHero));
        // add the power to current  hero
        currentHero.setSuperpower(findSuperpowerForHero(currentHero));
    }
    return heroList;
   }
        
     //superpowerMapper
    private static final class SuperpowerMapper implements RowMapper<Superpower> {
        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
           Superpower sp = new Superpower();
            sp.setSuperpowerId(rs.getInt("superpower_id"));
            sp.setSuperpower(rs.getString("Superpower"));
            
            return sp;
        }
    } 
    
    //mapper
    private static final class OrganisationMapper implements RowMapper<Organisation> {
        @Override
        public Organisation mapRow(ResultSet rs, int i) throws SQLException {
            Organisation org = new Organisation();
            org.setOrganisationId(rs.getInt("organisation_id"));
            org.setOrgName(rs.getString("org_name"));
            org.setOrgStreet(rs.getString("org_address"));
            org.setOrgCity(rs.getString("org_city"));
            org.setOrgState(rs.getString("org_state"));
            org.setOrgZipCode(rs.getString("org_zipcode"));
            org.setContact(rs.getString("org_contact"));
            return org;
        }
    }
    
    
   
   
    
   // before implementing the methods,add row mapper which convert a row in table to an object 
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
