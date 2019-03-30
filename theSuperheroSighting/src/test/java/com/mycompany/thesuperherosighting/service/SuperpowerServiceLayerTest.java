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
public class SuperpowerServiceLayerTest {
    
     SuperpowerServiceLayer service;
    OrganizationServiceLayer serviceO;
    SightingServiceLayer serviceSt;
    SuperheroServiceLayer serviceS;
    LocationServiceLayer serviceL;
   
    
    public SuperpowerServiceLayerTest() {
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
        service = ctx.getBean("superpowerServiceLayer", SuperpowerServiceLayer.class);
        
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
         
       
        serviceL = ctx.getBean("locationServiceLayer", LocationServiceLayer.class);
        
        // remove all of the locs
        List<Location> locs = serviceL.getAllLocations();
        for (Location currentLoc : locs) {
            serviceL.deleteLocation(currentLoc.getLocationId());
        }
        serviceO = ctx.getBean("organizationServiceLayer", OrganizationServiceLayer.class);
        // remove all of the Orgs
        List<Organisation> orgs = serviceO.getAllOrganisations();
        for (Organisation currentOrg : orgs) {
            serviceO.deleteOrganisation(currentOrg.getOrganisationId());
        }
        
        // remove all of the powers
        List<Superpower> powers = service.getAllSuperpowers();
        for (Superpower p : powers) {
            service.deleteSuperpower(p.getSuperpowerId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperpower method, of class SuperpowerServiceLayer.
     */
    @Test
    public void testAddGetSuperpower() {
         Superpower pow = new Superpower();
        pow.setSuperpower("fly");
        service.addSuperpower(pow);
        Superpower sp = service.getSuperpower(pow.getSuperpowerId());
        assertEquals(sp, pow);
    }

    /**
     * Test of deleteSuperpower method, of class SuperpowerServiceLayer.
     */
    @Test
    public void testDeleteSuperpower() {
        Superpower pow = new Superpower();
        pow.setSuperpower("fly");
        service.addSuperpower(pow);
        Superpower fromS = service.getSuperpower(pow.getSuperpowerId());
        assertEquals(fromS, pow);
        service.deleteSuperpower(pow.getSuperpowerId());
        assertNull(service.getSuperpower(pow.getSuperpowerId()));
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerServiceLayer.
     */
    @Test
    public void testUpdateSuperpower() {
        Superpower pow = new Superpower();
        pow.setSuperpower("fly");
        service.addSuperpower(pow);
        Superpower fromservice = service.getSuperpower(pow.getSuperpowerId());
        assertEquals(fromservice, pow);
        pow.setSuperpower("superSpeed");
        service.updateSuperpower(pow);
        assertEquals(pow.getSuperpower(),"superSpeed");
    }

    /**
     * Test of getSuperpower method, of class SuperpowerServiceLayer.
     */
    @Test
    public void testGetSuperpower() {
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerServiceLayer.
     */
    @Test
    public void testGetAllSuperpowers() {
        Superpower pow = new Superpower();
        pow.setSuperpower("fly");
        service.addSuperpower(pow);
        Superpower fromservice = service.getSuperpower(pow.getSuperpowerId());
        assertEquals(fromservice, pow);
        assertEquals(1,service.getAllSuperpowers().size());
        Superpower pow2 = new Superpower();
        pow.setSuperpower("superSpeed");
        service.addSuperpower(pow2);
        assertEquals(2,service.getAllSuperpowers().size());
    }

    
}
