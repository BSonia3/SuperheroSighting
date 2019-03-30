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
public class OrganizationServiceLayerTest {
    
     
    OrganizationServiceLayer service;
    SightingServiceLayer serviceSt;
    SuperheroServiceLayer serviceS;
    LocationServiceLayer serviceL;
    SuperpowerServiceLayer serviceP;
    
    
    public OrganizationServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
         // ask Spring for our DAO
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        service = ctx.getBean("organizationServiceLayer", OrganizationServiceLayer.class);
        
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
         
        
        serviceP = ctx.getBean("superpowerServiceLayer", SuperpowerServiceLayer.class);
        
        // remove all of the powers
        List<Superpower> powers = serviceP.getAllSuperpowers();
        for (Superpower p : powers) {
            serviceP.deleteSuperpower(p.getSuperpowerId());
        }
        serviceL = ctx.getBean("locationServiceLayer", LocationServiceLayer.class);
        
        // remove all of the locs
        List<Location> locs = serviceL.getAllLocations();
        for (Location currentLoc : locs) {
            serviceL.deleteLocation(currentLoc.getLocationId());
        }
        
        // remove all of the Orgs
        List<Organisation> orgs = service.getAllOrganisations();
        for (Organisation currentOrg : orgs) {
            service.deleteOrganisation(currentOrg.getOrganisationId());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrganisation method, of class OrganizationServiceLayer.
     */
    @Test
    public void testAddGetDeleteOrganisation() {
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        service.addOrganisation(org);
        Organisation fromDao = service.getOrganisationById(org.getOrganisationId());
        assertEquals(org,fromDao);
        service.deleteOrganisation(org.getOrganisationId());
        assertNull(service.getOrganisationById(org.getOrganisationId()));
    }

    
    /**
     * Test of updateOrganisation method, of class OrganizationServiceLayer.
     */
    @Test
    public void testUpdateOrganisation() {
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        service.addOrganisation(org);
        org.setContact("5555555555");
        service.updateOrganisation(org);
        assertEquals(org,service.getOrganisationById(org.getOrganisationId()));
        assertEquals(org.getContact(),"5555555555");
    }

    
    /**
     * Test of getAllOrganisations method, of class OrganizationServiceLayer.
     */
    @Test
    public void testGetAllOrganisations() {
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        service.addOrganisation(org);
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        service.addOrganisation(org2);
    }

    
    
}
