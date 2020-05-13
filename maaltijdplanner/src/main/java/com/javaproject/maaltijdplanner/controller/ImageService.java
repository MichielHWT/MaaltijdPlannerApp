package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Blob;

@Service
@Transactional
public class ImageService {
    @Autowired
    ImageRepository imr;

    public void addUploadedImage(Image newImage){
        imr.save(newImage);
    }

    public byte[] getImageByName(String imageName){
        Iterable<Image> allImages = imr.findAll();
        for(Image image : allImages){
            if(image.getFileName().equals(imageName)){
                return image.getBinaryImageFile();
            }
        }
        return null;
    }
}
