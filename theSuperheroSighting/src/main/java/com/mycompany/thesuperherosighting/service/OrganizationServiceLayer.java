/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.model.Organisation;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface OrganizationServiceLayer {
  public void addOrganisation(Organisation organisation);

    public void deleteOrganisation(int organisationId);

    public void updateOrganisation(Organisation organisation);

    public Organisation getOrganisationById(int organisationId);

    public List<Organisation> getAllOrganisations();
    
     
    
}
