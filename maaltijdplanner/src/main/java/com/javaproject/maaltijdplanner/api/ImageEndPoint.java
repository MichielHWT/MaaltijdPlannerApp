package com.javaproject.maaltijdplanner.api;

import com.javaproject.maaltijdplanner.controller.ImageService;
import com.javaproject.maaltijdplanner.domein.Image;
import com.javaproject.maaltijdplanner.domein.Ingredient;
import com.javaproject.maaltijdplanner.wrapper.FormWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ImageEndPoint {
    @Autowired
    ImageService ims;

    @PostMapping("/addUploadedImage")
    public void addUploadedImage(@ModelAttribute FormWrapper model) throws IOException {
        System.out.println(model);
        try{
            byte[] imageFileBinary = model.getImage().getBytes();
            String imageFileName = model.getTitle();
            ims.addUploadedImage(new Image(imageFileName, imageFileBinary));
            System.out.println("Send image file succesfull");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
