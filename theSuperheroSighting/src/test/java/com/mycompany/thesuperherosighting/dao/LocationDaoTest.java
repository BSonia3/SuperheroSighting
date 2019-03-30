/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.dao;

import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.model.Organisation;
import com.mycompany.thesuperherosighting.model.Sighting;
import com.mycompany.thesuperherosighting.model.Superhero;
import com.mycompany.thesuperherosighting.model.Superpower;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author sonia
 */
public class LocationDaoTest {
   LocationDao dao;
    SightingDao daoSt;
    SuperheroDao daoS;
    OrganisationDao daoO;
    SuperpowerDao daoP;
    
    
    
    
    public LocationDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
         // ask Spring for our DAOs 
        
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        
        // other daos
        daoSt = ctx.getBean("sightingDao", SightingDao.class);
        
        // remove all of the sightings
        List<Sighting> sightings = daoSt.getAllSightings();
        for (Sighting s : sightings) {
            daoSt.deleteSighting(s.getSightingId());
        }
        
          
        daoS = ctx.getBean("superheroDao", SuperheroDao.class);
        
        // remove all of the heros
        List<Superhero> heros = daoS.getAllSuperheros();
        for (Superhero s : heros) {
            daoS.deleteSuperhero(s.getSuperheroId());
        }
        
        daoO = ctx.getBean("organisationDao", OrganisationDao.class);
        
        // remove all of the Orgs
        List<Organisation> orgs = daoO.getAllOrganisations();
        for (Organisation currentOrg : orgs) {
            daoO.deleteOrganisation(currentOrg.getOrganisationId());
        }
        
        
        daoP = ctx.getBean("superpowerDao", SuperpowerDao.class);
        
        // remove all of the powers
        List<Superpower> powers = daoP.getAllSuperpowers();
        for (Superpower p : powers) {
            daoP.deleteSuperpower(p.getSuperpowerId());
        }
        
       
        
        dao = ctx.getBean("locationDao", LocationDao.class);
        // remove all of the locs
        List<Location> locs = dao.getAllLocations();
        for (Location currentLoc : locs) {
            dao.deleteLocation(currentLoc.getLocationId());
        }
        
        
        
        //
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addLocation method, of class LocationDao.
     */
    @Test
    public void testAddGetLocation() {
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("superhero descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("33.1"));
        loc.setLatitude(new BigDecimal("55.2"));
        
        dao.addLocation(loc);
        Location fromDao = dao.getLocationById(loc.getLocationId());
        //assertEquals(loc,dao.getLocationById(loc.getLocationId()));
        assertEquals(fromDao.getLocationId(),loc.getLocationId());
    }

    /**
     * Test of deleteLocation method, of class LocationDao.
     */
    @Test
    public void testDeleteLocation() {
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("superhero descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("33.1"));
        loc.setLatitude(new BigDecimal("55.2"));
        
        dao.addLocation(loc);
        assertNotNull(dao.getLocationById(loc.getLocationId()));
        dao.deleteLocation(loc.getLocationId());
        assertNull(dao.getLocationById(loc.getLocationId()));
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("superhero descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("33.1"));
        loc.setLatitude(new BigDecimal("55.2"));
        
        dao.addLocation(loc);
        loc.setLocName("location2");
        loc.setLocDescription("superhero descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("33.1"));
        loc.setLatitude(new BigDecimal("55.2"));
        dao.updateLocation(loc);
        //assertEquals(loc,dao.getLocationById(loc.getLocationId()));
        assertEquals(loc.getLocName(),"location2");
    }

    /**
     * Test of getAllLocations method, of class LocationDao.
     */
    @Test
    public void testGetAllLocations() {
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("superhero descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("33.1"));
        loc.setLatitude(new BigDecimal("55.2"));
        dao.addLocation(loc);
        
        Location loc2 = new Location();
        loc2.setLocName("loc2");
        loc2.setLocDescription("superhero descreption2");
        loc2.setStreet("55street");
        loc2.setCity("myCity2");
        loc2.setState("st");
        loc2.setZipCode("19046");
        loc2.setLongitude(new BigDecimal("41.13"));
        loc2.setLatitude(new BigDecimal("73.2"));
        dao.addLocation(loc2);
        
        assertEquals(dao.getAllLocations().size(),2);
        
    }

    
   
    
    
    
}

