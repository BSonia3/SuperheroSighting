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
public class SuperheroServiceLayerTest {
   SuperheroServiceLayer service;
    OrganizationServiceLayer serviceO;
    SuperpowerServiceLayer serviceP;
    LocationServiceLayer serviceL;
    SightingServiceLayer serviceSt;
    public SuperheroServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx
            = new ClassPathXmlApplicationContext(
                        "test-applicationContext.xml");
        
       
       service = ctx.getBean("superheroServiceLayer", SuperheroServiceLayer.class);
       
       serviceSt = ctx.getBean("sightingServiceLayer", SightingServiceLayer.class);
        
        // remove all of the sightings
        List<Sighting> sightings = serviceSt.getAllSightings();
        for (Sighting s : sightings) {
            serviceSt.deleteSighting(s.getSightingId());
        }
        
        
        // remove all of the heros
        List<Superhero> heros = service.getAllSuperheros();
        for (Superhero s : heros) {
         service.deleteSuperhero(s.getSuperheroId());
        }
        
        serviceP = ctx.getBean("superpowerServiceLayer", SuperpowerServiceLayer.class);
        
         // remove all of the powers
        List<Superpower> powers =  serviceP.getAllSuperpowers();
        for (Superpower p : powers) {
             serviceP.deleteSuperpower(p.getSuperpowerId());
        }
        
        serviceO = ctx.getBean("organizationServiceLayer", OrganizationServiceLayer.class);
        
        // remove all of the Orgs
        List<Organisation> orgs = serviceO.getAllOrganisations();
        for (Organisation currentOrg : orgs) {
            serviceO.deleteOrganisation(currentOrg.getOrganisationId());
        }
        
        serviceL = ctx.getBean("locationServiceLayer", LocationServiceLayer.class);
        
        // remove all of the locs
        List<Location> locs = serviceL.getAllLocations();
        for (Location currentLoc : locs) {
            serviceL.deleteLocation(currentLoc.getLocationId());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperhero method, of class SuperheroServiceLayer.
     */
    @Test
    public void testAddGetDeleteSuperhero() {
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        serviceP.addSuperpower(power);
        
        
        // add organisation
        
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        serviceO.addOrganisation(org);
        List<Organisation> orgs = new ArrayList<>();
        //orgs = daoO.getAllOrganisations();
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
      
        service.addSuperhero(sh);
        Superhero fromDao = service.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDao,sh);
        
        // delete superhero
        service.deleteSuperhero(sh.getSuperheroId());
        assertNull(service.getSuperheroById(sh.getSuperheroId()));
    }

   
    /**
     * Test of updateSuperhero method, of class SuperheroServiceLayer.
     */
    @Test
    public void testUpdateSuperhero() {
         // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        serviceP.addSuperpower(power);
        
        
        // add organisation
        
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        serviceO.addOrganisation(org);
        List<Organisation> orgs = new ArrayList<>();
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
        service.addSuperhero(sh);
        Superhero fromservice =service.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromservice,sh);
      
        sh.setName("Batbaby");
        
        service.updateSuperhero(sh);
        fromservice=service.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromservice,sh);
        assertEquals(sh.getName(),"Batbaby");
        
    }

    
    /**
     * Test of getAllSuperheros method, of class SuperheroServiceLayer.
     */
    @Test
    public void testGetAllSuperheros() {
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        serviceP.addSuperpower(power);
        
        
        // add organisation
        
        Organisation org2 = new Organisation();
        org2.setOrgName("avengers");
        org2.setOrgStreet("21somewhwre");
        org2.setOrgCity("somewhereCity");
        org2.setOrgState("st");
        org2.setOrgZipCode("23045");
        org2.setContact("800 23 45 67");
        serviceO.addOrganisation(org2);
        List<Organisation> orgs = new ArrayList<>();
        orgs = serviceO.getAllOrganisations();
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org2);
        sh.setOrgs(orgs);
      
        service.addSuperhero(sh);
        Superhero fromservice = service.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromservice,sh);
        
        
        Superhero  sh2 = new Superhero();
        sh2.setName("Flash");
        sh2.setDescription("superhero with super speed");
        sh2.setSuperpower(power);
        orgs.add(org2);
        sh2.setOrgs(orgs);
      
        service.addSuperhero(sh2);
        Superhero fromservice2 = service.getSuperheroById(sh2.getSuperheroId());
        assertEquals(fromservice2,sh2);
       
        assertEquals(2,service.getAllSuperheros().size());
    }

    /**
     * Test of getAllSuperherosByOrganisation method, of class SuperheroServiceLayer.
     */
    @Test
    public void testGetAllSuperherosByOrganisation() {
        // add first superpower
        Superpower power2 = new Superpower();
        power2.setSuperpower("speedFly");
        serviceP.addSuperpower(power2);
        
        
        // add organisation
        
        Organisation orgS = new Organisation();
        orgS.setOrgName("good cause");
        orgS.setOrgStreet("77somewhwre");
        orgS.setOrgCity("whereCity");
        orgS.setOrgState("pa");
        orgS.setOrgZipCode("19006");
        orgS.setContact("888 23 45 99");
        serviceO.addOrganisation(orgS);
        List<Organisation> AllOrg= new ArrayList<>() ;
      
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power2);
        AllOrg.add(orgS);
       sh3.setOrgs(AllOrg);
      
        service.addSuperhero(sh3);
        Superhero fromService = service.getSuperheroById(sh3.getSuperheroId());
        assertEquals(fromService,sh3);
        
        
        Superhero  sh4 = new Superhero();
        sh4.setName("Flash");
        sh4.setDescription("superhero with super speed");
        sh4.setSuperpower(power2);
        sh4.setOrgs(AllOrg);
      
        service.addSuperhero(sh4);
        Superhero fromservice2 = service.getSuperheroById(sh4.getSuperheroId());
        
        assertEquals(fromservice2,sh4);
        int orgId = orgS.getOrganisationId();
        List<Superhero>sup = service.getAllSuperherosByOrganisation(orgId);
        
        assertEquals(2,sup.size());
        
    }

    /**
     * Test of getAllOrganisationsByHero method, of class SuperheroServiceLayer.
     */
    @Test
    public void testGetAllOrganisationsByHero() {
        
         // add first superpower
        Superpower power2 = new Superpower();
        power2.setSuperpower("speedFly");
        serviceP.addSuperpower(power2);
        
        // add orgs
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        serviceO.addOrganisation(org);
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        serviceO.addOrganisation(org2);
        
        List<Organisation>orgs = new ArrayList<>();
        orgs = serviceO.getAllOrganisations();
        assertEquals(2,orgs.size());
        
        //add heros
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power2);
        
        sh3.setOrgs(orgs);
      
        service.addSuperhero(sh3);
        Superhero fromDao = service.getSuperheroById(sh3.getSuperheroId());
        assertEquals(fromDao.getSuperheroId(),sh3.getSuperheroId());
        
         
         assertEquals(2,service.getAllOrganisationsByHero(sh3.getSuperheroId()).size());
        
        List<Organisation>orgSh4 = new ArrayList<>();
        Superhero  sh4 = new Superhero();
        sh4.setName("Flash");
        sh4.setDescription("superhero with super speed");
        sh4.setSuperpower(power2);
        orgSh4.add(org);
        sh4.setOrgs(orgSh4);
      
        service.addSuperhero(sh4);
        Superhero fromDao2 = service.getSuperheroById(sh4.getSuperheroId());
        
        assertEquals(fromDao2,sh4);
        
        assertEquals(1,service.getAllOrganisationsByHero(sh4.getSuperheroId()).size());
        
        
    }

   
   
    
}
