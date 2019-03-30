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
public class SightingServiceLayerTest {
    SightingServiceLayer service;
    SuperheroServiceLayer serviceS;
    LocationServiceLayer serviceL;
    OrganizationServiceLayer serviceO;
    SuperpowerServiceLayer serviceP;
    
    public SightingServiceLayerTest() {
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
       service = ctx.getBean("sightingServiceLayer", SightingServiceLayer.class);
        
        // remove all of the sightings
        List<Sighting> sightings = service.getAllSightings();
        for (Sighting s : sightings) {
            service.deleteSighting(s.getSightingId());
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
     * Test of addSighting method, of class SightingServiceLayer.
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
        
        serviceL.addLocation(loc);
        Location fromDaoL = serviceL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        //add hero
        
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
        serviceS.addSuperhero(sh);
        Superhero fromDaoS = serviceS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setLocation(loc);
        heros.add(sh);
        s.setHeros(heros);
        service.addSighting(s);
        Sighting fromDao = service.getSighting(s.getSightingId());
        assertEquals(fromDao.getSightingId(),s.getSightingId());
        
        //delete sighting
        service.deleteSighting(s.getSightingId());
        assertNull(service.getSighting(s.getSightingId()));
        
    }

    
    /**
     * Test of updateSighting method, of class SightingServiceLayer.
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
        
       serviceL.addLocation(loc);
        Location fromDaoL = serviceL.getLocationById(loc.getLocationId());
        assertEquals(fromDaoL.getLocationId(),loc.getLocationId());
        //add hero
        
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
      
        serviceS.addSuperhero(sh);
        Superhero fromDaoS = serviceS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        service.addSighting(s);
        Sighting fromDao = service.getSighting(s.getSightingId());
        assertEquals(fromDao.getSightingId(),s.getSightingId());
        
        // update 
        s.setSightingDate(LocalDate.parse("2019-12-13", 
                         DateTimeFormatter.ISO_DATE));
        service.updateSighting(s);
        Sighting updatedSight =service.getSighting(s.getSightingId());
        assertEquals(s.getSightingId(),updatedSight.getSightingId());
    }

   

    /**
     * Test of getAllSightings method, of class SightingServiceLayer.
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
        
        serviceL.addLocation(loc);
        Location fromDaoL = serviceL.getLocationById(loc.getLocationId());
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
        serviceL.addLocation(loc2);
        
        //add hero
        
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
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        serviceO.addOrganisation(org2);
        
        List<Organisation> orgs = new ArrayList<>();
        
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
        
        serviceS.addSuperhero(sh);
        Superhero fromDaoS = serviceS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        serviceS.addSuperhero(sh3);
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
       service.addSighting(s);
        
        
        Sighting s2 = new Sighting();
       
        heros.add(sh3);
        s2.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s2.setHeros(heros);
        s2.setLocation(loc2);
        service.addSighting(s2);
        
        assertEquals(2,service.getAllSightings().size());
        
    
    }

    /**
     * Test of getSightingsByDate method, of class SightingServiceLayer.
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
        
        serviceL.addLocation(loc);
        Location fromDaoL = serviceL.getLocationById(loc.getLocationId());
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
        serviceL.addLocation(loc2);
        
        //add hero
        
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
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        serviceO.addOrganisation(org2);
        
        List<Organisation> orgs = new ArrayList<>();
        
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
        
        serviceS.addSuperhero(sh);
        Superhero fromDaoS = serviceS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        serviceS.addSuperhero(sh3);
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        service.addSighting(s);
        
        Sighting s2 = new Sighting();
        List<Superhero>heros2=new ArrayList<>();
        heros2.add(sh3);
        s2.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s2.setHeros(heros2);
        s2.setLocation(loc2);
        service.addSighting(s2);
        service.getSightingsByDate((LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE)));
        assertEquals(2,service.getAllSightings().size());
        assertEquals(2,service.getSightingsByDate((LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE))).size());
        
        
    }

    /**
     * Test of getAllSuperherosByLocation method, of class SightingServiceLayer.
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
        
        serviceL.addLocation(loc);
        Location fromDaoL = serviceL.getLocationById(loc.getLocationId());
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
        serviceL.addLocation(loc2);
        
        //add hero
        
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
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        serviceO.addOrganisation(org2);
        
        List<Organisation> orgs = new ArrayList<>();
        
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
        
        serviceS.addSuperhero(sh);
        Superhero fromDaoS = serviceS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        serviceS.addSuperhero(sh3);
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        service.addSighting(s);
        List<Superhero>herosLoc=new ArrayList<>();
        herosLoc=service.getAllSuperherosByLocation(loc.getLocationId());
        assertEquals(1,herosLoc.size());
        
    }

    /**
     * Test of getAllLocsByHero method, of class SightingServiceLayer.
     */
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
        
        serviceL.addLocation(loc);
        Location fromDaoL = serviceL.getLocationById(loc.getLocationId());
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
        serviceL.addLocation(loc2);
        
        //add hero
        
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
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        serviceO.addOrganisation(org2);
        
        List<Organisation> orgs = new ArrayList<>();
        
       
       Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
        
        serviceS.addSuperhero(sh);
        Superhero fromDaoS = serviceS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("superman");
        sh3.setDescription("flying man");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        serviceS.addSuperhero(sh3);
        
        // add sight
       Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2010-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
        service.addSighting(s);
       
        Sighting s2 = new Sighting();
        List<Superhero>heros2=new ArrayList<>();
        heros2.add(sh3);
        s2.setSightingDate(LocalDate.parse("2017-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s2.setHeros(heros2);
        s2.setLocation(loc2);
        service.addSighting(s2);
       
        Sighting s3 = new Sighting();
       
       // heros.add(sh3);
        s3.setSightingDate(LocalDate.parse("2019-05-25", 
                         DateTimeFormatter.ISO_DATE));
        s3.setHeros(heros2);
        s3.setLocation(loc);
       service.addSighting(s3);
        
        int id=sh3.getSuperheroId();
        assertEquals(2,service.getAllLocsByHero(id).size());
   
    }

    
    @Test
    public void getAllSightingsOrdredByLast10() {
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
        
        serviceL.addLocation(loc);
        Location fromDaoL = serviceL.getLocationById(loc.getLocationId());
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
        serviceL.addLocation(loc2);
        
        //add hero
        
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
        
        Organisation org2 = new Organisation();
        org2.setOrgName("Defenders");
        org2.setOrgStreet("77Strret");
        org2.setOrgCity("myCity");
        org2.setOrgState("nj");
        org2.setOrgZipCode("2357");
        org2.setContact("2574098373");
        serviceO.addOrganisation(org2);
        
        List<Organisation> orgs = new ArrayList<>();
        
       
        Superhero  sh = new Superhero();
        sh.setName("BabyHero");
        sh.setDescription("Baby with superpowers");
        sh.setSuperpower(power);
        orgs.add(org);
        sh.setOrgs(orgs);
        
        serviceS.addSuperhero(sh);
        Superhero fromDaoS = serviceS.getSuperheroById(sh.getSuperheroId());
        assertEquals(fromDaoS,sh);
        
        Superhero  sh3 = new Superhero();
        sh3.setName("Batman");
        sh3.setDescription("Man with superpowers");
        sh3.setSuperpower(power);
        orgs.add(org2);
        sh3.setOrgs(orgs);
      
        serviceS.addSuperhero(sh3);
        
        // add sight
        Sighting s = new Sighting();
        List<Superhero>heros=new ArrayList<>();
        heros.add(sh);
        s.setSightingDate(LocalDate.parse("2019-01-01", 
                         DateTimeFormatter.ISO_DATE));
        s.setHeros(heros);
        s.setLocation(loc);
       service.addSighting(s);
        
        
        Sighting s2 = new Sighting();
       
        heros.add(sh3);
        s2.setSightingDate(LocalDate.parse("2020-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s2.setHeros(heros);
        s2.setLocation(loc2);
        service.addSighting(s2);
        
         
        Sighting s3 = new Sighting();
       
        s3.setSightingDate(LocalDate.parse("2011-03-23", 
                         DateTimeFormatter.ISO_DATE));
        s3.setHeros(heros);
        s3.setLocation(loc2);
        service.addSighting(s3);
        List<Sighting>sightings = service.getAllSightingsOrdredByLast10();
        assertEquals(3,service.getAllSightings().size());
    }
}

