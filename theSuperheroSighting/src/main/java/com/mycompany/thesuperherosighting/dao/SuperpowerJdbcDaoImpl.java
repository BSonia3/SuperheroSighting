/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.dao;

import com.mycompany.thesuperherosighting.model.Superpower;
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
public class SuperpowerJdbcDaoImpl implements SuperpowerDao {
  //Superpower prepared statement
    private static final String SQL_INSERT_SUPER_POWER = "insert into superpower (superpower) values (?)";
    private static final String SQL_SELECT_SUPER_POWER_BY_ID = "select * from superpower where superpower_id = ?";
    private static final String SQL_SELECT_ALL_SUPER_POWERS = "select * from superpower";
    private static final String SQL_UPDATE_SUPER_POWER_BY_ID = "update SuperPower set superpower = ? where superpower_id= ?";
    private static final String SQL_DELETE_SUPER_POWER_BY_ID = "delete from superpower where superpower_id = ?";
    
     //set up for setter injection of a JDBC Template
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperpower(Superpower superpower) {
       jdbcTemplate.update(SQL_INSERT_SUPER_POWER, superpower.getSuperpower());
       int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        superpower.setSuperpowerId(id);
    }

    @Override
     @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperpower(int superpowerId) {
        jdbcTemplate.update(SQL_DELETE_SUPER_POWER_BY_ID, superpowerId);
    }

    @Override
    public void updateSuperpower(Superpower superpower) {
        jdbcTemplate.update(SQL_UPDATE_SUPER_POWER_BY_ID, superpower.getSuperpower(), superpower.getSuperpowerId()
        );}
    
    @Override
    public Superpower getSuperpower(int superpowerId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_SUPER_POWER_BY_ID, new SuperpowerMapper(), superpowerId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    @Override
    public List<Superpower> getAllSuperpowers() {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPER_POWERS, new SuperpowerMapper());
    }
    //superpowerMapper
    private static final class SuperpowerMapper implements RowMapper<Superpower> {
        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
           Superpower sp = new Superpower();
            sp.setSuperpowerId(rs.getInt("superpower_id"));
            sp.setSuperpower(rs.getString("Superpower"));
            return sp;
        }
    }
    
}
