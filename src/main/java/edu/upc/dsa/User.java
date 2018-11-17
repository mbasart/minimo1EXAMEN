package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

public class User {
    String idUser;
    String name;
    String surname;
    private List<Bike> bikeList;

    public User(){

    }

    public User(String idUser, String name, String surname){
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.bikeList = new LinkedList<Bike>();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Bike> getBikeList() {
        return bikeList;
    }

    public void setBikeList(List<Bike> bikeList) {
        this.bikeList = bikeList;
    }

    public void addBikeUser(String idBike, String description, double kms, String idStation){
        Bike b = new Bike(idBike,description, kms,idStation);
        this.bikeList.add(b);

    }

    public void addBikeUser2 (Bike bike){
        this.bikeList.add(bike);
    }
}
