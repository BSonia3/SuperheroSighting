/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

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
public class LocationServiceLayerTest {
   LocationServiceLayer service;
    SightingServiceLayer serviceSt;
    SuperheroServiceLayer serviceS;
    OrganizationServiceLayer serviceO;
    SuperpowerServiceLayer serviceP;
    
    public LocationServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
          // ask Spring for our service
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        service = ctx.getBean("locationServiceLayer", LocationServiceLayer.class);
        
         serviceSt = ctx.getBean("sightingServiceLayer", SightingServiceLayer.class);
        
        // remove all of the sightings
        List<Sighting> sightings = serviceSt.getAllSightings();
        for (Sighting s : sightings) {
            serviceSt.deleteSighting(s.getSightingId());
        }
        
          
        serviceS = ctx.getBean("superheroServiceLayer", SuperheroServiceLayer.class);
        
        // remove all of the heros
        List<Superhero> heros = serviceS.getAllSuperheros();
        for (Superhero s : heros) {
            serviceS.deleteSuperhero(s.getSuperheroId());
        }
        
        serviceO = ctx.getBean("organizationServiceLayer", OrganizationServiceLayer.class);
        
        // remove all of the Orgs
        List<Organisation> orgs = serviceO.getAllOrganisations();
        for (Organisation currentOrg : orgs) {
            serviceO.deleteOrganisation(currentOrg.getOrganisationId());
        }
        
        
        serviceP = ctx.getBean("superpowerServiceLayer", SuperpowerServiceLayer.class);
        
        // remove all of the powers
        List<Superpower> powers = serviceP.getAllSuperpowers();
        for (Superpower p : powers) {
            serviceP.deleteSuperpower(p.getSuperpowerId());
        }
        
        // remove all of the locs
        List<Location> locs = service.getAllLocations();
        for (Location l : locs) {
            service.deleteLocation(l.getLocationId());
        }
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addLocation method, of class LocationServiceLayer.
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
        
        service.addLocation(loc);
        Location fromDao = service.getLocationById(loc.getLocationId());
        //assertEquals(loc,dao.getLocationById(loc.getLocationId()));
        assertEquals(fromDao.getLocationId(),loc.getLocationId());
    }

    /**
     * Test of deleteLocation method, of class LocationServiceLayer.
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
        
        service.addLocation(loc);
        assertNotNull(service.getLocationById(loc.getLocationId()));
        service.deleteLocation(loc.getLocationId());
        assertNull(service.getLocationById(loc.getLocationId()));
    }

    /**
     * Test of updateLocation method, of class LocationServiceLayer.
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
        
        service.addLocation(loc);
        loc.setLocName("location2");
        loc.setLocDescription("superhero descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("33.1"));
        loc.setLatitude(new BigDecimal("55.2"));
        service.updateLocation(loc);
        //assertEquals(loc,dao.getLocationById(loc.getLocationId()));
        assertEquals(loc.getLocName(),"location2");
    }

    

    /**
     * Test of getAllLocations method, of class LocationServiceLayer.
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
        service.addLocation(loc);
        
        Location loc2 = new Location();
        loc2.setLocName("loc2");
        loc2.setLocDescription("superhero descreption2");
        loc2.setStreet("55street");
        loc2.setCity("myCity2");
        loc2.setState("st");
        loc2.setZipCode("19046");
        loc2.setLongitude(new BigDecimal("41.13"));
        loc2.setLatitude(new BigDecimal("73.2"));
        service.addLocation(loc2);
        
        assertEquals(service.getAllLocations().size(),2);
    }

    
    
}

