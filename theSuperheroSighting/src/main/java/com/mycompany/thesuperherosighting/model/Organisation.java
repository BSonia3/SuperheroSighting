/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.model;

import java.util.Objects;

/**
 *
 * @author sonia
 */
public class Organisation {
   private int organisationId;
    private String orgName;
    private String orgStreet;
    private String orgCity;
    private String orgState;
    private String orgZipCode;
    private String contact; 

    public int getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(int organisationId) {
        this.organisationId = organisationId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgStreet() {
        return orgStreet;
    }

    public void setOrgStreet(String orgStreet) {
        this.orgStreet = orgStreet;
    }

    public String getOrgCity() {
        return orgCity;
    }

    public void setOrgCity(String orgCity) {
        this.orgCity = orgCity;
    }

    public String getOrgState() {
        return orgState;
    }

    public void setOrgState(String orgState) {
        this.orgState = orgState;
    }

    public String getOrgZipCode() {
        return orgZipCode;
    }

    public void setOrgZipCode(String orgZipCode) {
        this.orgZipCode = orgZipCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.organisationId;
        hash = 61 * hash + Objects.hashCode(this.orgName);
        hash = 61 * hash + Objects.hashCode(this.orgStreet);
        hash = 61 * hash + Objects.hashCode(this.orgCity);
        hash = 61 * hash + Objects.hashCode(this.orgState);
        hash = 61 * hash + Objects.hashCode(this.orgZipCode);
        hash = 61 * hash + Objects.hashCode(this.contact);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organisation other = (Organisation) obj;
        if (this.organisationId != other.organisationId) {
            return false;
        }
        if (!Objects.equals(this.orgName, other.orgName)) {
            return false;
        }
        if (!Objects.equals(this.orgStreet, other.orgStreet)) {
            return false;
        }
        if (!Objects.equals(this.orgCity, other.orgCity)) {
            return false;
        }
        if (!Objects.equals(this.orgState, other.orgState)) {
            return false;
        }
        if (!Objects.equals(this.orgZipCode, other.orgZipCode)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        return true;
    }
    
    
}
