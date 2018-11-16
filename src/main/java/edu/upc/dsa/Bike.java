package edu.upc.dsa;

import java.util.List;

public class Bike {
    String idBike;
    String description;
    double kms;
    String idStation;

    public Bike(){
    }

    public Bike(String idBike, String description, double kms, String idStation){
        this.idBike = idBike;
        this.description = description;
        this.kms = kms;
    }

    public String getIdBike() {
        return idBike;
    }

    public void setIdBike(String idBike) {
        this.idBike = idBike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getKms() {
        return kms;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public String getBikeId() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }
}
