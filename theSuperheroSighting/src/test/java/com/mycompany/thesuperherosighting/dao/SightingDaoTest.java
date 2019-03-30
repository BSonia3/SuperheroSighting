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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoTest {
    
    SightingDao dao;
    LocationDao daoL;
    SuperheroDao  daoS;
    OrganisationDao  daoO;
    SuperpowerDao  daoP;
    
    public SightingDaoTest() {
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
        dao = ctx.getBean("sightingDao", SightingDao.class);
        
        // remove all of the sightings
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting s : sightings) {
            dao.deleteSighting(s.getSightingId());
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
     * Test of addSighting method, of class SightingDao.
     */
    @Test
    public void testAddGetDeleteSighting() {
        // add loc
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("superhero descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("33.1"));
        loc.setLatitude(new BigDecimal("55.2"));
        
        daoL.addLocation(loc);
        Location fromDaoL = daoL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        //add hero
        
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoP.addSuperpower(power);
        
        
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
        
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
        daoS.addSuperhero(sh);
        Superhero fromDaoS = daoS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setLocation(loc);
        heros.add(sh);
        s.setHeros(heros);
        dao.addSighting(s);
        Sighting fromDao = dao.getSighting(s.getSightingId());
        assertEquals(fromDao.getSightingId(),s.getSightingId());
        
        //delete sighting
        dao.deleteSighting(s.getSightingId());
        assertNull(dao.getSighting(s.getSightingId()));
        
       
    }

   
    /**
     * Test of updateSighting method, of class SightingDao.
     */
    @Test
    public void testUpdateSighting() {
        
        // add loc
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("superhero descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("33.1"));
        loc.setLatitude(new BigDecimal("55.2"));
        
        daoL.addLocation(loc);
        Location fromDaoL = daoL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        
        Location loc2 = new Location();
        loc2.setLocName("loc2");
        loc2.setLocDescription("loc descreption2");
        loc2.setStreet("55street");
        loc2.setCity("myCity2");
        loc2.setState("st");
        loc2.setZipCode("19046");
        loc2.setLongitude(new BigDecimal("1.1"));
        loc2.setLatitude(new BigDecimal("3.2"));
        daoL.addLocation(loc2);
        
        
        //add hero
        
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoP.addSuperpower(power);
        
        
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
        
       
        Superhero  sh2 = new Superhero();
        sh2.setName("Batman");
        sh2.setDescription("man with superpowers");
        sh2.setSuperpower(power);
        orgs.add(org);
        sh2.setOrgs(orgs);
        daoS.addSuperhero(sh2);
        
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
      
        daoS.addSuperhero(sh);
        Superhero fromDaoS = daoS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        dao.addSighting(s);
        Sighting fromDao = dao.getSighting(s.getSightingId());
        assertEquals(fromDao.getSightingId(),s.getSightingId());
        
        // update 
        s.setSightingDate(LocalDate.parse("2019-12-13", 
                         DateTimeFormatter.ISO_DATE));
        s.setLocation(loc2);
        heros.add(sh2);
        s.setHeros(heros);
         
        dao.updateSighting(s);
        Sighting updatedSight =dao.getSighting(s.getSightingId());
        assertEquals(s.getSightingId(),updatedSight.getSightingId());
    } 

   

    /**
     * Test of getAllSightings method, of class SightingDao.
     */
    @Test
    public void testGetAllSightings() {
        
     // add loc
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("loc descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("3.1"));
        loc.setLatitude(new BigDecimal("5.2"));
        
        daoL.addLocation(loc);
        Location fromDaoL = daoL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        
        Location loc2 = new Location();
        loc2.setLocName("loc2");
        loc2.setLocDescription("loc descreption2");
        loc2.setStreet("55street");
        loc2.setCity("myCity2");
        loc2.setState("st");
        loc2.setZipCode("19046");
        loc2.setLongitude(new BigDecimal("1.1"));
        loc2.setLatitude(new BigDecimal("3.2"));
        daoL.addLocation(loc2);
        
        //add hero
        
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoP.addSuperpower(power);
        
        
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
        
        daoS.addSuperhero(sh);
        Superhero fromDaoS = daoS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        daoS.addSuperhero(sh3);
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        dao.addSighting(s);
        Sighting fromDao = dao.getSighting(s.getSightingId());
       // assertEquals(fromDao,s);
        
        Sighting s2 = new Sighting();
       
        heros.add(sh3);
        s2.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s2.setHeros(heros);
        s2.setLocation(loc2);
        dao.addSighting(s2);
        fromDao = dao.getSighting(s2.getSightingId());
       // assertEquals(fromDao,s2);
        
        assertEquals(2,dao.getAllSightings().size());
        
        
    } 
    
    @Test
    public void testGetAllLocsByHero() {
        
        // add loc
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("loc descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("3.1"));
        loc.setLatitude(new BigDecimal("5.2"));
        
        daoL.addLocation(loc);
        Location fromDaoL = daoL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        
        Location loc2 = new Location();
        loc2.setLocName("loc2");
        loc2.setLocDescription("loc descreption2");
        loc2.setStreet("55street");
        loc2.setCity("myCity2");
        loc2.setState("st");
        loc2.setZipCode("19046");
        loc2.setLongitude(new BigDecimal("1.1"));
        loc2.setLatitude(new BigDecimal("3.2"));
        daoL.addLocation(loc2);
        
        //add hero
        
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoP.addSuperpower(power);
        
        
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
        
        daoS.addSuperhero(sh);
        Superhero fromDaoS = daoS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("superman");
        sh3.setDescription("flying man");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        daoS.addSuperhero(sh3);
        
        // add sight
       Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        dao.addSighting(s);
       // Sighting fromDao = dao.getSighting(s.getSightingId());
       // assertEquals(fromDao,s);
        
        Sighting s2 = new Sighting();
        List<Superhero>heros2=new ArrayList<>();
        heros2.add(sh3);
        s2.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s2.setHeros(heros2);
        s2.setLocation(loc2);
        dao.addSighting(s2);
       
        Sighting s3 = new Sighting();
       
       // heros.add(sh3);
        s3.setSightingDate(LocalDate.parse("2019-05-25", 
                         DateTimeFormatter.ISO_DATE));
        s3.setHeros(heros2);
        s3.setLocation(loc);
        dao.addSighting(s3);
        
        int id=sh3.getSuperheroId();
        assertEquals(2,dao.getAllLocsByHero(id).size());
    }


    /**
     * Test of getSightingsByDate method, of class SightingDao.
     */ 
   
    @Test
    public void testGetSightingsByDate() {
        
        // add loc
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("loc descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("3.1"));
        loc.setLatitude(new BigDecimal("5.2"));
        
        daoL.addLocation(loc);
        Location fromDaoL = daoL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        
        Location loc2 = new Location();
        loc2.setLocName("loc2");
        loc2.setLocDescription("loc descreption2");
        loc2.setStreet("55street");
        loc2.setCity("myCity2");
        loc2.setState("st");
        loc2.setZipCode("19046");
        loc2.setLongitude(new BigDecimal("1.1"));
        loc2.setLatitude(new BigDecimal("3.2"));
        daoL.addLocation(loc2);
        
        //add hero
        
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoP.addSuperpower(power);
        
        
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
        
        daoS.addSuperhero(sh);
        Superhero fromDaoS = daoS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        daoS.addSuperhero(sh3);
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        dao.addSighting(s);
        Sighting fromDao = dao.getSighting(s.getSightingId());
       // assertEquals(fromDao,s);
        
        Sighting s2 = new Sighting();
        List<Superhero>heros2=new ArrayList<>();
        heros2.add(sh3);
        s2.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s2.setHeros(heros2);
        s2.setLocation(loc2);
        dao.addSighting(s2);
        fromDao = dao.getSighting(s2.getSightingId());
        dao.getSightingsByDate((LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE)));
        assertEquals(2,dao.getAllSightings().size());
        assertEquals(2,dao.getSightingsByDate((LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE))).size());
        
        
    } 

    /**
     * Test of getSightingsByLocationID method, of class SightingDao.
     */
    @Test
    public void testGetAllSuperherosByLocation() {
        
        // add loc
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("loc descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("3.1"));
        loc.setLatitude(new BigDecimal("5.2"));
        
        daoL.addLocation(loc);
        Location fromDaoL = daoL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        
        Location loc2 = new Location();
        loc2.setLocName("loc2");
        loc2.setLocDescription("loc descreption2");
        loc2.setStreet("55street");
        loc2.setCity("myCity2");
        loc2.setState("st");
        loc2.setZipCode("19046");
        loc2.setLongitude(new BigDecimal("1.1"));
        loc2.setLatitude(new BigDecimal("3.2"));
        daoL.addLocation(loc2);
        
        //add hero
        
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoP.addSuperpower(power);
        
        
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
        
        daoS.addSuperhero(sh);
        Superhero fromDaoS = daoS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        daoS.addSuperhero(sh3);
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        dao.addSighting(s);
        List<Superhero>herosLoc=new ArrayList<>();
        herosLoc=dao.getAllSuperherosByLocation(loc.getLocationId());
        assertEquals(1,herosLoc.size());
        
    }
     @Test
    public void getLast10SightingsByDate(){
   
        // add loc
        Location loc = new Location();
        loc.setLocName("locName");
        loc.setLocDescription("loc descreption");
        loc.setStreet("33street");
        loc.setCity("myCity");
        loc.setState("state");
        loc.setZipCode("1234");
        loc.setLongitude(new BigDecimal("3.1"));
        loc.setLatitude(new BigDecimal("5.2"));
        
        daoL.addLocation(loc);
        Location fromDaoL = daoL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        
        Location loc2 = new Location();
        loc2.setLocName("loc2");
        loc2.setLocDescription("loc descreption2");
        loc2.setStreet("55street");
        loc2.setCity("myCity2");
        loc2.setState("st");
        loc2.setZipCode("19046");
        loc2.setLongitude(new BigDecimal("1.1"));
        loc2.setLatitude(new BigDecimal("3.2"));
        daoL.addLocation(loc2);
        
        //add hero
        
        // add first superpower
        Superpower power = new Superpower();
        power.setSuperpower("fly");
        daoP.addSuperpower(power);
        
        
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
        
        daoS.addSuperhero(sh);
        Superhero fromDaoS = daoS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        daoS.addSuperhero(sh3);
        
        // add sight
       List<Superhero>heros=new ArrayList<>();
         
        Sighting s2 = new Sighting();
       
        heros.add(sh3);
        
        s2.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s2.setHeros(heros);
        s2.setLocation(loc);
        dao.addSighting(s2);
        
        
        Sighting s = new Sighting();
       
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        dao.addSighting(s);
       
        
        
       Sighting s3 = new Sighting();
       
       // heros.add(sh3);
        s3.setSightingDate(LocalDate.parse("2018-09-09", 
                         DateTimeFormatter.ISO_DATE));
        s3.setHeros(heros);
        s3.setLocation(loc2);
        dao.addSighting(s3);
       
       
        
        Sighting s4 = new Sighting();
       
       // heros.add(sh3);
        s4.setSightingDate(LocalDate.parse("2019-11-23", 
                         DateTimeFormatter.ISO_DATE));
        s4.setHeros(heros);
        s4.setLocation(loc);
        dao.addSighting(s4);
        List<Sighting>sightings=dao.getLast10SightingsByDate();
        assertEquals(4,dao.getLast10SightingsByDate().size());
      
     }
    
}
 


    

