package com.javaproject.maaltijdplanner.controller;

import com.javaproject.maaltijdplanner.domein.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ImageService {
    @Autowired
    ImageRepository imr;

    public void addUploadedImage(Image newImage){
        imr.save(newImage);
    }
}
