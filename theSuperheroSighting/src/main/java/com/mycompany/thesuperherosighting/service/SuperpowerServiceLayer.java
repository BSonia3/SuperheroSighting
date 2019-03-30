/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.model.Superpower;
import java.util.List;

/**
 *
 * @author sonia
 */
public interface SuperpowerServiceLayer {
    public void addSuperpower(Superpower superpower);

    public void deleteSuperpower(int superpowerId);

    public void updateSuperpower(Superpower superpower);
    
    public Superpower getSuperpower(int id);
    
    public List<Superpower> getAllSuperpowers();
    
}
