package com.javaproject.maaltijdplanner.wrapper;

import org.springframework.web.multipart.MultipartFile;
/*
    https://stackoverflow.com/questions/49845355/spring-boot-controller-upload-multipart-and-json-to-dto
 */

public class FormWrapper {
    private MultipartFile image;
    private String title;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
