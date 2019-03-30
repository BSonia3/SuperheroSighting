/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.thesuperherosighting.service;

import com.mycompany.thesuperherosighting.dao.AlbumDao;
import com.mycompany.thesuperherosighting.model.Picture;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author sonia
 */
public class AlbumServiceLayerInMemImpl implements AlbumServiceLayer{
   AlbumDao dao;
    
    @Inject
     AlbumServiceLayerInMemImpl(AlbumDao dao){
       this.dao = dao; 
     } 
    @Override
    public Picture addPicture(Picture picture) {
       return dao.addPicture(picture);
     }

    @Override
    public void deletePicture(int pictureId) {
        dao.deletePicture(pictureId);
    }

    @Override
    public Picture getPictureById(int pictureId) {
        return dao.getPictureById(pictureId);
    }

    @Override
    public List<Picture> getAllPictures() {
       return dao.getAllPictures();
    }
    
}
