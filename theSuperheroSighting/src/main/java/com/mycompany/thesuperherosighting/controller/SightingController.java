/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.controller;

import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.model.Organisation;
import com.mycompany.thesuperherosighting.model.Picture;
import com.mycompany.thesuperherosighting.model.Sighting;
import com.mycompany.thesuperherosighting.model.Superhero;
import com.mycompany.thesuperherosighting.model.Superpower;
import com.mycompany.thesuperherosighting.service.AlbumServiceLayer;
import com.mycompany.thesuperherosighting.service.LocationServiceLayer;
import com.mycompany.thesuperherosighting.service.OrganizationServiceLayer;
import com.mycompany.thesuperherosighting.service.SightingServiceLayer;
import com.mycompany.thesuperherosighting.service.SuperheroServiceLayer;
import com.mycompany.thesuperherosighting.service.SuperpowerServiceLayer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sonia
 */
@Controller
public class SightingController {
    
    SuperheroServiceLayer serviceS;
    SightingServiceLayer service;
    LocationServiceLayer serviceL;
    OrganizationServiceLayer serviceO;
    SuperpowerServiceLayer serviceP;
   // public static final String PICTUREFOLDER = "PICTUREFOLDER/";
    private AlbumServiceLayer serviceA;
    @Inject
   public SightingController( SightingServiceLayer service,
           SuperheroServiceLayer serviceS , 
           LocationServiceLayer serviceL,
           OrganizationServiceLayer serviceO,
           SuperpowerServiceLayer serviceP,
           AlbumServiceLayer serviceA)
   { this.service=service;
     this.serviceS=serviceS;
     this.serviceL=serviceL;
     this.serviceO=serviceO;
     this.serviceP=serviceP;
     this.serviceA =serviceA;
    }
   
   @RequestMapping(value="/displaySightingPage",method=RequestMethod.GET)
    public  String displaySightingPage(Model model){
      
        List<Sighting> sightingList = service.getAllSightings();
        model.addAttribute("sightingList", sightingList); 
        List<Picture> picList=serviceA.getAllPictures();
         model.addAttribute("pictureList", picList); 
        return "sighting";
        
    }
    
    @RequestMapping(value="/displaySightingForm", method=RequestMethod.GET)
    public String displaySightingForm(Model model) {
        List<Superhero> heroList=serviceS.getAllSuperheros();
        List<Location>locList = serviceL.getAllLocations();
                
        List<Superpower> powerList=serviceP.getAllSuperpowers();
        List<Organisation> orgList=serviceO.getAllOrganisations();
        model.addAttribute("heroList", heroList);
        model.addAttribute("locationList",locList);
        model.addAttribute("powerList", powerList);
        model.addAttribute("orgList", orgList);
        
        return  "/sightingCreateForm";
    }
    
    
    @RequestMapping(value="/createSighting", method=RequestMethod.POST)
    public String createSighting(HttpServletRequest request,int locationId, int[]superheroId){
        
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse(request.getParameter("sightingDate"), 
                                 DateTimeFormatter.ISO_DATE));
        Location location = new Location();
        location.setLocationId(locationId);
        sighting.setLocation(location);
        
        List<Superhero> heroList=new ArrayList<>();
        List<Superhero> heroS=serviceS.getAllSuperheros();
        for (int id:superheroId){
         for (Superhero hero:heroS){
         if(hero.getSuperheroId()==id)
        
        heroList.add(hero);}}
        sighting.setHeros(heroList);
        service.addSighting(sighting);

        return "redirect:displaySightingPage";
    }
    
     @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request, Model model) {
    String sightingIdParameter = request.getParameter("sightingId");
    int sightingId = Integer.parseInt(sightingIdParameter);

    service.deleteSighting(sightingId);

   return "redirect:displaySightingPage";
   }
      
    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
    String sIdParameter = request.getParameter("sightingId");
    int sId = Integer.parseInt(sIdParameter);
    Sighting sighting = service.getSighting(sId);
    List<Superhero> heroList=sighting.getHeros();
    Location location = sighting.getLocation();
    model.addAttribute("heroList", heroList);
    model.addAttribute("sighting", sighting);
    model.addAttribute("location", location);
    //add picture 
    List<Picture> picList=serviceA.getAllPictures();
    List<Picture> heroPic=new ArrayList<>();
     for(Superhero hero:heroList){ 
    for(Picture currentPicture:picList){
 
    if(hero.getName().equalsIgnoreCase(currentPicture.getTitle())){
  
      heroPic.add(currentPicture);}
      model.addAttribute("heroPic",heroPic);     
    }}
  
    return "sightingDetails";
   } 
    
   // update sughting
    
    @RequestMapping(value="/displayEditSightingForm", method=RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sIdParameter = request.getParameter("sightingId");
        int sId = Integer.parseInt(sIdParameter);
        Sighting sighting = service.getSighting(sId);
        model.addAttribute("sighting", sighting);
        List<Superhero> heroList=serviceS.getAllSuperheros();
        List<Location> locList=serviceL.getAllLocations();
        model.addAttribute("heroList", heroList);
        model.addAttribute("locList", locList);
        return "/editSightingForm";
    }
    
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@ModelAttribute("sighting") Sighting sighting ,HttpServletRequest request,int locationId,int[]superheroId) {
      
       // sighting.setSightingDate(LocalDate.parse(request.getParameter("sightingDate"), 
                            //  DateTimeFormatter.ISO_DATE));
        
        List<Superhero> listH=new ArrayList<>();
        List<Superhero> heroList=serviceS.getAllSuperheros();
        for (int id:superheroId){
         for (Superhero sh:heroList){
         if(sh.getSuperheroId()==id)
        
        listH.add(sh);}}
        sighting.setHeros(listH);
        
        Location location  = new Location();
        String locIdParameter = request.getParameter("locationId");
        locationId = Integer.parseInt(locIdParameter); 
        location.setLocationId(locationId);
        sighting.setLocation(location);
        
        service.updateSighting(sighting);
        
        return "redirect:displaySightingPage";
    }
    
  
    
   
    
}

