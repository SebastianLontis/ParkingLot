package com.parking.parkinglot.entities;

import jakarta.persistence.*;

@Entity
public class CarPhoto {

    private Long id;

    String filename;

    String fileType;

    byte[] fileContent;

    Car car;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    @OneToOne
    public Car getCar() {
        return car;
    }

    public String getFilename() {
        return filename;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}