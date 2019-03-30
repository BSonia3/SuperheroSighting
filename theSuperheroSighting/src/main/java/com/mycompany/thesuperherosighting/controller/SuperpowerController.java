/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.controller;

import com.mycompany.thesuperherosighting.model.Superpower;
import com.mycompany.thesuperherosighting.service.SuperpowerServiceLayer;
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
public class SuperpowerController {
 SuperpowerServiceLayer service;
    
    @Inject
   public SuperpowerController(SuperpowerServiceLayer service){
     this.service=service;

    }
    @RequestMapping(value="/displaySuperpowerPage", method=RequestMethod.GET)
    public String displaySuperheroPage(Model model) {
        List<Superpower> powerList = service.getAllSuperpowers();

     // Put the List of locs on the Model
       model.addAttribute("superpowerList",powerList); 
        return "superpower";
    }
      
    
    @RequestMapping(value="/createSuperpower", method=RequestMethod.POST)
    public String createSuperpower(HttpServletRequest request){
        
        Superpower power = new Superpower();
        power.setSuperpower(request.getParameter("superpower"));
        service.addSuperpower(power);
        
        return "redirect:displaySuperpowerPage";
    }
    
    @RequestMapping(value = "/deleteSuperpower", method = RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request, Model model) {
    String powerIdParameter = request.getParameter("superpowerId");
    int powerId = Integer.parseInt(powerIdParameter);

    service.deleteSuperpower(powerId);

   return "redirect:displaySuperpowerPage";
   }
   
    // view power 
    @RequestMapping(value = "/displaySuperpowerDetails", method = RequestMethod.GET)
    public String displaySuperpowerDetails(HttpServletRequest request, Model model) {
    String pIdParameter = request.getParameter("superpowerId");
    int pId = Integer.parseInt(pIdParameter);
    Superpower superpower = service.getSuperpower(pId);

    model.addAttribute("superpower", superpower);

    return "superpowerDetails";
   } 
    
    
 // update power
    
    @RequestMapping(value = "/displayEditSuperpowerForm", method = RequestMethod.GET)
    public String displayEditSuperpowerForm(HttpServletRequest request, Model model) {
    String powerIdParameter = request.getParameter("superpowerId");
    int powId = Integer.parseInt(powerIdParameter);

   Superpower p = service.getSuperpower(powId);

    model.addAttribute("superpower", p);

    return "editSuperpowerForm";
   }
  
   @RequestMapping(value = "/editSuperpower", method = RequestMethod.POST)
   public String editSuperpower(@ModelAttribute("superpower") Superpower p) {

      service.updateSuperpower(p);

    return "redirect:displaySuperpowerPage";
   }     
    
    
}

