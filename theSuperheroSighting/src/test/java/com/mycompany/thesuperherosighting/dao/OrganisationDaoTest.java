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
import java.util.ArrayList;
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
public class OrganisationDaoTest {
    private OrganisationDao dao;
    LocationDao daoL;
    SightingDao daoSt;
    SuperheroDao daoS;
    SuperpowerDao daoP;
    public OrganisationDaoTest() {
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
        dao = ctx.getBean("organisationDao", OrganisationDao.class);
        
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
        
        
        
        daoP = ctx.getBean("superpowerDao", SuperpowerDao.class);
        
        // remove all of the powers
        List<Superpower> powers = daoP.getAllSuperpowers();
        for (Superpower p : powers) {
            daoP.deleteSuperpower(p.getSuperpowerId());
        }
        
       
        
        daoL = ctx.getBean("locationDao", LocationDao.class);
        // remove all of the locs
        List<Location> locs = daoL.getAllLocations();
        for (Location currentLoc : locs) {
            daoL.deleteLocation(currentLoc.getLocationId());
        }
        
        // remove all of the Orgs
        List<Organisation> orgs = dao.getAllOrganisations();
        for (Organisation currentOrg : orgs) {
            dao.deleteOrganisation(currentOrg.getOrganisationId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrganisation method, of class OrganisationDao.
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
        dao.addOrganisation(org);
        Organisation fromDao = dao.getOrganisationById(org.getOrganisationId());
        assertEquals(org,fromDao);
        dao.deleteOrganisation(org.getOrganisationId());
        assertNull(dao.getOrganisationById(org.getOrganisationId()));
                
    }

    /**
     * Test of updateOrganisation method, of class OrganisationDao.
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
        dao.addOrganisation(org);
        org.setContact("5555555555");
        dao.updateOrganisation(org);
        assertEquals(org,dao.getOrganisationById(org.getOrganisationId()));
        assertEquals(org.getContact(),"5555555555");
    }

    
    /**
     * Test of getAllOrganisations method, of class OrganisationDao.
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
        dao.addOrganisation(org);
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        dao.addOrganisation(org2);
        
        List<Organisation>orgs = new ArrayList<>();
        orgs = dao.getAllOrganisations();
        assertEquals(2,orgs.size());
    }
    
    

    
    
}
