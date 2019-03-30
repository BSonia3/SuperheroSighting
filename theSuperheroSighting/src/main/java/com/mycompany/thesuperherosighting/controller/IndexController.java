/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.controller;

import com.mycompany.thesuperherosighting.model.Sighting;
import com.mycompany.thesuperherosighting.model.Superhero;
import com.mycompany.thesuperherosighting.service.LocationServiceLayer;
import com.mycompany.thesuperherosighting.service.OrganizationServiceLayer;
import com.mycompany.thesuperherosighting.service.SightingServiceLayer;
import com.mycompany.thesuperherosighting.service.SuperheroServiceLayer;
import com.mycompany.thesuperherosighting.service.SuperpowerServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author sonia
 */
@Controller
public class IndexController {
  SightingServiceLayer service;
  SuperheroServiceLayer serviceS;
  LocationServiceLayer serviceL;
  OrganizationServiceLayer serviceO;
  SuperpowerServiceLayer serviceP; 
   
  @Inject
   public IndexController(SightingServiceLayer service, 
       SuperheroServiceLayer serviceS , 
           LocationServiceLayer serviceL,
           OrganizationServiceLayer serviceO,
           SuperpowerServiceLayer serviceP)
   { this.service=service;
     this.serviceS=serviceS;
     this.serviceL=serviceL;
     this.serviceO=serviceO;
     this.serviceP=serviceP;
    } 
   
   
   
   @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(Model model) {
      List<Sighting> sightingList = service.getAllSightingsOrdredByLast10();
        model.addAttribute("sightingList", sightingList);
         List<Superhero> heroList = serviceS.getAllSuperheros();
         model.addAttribute(" heroList",  heroList); 
     
     
        return "index";
    }
   
   @RequestMapping(value="/displayHomePage", method=RequestMethod.GET)
    public String displayhomePage() {
        
        return "redirect:/";
    } 
    
    
    
    
}

