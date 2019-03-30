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
public class SuperheroDaoTest {
    
     SuperheroDao dao;
    SuperpowerDao daoS;
    OrganisationDao daoO;
    LocationDao daoL;
    SightingDao daoSt;
    
    public SuperheroDaoTest() {
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
        
       
        dao = ctx.getBean("superheroDao", SuperheroDao.class);
       
         // other daos
        daoSt = ctx.getBean("sightingDao", SightingDao.class);
        
        // remove all of the sightings
        List<Sighting> sightings = daoSt.getAllSightings();
        for (Sighting s : sightings) {
            daoSt.deleteSighting(s.getSightingId());
        }
        
        
        // remove all of the heros
        List<Superhero> heros = dao.getAllSuperheros();
        for (Superhero s : heros) {
         dao.deleteSuperhero(s.getSuperheroId());
        }
        
        daoS = ctx.getBean("superpowerDao", SuperpowerDao.class);
        
        // remove all of the powers
        List<Superpower> powers = daoS.getAllSuperpowers();
        for (Superpower p : powers) {
            daoS.deleteSuperpower(p.getSuperpowerId());
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
        
       
        
        
    

     }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperhero method, of class SuperheroDao.
     */
   @Test
    public void testGetAddDeleteSuperhero() {
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoS.addSuperpower(power);
        
        
        // add organisation
        
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        daoO.addOrganisation(org);
        List<Organisation> orgs = new ArrayList<>();
        //orgs = daoO.getAllOrganisations();
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
      
        dao.addSuperhero(sh);
        Superhero fromDao = dao.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDao,sh);
        
        // delete superhero
        dao.deleteSuperhero(sh.getSuperheroId());
        assertNull(dao.getSuperheroById(sh.getSuperheroId()));
    }

    
    /**
     * Test of updateSuperhero method, of class SuperheroDao.
     */
    @Test
    public void testUpdateSuperhero() {
        
         // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoS.addSuperpower(power);
        Superpower power2 = new Superpower();
        power.setSuperpower("High Speed");
        daoS.addSuperpower(power2);
        
        // add organisation
        
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        daoO.addOrganisation(org);
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        daoO.addOrganisation(org2);
        
        List<Organisation> orgs = new ArrayList<>();
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
        assertEquals(1,orgs.size());
        dao.addSuperhero(sh);
        int id = sh.getSuperheroId();
        Superhero fromDao = dao.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDao.getSuperheroId(),sh.getSuperheroId());
      
        sh.setName("Batbaby");
        sh.setDescription("Baby flying like a bat");
        sh.setSuperpower(power2);
        orgs.add(org2);
        sh.setOrgs(orgs);
        assertEquals(2,orgs.size());
        Superhero  fromDao2 = new Superhero();
        dao.updateSuperhero(sh);
        fromDao2=dao.getSuperheroById(id);
        assertEquals(fromDao2,sh);
        assertEquals(sh.getName(),"Batbaby");
        
    }

    
    /**
     * Test of getAllSuperheros method, of class SuperheroDao.
     */
 @Test
    public void testGetAllSuperheros() {
        
        
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoS.addSuperpower(power);
        
        
        // add organisation
        
        Organisation org2 = new Organisation();
        org2.setOrgName("avengers");
        org2.setOrgStreet("21somewhwre");
        org2.setOrgCity("somewhereCity");
        org2.setOrgState("st");
        org2.setOrgZipCode("23045");
        org2.setContact("800 23 45 67");
        daoO.addOrganisation(org2);
        List<Organisation> orgs = new ArrayList<>();
        orgs = daoO.getAllOrganisations();
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org2);
        sh.setOrgs(orgs);
      
        dao.addSuperhero(sh);
        Superhero fromDao = dao.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDao,sh);
        
        
        Superhero  sh2 = new Superhero();
        sh2.setName("Flash");
        sh2.setDescription("superhero with super speed");
        sh2.setSuperpower(power);
        orgs.add(org2);
        sh2.setOrgs(orgs);
      
        dao.addSuperhero(sh2);
        Superhero fromDao2 = dao.getSuperheroById(sh2.getSuperheroId());
        assertEquals(fromDao2,sh2);
       
        assertEquals(2,dao.getAllSuperheros().size());
        
    }    

    /**
     * Test of getAllSuperherosByOrganisation method, of class SuperheroDao.
     */
    @Test
    public void testGetAllSuperherosByOrganisation() {
        
        // add first superpower
        Superpower power2 = new Superpower();
        power2.setSuperpower("speedFly");
        daoS.addSuperpower(power2);
        
        
        // add organisation
        
        Organisation orgS = new Organisation();
        orgS.setOrgName("good cause");
        orgS.setOrgStreet("77somewhwre");
        orgS.setOrgCity("whereCity");
        orgS.setOrgState("pa");
        orgS.setOrgZipCode("19006");
        orgS.setContact("888 23 45 99");
        daoO.addOrganisation(orgS);
        List<Organisation> AllOrg= new ArrayList<>() ;
      
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power2);
        AllOrg.add(orgS);
       sh3.setOrgs(AllOrg);
      
        dao.addSuperhero(sh3);
        Superhero fromDao = dao.getSuperheroById(sh3.getSuperheroId());
        assertEquals(fromDao,sh3);
        
        
        Superhero  sh4 = new Superhero();
        sh4.setName("Flash");
        sh4.setDescription("superhero with super speed");
        sh4.setSuperpower(power2);
        sh4.setOrgs(AllOrg);
      
        dao.addSuperhero(sh4);
        Superhero fromDao2 = dao.getSuperheroById(sh4.getSuperheroId());
        
        assertEquals(fromDao2,sh4);
        int orgId = orgS.getOrganisationId();
        List<Superhero>sup = dao.getAllSuperherosByOrganisation(orgId);
        
        assertEquals(2,sup.size());
        
        
    }

    /**
     * Test of getAllSuperherosByLocation method, of class SuperheroDao.
     */
    @Test
    public void testGetAllOrganisationsByHero() {
         // add first superpower
        Superpower power2 = new Superpower();
        power2.setSuperpower("speedFly");
        daoS.addSuperpower(power2);
        
        // add orgs
        Organisation org = new Organisation();
        org.setOrgName("avengers");
        org.setOrgStreet("21somewhwre");
        org.setOrgCity("somewhereCity");
        org.setOrgState("st");
        org.setOrgZipCode("23045");
        org.setContact("800 23 45 67");
        daoO.addOrganisation(org);
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        daoO.addOrganisation(org2);
        
        List<Organisation>orgs = new ArrayList<>();
        orgs = daoO.getAllOrganisations();
        assertEquals(2,orgs.size());
        
        //add heros
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power2);
        
        sh3.setOrgs(orgs);
      
        dao.addSuperhero(sh3);
        Superhero fromDao = dao.getSuperheroById(sh3.getSuperheroId());
        assertEquals(fromDao.getSuperheroId(),sh3.getSuperheroId());
        
         
         assertEquals(2,dao.getAllOrganisationsByHero(sh3.getSuperheroId()).size());
        
        List<Organisation>orgSh4 = new ArrayList<>();
        Superhero  sh4 = new Superhero();
        sh4.setName("Flash");
        sh4.setDescription("superhero with super speed");
        sh4.setSuperpower(power2);
        orgSh4.add(org);
        sh4.setOrgs(orgSh4);
      
        dao.addSuperhero(sh4);
        Superhero fromDao2 = dao.getSuperheroById(sh4.getSuperheroId());
        
        assertEquals(fromDao2,sh4);
        
        assertEquals(1,dao.getAllOrganisationsByHero(sh4.getSuperheroId()).size());
        
        
    }
   
    


}
