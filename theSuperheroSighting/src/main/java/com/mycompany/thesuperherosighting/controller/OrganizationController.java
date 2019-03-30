/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.controller;

import com.mycompany.thesuperherosighting.model.Organisation;
import com.mycompany.thesuperherosighting.service.OrganizationServiceLayer;
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
public class OrganizationController {
    OrganizationServiceLayer service;
    
    @Inject
   public OrganizationController(OrganizationServiceLayer service){
     this.service=service;
    }
    @RequestMapping(value="/displayOrganizationPage", method=RequestMethod.GET)
    public String displayOrganizationPage(Model model) {
        List<Organisation> orgList = service.getAllOrganisations();

     // Put the List of orgs on the Model
       model.addAttribute("orgList", orgList); 
        return "organization";
    }
    
    @RequestMapping(value="/createOrganisation", method=RequestMethod.POST)
    public String createOrganisation(HttpServletRequest request){
        
       Organisation org = new Organisation();
        org.setOrgName(request.getParameter("organizationName"));
        org.setOrgStreet(request.getParameter("street"));
        org.setOrgCity(request.getParameter("city"));
        org.setOrgState(request.getParameter("state"));
        org.setOrgZipCode(request.getParameter("zip"));
        org.setContact(request.getParameter("contact"));
         
        service.addOrganisation(org);
        
        return "redirect:displayOrganizationPage";
    }
    
    // view loc
    
    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
    String locationIdParameter = request.getParameter("organisationId");
    int orgId = Integer.parseInt(locationIdParameter);
    Organisation org = service.getOrganisationById(orgId);

    model.addAttribute("organization", org);

    return "organizationDetails";
   }
    
    //delete org
   
   @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request, Model model) {
    String organizationIdParameter = request.getParameter("organisationId");
    int orgId = Integer.parseInt(organizationIdParameter);

    service.deleteOrganisation(orgId);

   return "redirect:displayOrganizationPage";
   }
    
    // update location
    
    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
    String locationIdParameter = request.getParameter("organisationId");
    int orgId = Integer.parseInt(locationIdParameter);

   Organisation org = service.getOrganisationById(orgId);

    model.addAttribute("organization", org);

    return "editOrganizationForm";
   }
  
   @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
   public String editLocation(@ModelAttribute("organization") Organisation org) {

      service.updateOrganisation(org);

    return "redirect:displayOrganizationPage";
   }  
    
    
}

