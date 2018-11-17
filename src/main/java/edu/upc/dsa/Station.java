package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

public class Station {
    String idStation;
    String description;
    int max;
    double lat;
    double lon;
    private List<Bike> bikeList;

    public Station(){

    }

    public Station(String idStation, String description, int max, double lat, double lon){
        this.idStation = idStation;
        this.description = description;
        this.max = max;
        this.lat = lat;
        this.lon = lon;
        this.bikeList = new LinkedList<Bike>();
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void addBikeStation(String idBike, String description, double kms, String idStation){
        Bike b = new Bike(idBike,description, kms,idStation);
        this.bikeList.add(b);

    }

    public List<Bike> getBikeList() {
        return bikeList;
    }

    public Bike getBikeOfStation(){
        Bike bike = bikeList.get(0);
        bikeList.remove(0);
        return bike;
    }

    public void setBikeList(List<Bike> bikeList) {
        this.bikeList = bikeList;
    }
}
