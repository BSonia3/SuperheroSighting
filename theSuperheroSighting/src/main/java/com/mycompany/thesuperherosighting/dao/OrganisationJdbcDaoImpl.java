/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.dao;

import com.mycompany.thesuperherosighting.model.Organisation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sonia
 */
public class OrganisationJdbcDaoImpl implements OrganisationDao{
  //organisation prepared statement  
    private static final String SQL_INSERT_ORGANISATION = "insert into organisation "
            + "(org_name, org_address, org_city , org_state,org_zipcode, org_contact) "
            + "values (?,?,?,?,?,?)";
    private static final String SQL_SELECT_ORGANISATION = "select * from organisation where organisation_id  = ?";
    private static final String SQL_SELECT_ALL_ORGANISATIONS = "select * from organisation";
    private static final String SQL_UPDATE_ORGANISATION = "update organisation "
            + "set org_name = ?, org_address = ?, org_city = ?, org_state = ?, org_zipcode = ?, org_contact = ?"
            + "where organisation_id = ?";
    private static final String SQL_DELETE_ORGANISATION = "delete from organisation where organisation_id = ?";
    private static final String SQL_SELECT_ORGORGANISATIONS_BY_SUPERHERO_ID =
            "select o.* from organization o" +
            " join superhero_organisation so on superhero_id" +
            " where o.organization_id=so.organization_id"+
            " and so.superhero_id=?;"; 
    
    // set up jdbcTemplate
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate =  jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganisation(Organisation organisation) {
        jdbcTemplate.update(SQL_INSERT_ORGANISATION,
                organisation.getOrgName(),
                organisation.getOrgStreet(),
                organisation.getOrgCity(),
                organisation.getOrgState(),
                organisation.getOrgZipCode(),
                organisation.getContact());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
            organisation.setOrganisationId(id);
    }

    @Override
    public void deleteOrganisation(int organisationId) {
        jdbcTemplate.update(SQL_DELETE_ORGANISATION,organisationId);
    }

    @Override
    public void updateOrganisation(Organisation organisation) {
       jdbcTemplate.update(SQL_UPDATE_ORGANISATION,
               organisation.getOrgName(),
               organisation.getOrgStreet(),
               organisation.getOrgCity(),
               organisation.getOrgState(),
               organisation.getOrgZipCode(),
               organisation.getContact(),
               organisation.getOrganisationId()); 
    }

    @Override
    public Organisation getOrganisationById(int organisationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANISATION, new OrganisationMapper(), organisationId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Organisation> getAllOrganisations() {
       return jdbcTemplate.query(SQL_SELECT_ALL_ORGANISATIONS, new OrganisationMapper());
      
    }

    @Override
    public List<Organisation> getAllOrganisationsOfSuperhero(int superheroId) {
         return jdbcTemplate.query(SQL_SELECT_ORGORGANISATIONS_BY_SUPERHERO_ID,new OrganisationMapper(),superheroId);
    }
    
    
    //mapper
    private static final class OrganisationMapper implements RowMapper<Organisation> {
        @Override
        public Organisation mapRow(ResultSet rs, int i) throws SQLException {
            Organisation org = new Organisation();
            org.setOrganisationId(rs.getInt("organisation_id"));
            org.setOrgName(rs.getString("org_name"));
            org.setOrgStreet(rs.getString("org_address"));
            org.setOrgCity(rs.getString("org_city"));
            org.setOrgState(rs.getString("org_state"));
            org.setOrgZipCode(rs.getString("org_zipcode"));
            org.setContact(rs.getString("org_contact"));
            return org;
        }
    }
    
  
}
