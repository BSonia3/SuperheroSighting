/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.dao.OrganisationDao;
import com.mycompany.thesuperherosighting.model.Organisation;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author sonia
 */
public class OrganizationServiceLayerDbImpl implements OrganizationServiceLayer {
   OrganisationDao dao;
 
 @Inject 
   OrganizationServiceLayerDbImpl(OrganisationDao dao){
       this.dao = dao;
   } 
    @Override
    public void addOrganisation(Organisation organisation) {
        dao.addOrganisation(organisation);
    }

    @Override
    public void deleteOrganisation(int organisationId) {
       dao.deleteOrganisation(organisationId);
    }

    @Override
    public void updateOrganisation(Organisation organisation) {
       dao.updateOrganisation(organisation);
    }

    @Override
    public Organisation getOrganisationById(int organisationId) {
       return dao.getOrganisationById(organisationId);
    }

    @Override
    public List<Organisation> getAllOrganisations() {
       return dao.getAllOrganisations();
    }  
}
