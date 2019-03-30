/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.dao.UserDao;
import com.mycompany.thesuperherosighting.model.User;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author sonia
 */
public class UserServiceLayerDbImpl implements UserServiceLayerDb{
    
   UserDao dao;
    
   @Inject
   UserServiceLayerDbImpl(UserDao dao){
     this.dao = dao;   
    }

    @Override
    public User addUser(User newUser) {
      return dao.addUser(newUser);
    }

    @Override
    public void deleteUser(String username) {
        dao.deleteUser(username);
    }

    @Override
    public List<User> getAllUsers() {
       return dao.getAllUsers();
    }
    
}
