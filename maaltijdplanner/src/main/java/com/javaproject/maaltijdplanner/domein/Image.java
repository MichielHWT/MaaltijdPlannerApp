package com.javaproject.maaltijdplanner.domein;

import javax.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fileName;
    @Column(name = "binaryImageFile", columnDefinition="BLOB")
    byte[] binaryImageFile;

    public Image(){

    }
    public Image(String fileName, byte[] binaryImageFile){
        this.fileName = fileName;
        this.binaryImageFile = binaryImageFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getBinaryImageFile() {
        return binaryImageFile;
    }

    public void setBinaryImageFile(byte[] binaryImageFile) {
        this.binaryImageFile = binaryImageFile;
    }
}
