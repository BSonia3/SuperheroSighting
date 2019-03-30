/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.controller;

import com.mycompany.thesuperherosighting.model.Location;
import com.mycompany.thesuperherosighting.service.LocationServiceLayer;
import java.math.BigDecimal;
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
public class LocationController {
    LocationServiceLayer service;
    
    @Inject
    public LocationController( LocationServiceLayer service){
        this.service=service;
    }
   // get all contacts
    @RequestMapping(value="/displayLocationPage", method=RequestMethod.GET)
    public String displayLocationPage(Model model) {
        List<Location> locList = service.getAllLocations();

     // Put the List of locs on the Model
       model.addAttribute("locationList", locList); 
    
        return "location";
    }
    
    
    @RequestMapping(value="/createLocation", method=RequestMethod.POST)
    public String createLocation(HttpServletRequest request){
        
        Location loc = new Location();
        loc.setLocName(request.getParameter("locationName"));
        loc.setLocDescription(request.getParameter("locationDescription"));
        loc.setStreet(request.getParameter("street"));
        loc.setCity(request.getParameter("city"));
        loc.setState(request.getParameter("state"));
        loc.setZipCode(request.getParameter("zipCode"));
        loc.setLatitude(new BigDecimal(request.getParameter("latitude")));
        loc.setLongitude(new BigDecimal(request.getParameter("longitude")));
        
        
        service.addLocation(loc);
        
        return "redirect:displayLocationPage";
    }
    
    // view loc
    
    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
    String locationIdParameter = request.getParameter("locationId");
    int locationId = Integer.parseInt(locationIdParameter);

    Location location = service.getLocationById(locationId);

    model.addAttribute("location", location);

    return "locationDetails";
   }
   
   //delete location
   
   @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request, Model model) {
    String locationIdParameter = request.getParameter("locationId");
    int locationId = Integer.parseInt(locationIdParameter);

    service.deleteLocation(locationId);

   return "redirect:displayLocationPage";
   }
    
   // update location
    
    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
    String locationIdParameter = request.getParameter("locationId");
    int locationId = Integer.parseInt(locationIdParameter);

    Location location = service.getLocationById(locationId);

    model.addAttribute("location", location);

    return "editLocationForm";
   }
  
   @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
   public String editLocation(@ModelAttribute("location") Location location) {

      service.updateLocation(location);

    return "redirect:displayLocationPage";
   }  
        
    
}
