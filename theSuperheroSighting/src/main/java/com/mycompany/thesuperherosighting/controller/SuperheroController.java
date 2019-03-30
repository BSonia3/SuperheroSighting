/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.controller;

import com.mycompany.thesuperherosighting.model.Organisation;
import com.mycompany.thesuperherosighting.model.Picture;
import com.mycompany.thesuperherosighting.model.Superhero;
import com.mycompany.thesuperherosighting.model.Superpower;
import com.mycompany.thesuperherosighting.service.AlbumServiceLayer;
import com.mycompany.thesuperherosighting.service.OrganizationServiceLayer;
import com.mycompany.thesuperherosighting.service.SuperheroServiceLayer;
import com.mycompany.thesuperherosighting.service.SuperpowerServiceLayer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author sonia
 */
@Controller
public class SuperheroController {
    
    SuperheroServiceLayer serviceS;
    OrganizationServiceLayer serviceO;
    SuperpowerServiceLayer serviceP;
    public static final String PICTUREFOLDER= "SUPERHEROPICTURES/";
    private AlbumServiceLayer serviceA;
    @Inject
   public SuperheroController(SuperheroServiceLayer serviceS ,OrganizationServiceLayer serviceO,
           SuperpowerServiceLayer serviceP,AlbumServiceLayer serviceA){
     this.serviceS=serviceS;
     this.serviceO=serviceO;
     this.serviceP=serviceP;
     this.serviceA =serviceA;
    }
   
   
    @RequestMapping(value="/displaySuperheroPage", method=RequestMethod.GET)
    public String displaySuperheroPage(Model model) {
        List<Superhero> heroList = serviceS.getAllSuperheros();
        model.addAttribute("superheroList", heroList); 
        // display all pictures 
        List<Picture> pictures = serviceA.getAllPictures();
        model.addAttribute("pictureList", pictures);
        return "superhero";
    }
    
    @RequestMapping(value="/displaySuperheroForm", method=RequestMethod.GET)
    public String displaySuperheroForm(Model model) {

        List<Superpower> powerList=serviceP.getAllSuperpowers();
        List<Organisation> orgList=serviceO.getAllOrganisations();
        model.addAttribute("powerList", powerList);
        model.addAttribute("orgList", orgList);
           return  "/superheroCreateForm";
    }
    
   
    
   @RequestMapping(value="/createSuperhero", method=RequestMethod.POST)
    public String createSuperhero(HttpServletRequest request,int superpowerId, int[]organisationId){
        
        Superhero hero = new Superhero();
        hero.setName(request.getParameter("name"));
        hero.setDescription(request.getParameter("description"));
        Superpower power = new Superpower();
        power.setSuperpowerId(superpowerId);
        hero.setSuperpower(power);
        List<Organisation> orgList=new ArrayList<>();
        List<Organisation> orgS=serviceO.getAllOrganisations();
        for (int id:organisationId){
         for (Organisation org:orgS){
         if(org.getOrganisationId()==id)
        
        orgList.add(org);}}
        hero.setOrgs(orgList);
        serviceS.addSuperhero(hero);

        return "redirect:displaySuperheroPage";
    }


    // delete superhero
    
    @RequestMapping(value = "/deleteSuperhero", method = RequestMethod.GET)
    public String deleteSuperhero(HttpServletRequest request, Model model) {
    String heroIdParameter = request.getParameter("superheroId");
    int heroId = Integer.parseInt(heroIdParameter);

    serviceS.deleteSuperhero(heroId);

   return "redirect:displaySuperheroPage";
   }
    
    // view superhero 
    @RequestMapping(value = "/displaySuperheroDetails", method = RequestMethod.GET)
    public String displaySuperheroDetails(HttpServletRequest request, Model model) {
    String pIdParameter = request.getParameter("superheroId");
    int pId = Integer.parseInt(pIdParameter);
    Superhero superhero = serviceS.getSuperheroById(pId);
    List<Organisation> orgList=superhero.getOrgs();
    model.addAttribute("orgList", orgList);
    model.addAttribute("superhero", superhero);

    return "superheroDetails";
   } 
   
    @RequestMapping(value="/displayEditSuperheroForm", method=RequestMethod.GET)
    public String displayEditSuperheroForm(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("superheroId");
        int heroId = Integer.parseInt(heroIdParameter);
        Superhero superhero = serviceS.getSuperheroById(heroId);
        model.addAttribute("superhero", superhero);
        List<Superpower> powerList=serviceP.getAllSuperpowers();
        List<Organisation> orgList=serviceO.getAllOrganisations();
        model.addAttribute("powerList", powerList);
        model.addAttribute("orgList", orgList);
        return "/editSuperheroForm";
    }
 
    @RequestMapping(value = "/editSuperhero", method = RequestMethod.POST)
    public String editSuperhero(@ModelAttribute("superhero")Superhero superhero ,HttpServletRequest request,int[]organisationId) {
      
        List<Organisation> orgList=new ArrayList<>();
        List<Organisation> orgS=serviceO.getAllOrganisations();
        for (int id:organisationId){
         for (Organisation org:orgS){
         if(org.getOrganisationId()==id)
        
        orgList.add(org);}}
        superhero.setOrgs(orgList);
        
        Superpower superpower  = new Superpower();
        String powerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(powerIdParameter); 
        superpower.setSuperpowerId(superpowerId);
        superhero.setSuperpower(superpower);
        
        serviceS.updateSuperhero(superhero);
        
        return "redirect:displaySuperheroPage";
    }
    
    // add picture endpoint
    
  @RequestMapping(value="/addPicture", method=RequestMethod.POST)
    public String addPicture(HttpServletRequest request,
                    Model model,
                    @RequestParam("displayTitle") String displayTitle,
                    @RequestParam("picture") MultipartFile pictureFile
                    ) {
        
       
        // only save the pictureFile if the user actually uploaded something
        if (!pictureFile.isEmpty()) {
            try {
                // we want to put the uploaded image into the 
                // <pictureFolder> folder of our application. getRealPath
                // returns the full path to the directory under Tomcat
                // where we can save files.
                String savePath = request
                        .getSession()
                        .getServletContext()
                        .getRealPath("/") + PICTUREFOLDER;
                File dir = new File(savePath);
                // if <pictureFolder> directory is not there, 
                // go ahead and create it
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                // get the filename of the uploaded file - we'll use the
                // same name on the server.
                String filename = pictureFile.getOriginalFilename();
                // transfer the contents of the uploaded pictureFile to 
                // the server
                pictureFile.transferTo(new File(savePath + filename));

                // we successfully saved the pictureFile, now save a 
                // Picture to the DAO
                Picture picture = new Picture();
                picture.setFilename(PICTUREFOLDER + filename);
                picture.setTitle(displayTitle);
                serviceA.addPicture(picture);

                // redirect to home page to redisplay the entire album
               // return "redirect:home";
              return  "redirect:displaySuperheroPage";
            } catch (Exception e) {
                // if we encounter an exception, add the error message 
                // to the model and return back to the pictureFile upload 
                // form page
                model.addAttribute("errorMsg", "File upload failed: " + 
                        e.getMessage());
                return "superheroCreateForm";
            }
        } else {
            // if the user didn't upload anything, add the error 
            // message to the model and return back to the pictureFile 
            // upload form page
            model.addAttribute("errorMsg", 
                               "Please specify a non-empty file.");
            return "superheroCreateForm";
        }


    }
   
    
}