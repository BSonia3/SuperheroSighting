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
public class SuperpowerDaoTest {
    SuperpowerDao dao;
    LocationDao daoL;
    SightingDao daoSt;
    SuperheroDao daoS;
    OrganisationDao daoO;
   
    public SuperpowerDaoTest() {
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
        dao = ctx.getBean("superpowerDao", SuperpowerDao.class);
        
        
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
        
       
        
        daoL = ctx.getBean("locationDao", LocationDao.class);
        // remove all of the locs
        List<Location> locs = daoL.getAllLocations();
        for (Location currentLoc : locs) {
            daoL.deleteLocation(currentLoc.getLocationId());
        }
        
        // remove all of the powers
        List<Superpower> powers = dao.getAllSuperpowers();
        for (Superpower p : powers) {
            dao.deleteSuperpower(p.getSuperpowerId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperpower method, of class SuperpowerDao.
     */
    @Test
    public void testAddGetSuperpower() {
        Superpower pow = new Superpower();
        pow.setSuperpower("fly");
        dao.addSuperpower(pow);
        Superpower fromDao = dao.getSuperpower(pow.getSuperpowerId());
        assertEquals(fromDao, pow);
    }

    /**
     * Test of deleteSuperpower method, of class SuperpowerDao.
     */
    @Test
    public void testDeleteSuperpower() {
        Superpower pow = new Superpower();
        pow.setSuperpower("fly");
        dao.addSuperpower(pow);
        Superpower fromDao = dao.getSuperpower(pow.getSuperpowerId());
        assertEquals(fromDao, pow);
        dao.deleteSuperpower(pow.getSuperpowerId());
        assertNull(dao.getSuperpower(pow.getSuperpowerId()));
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerDao.
     */
    @Test
    public void testUpdateSuperpower() {
        Superpower pow = new Superpower();
        pow.setSuperpower("fly");
        dao.addSuperpower(pow);
        Superpower fromDao = dao.getSuperpower(pow.getSuperpowerId());
        assertEquals(fromDao, pow);
        pow.setSuperpower("superSpeed");
        dao.updateSuperpower(pow);
        assertEquals(pow.getSuperpower(),"superSpeed");
    }

    
    /**
     * Test of getAllSuperpowers method, of class SuperpowerDao.
     */
    @Test
    public void testGetAllSuperpowers() {
        Superpower pow = new Superpower();
        pow.setSuperpower("fly");
        dao.addSuperpower(pow);
        Superpower fromDao = dao.getSuperpower(pow.getSuperpowerId());
        assertEquals(fromDao, pow);
        assertEquals(1,dao.getAllSuperpowers().size());
        Superpower pow2 = new Superpower();
        pow.setSuperpower("superSpeed");
        dao.addSuperpower(pow2);
        assertEquals(2,dao.getAllSuperpowers().size());
    }

    
    
}
