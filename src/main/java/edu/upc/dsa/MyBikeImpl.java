package edu.upc.dsa;

import org.apache.log4j.Logger;

import java.util.*;


public class MyBikeImpl implements MyBike {
    private static MyBike instance;

    private Map<String, User> userMap;
    Station[] stations;
    private int sizeArr;

    final Logger log = Logger.getLogger(MyBikeImpl.class);

    private MyBikeImpl() {
        this.userMap = new HashMap<String, User>();
        this.stations = new Station[10];
        this.sizeArr =0;
    }

    public static MyBike getInstance() {
        if (instance==null) instance = new MyBikeImpl();
        return instance;
    }

    //afegir coses
    public void addUser (String idUser, String name, String surname){
        User user = new User(idUser,name,surname);
        userMap.put(user.getIdUser(), user);
        log.info("Nom del usuari: "+name);
    }

    public void addStation(String idStation, String description, int max, double lat, double lon){
        Station station = new Station(idStation,description,max,lat,lon);
        int pos2 =0;
        int cont =0;
        while (stations[pos2] != null){
            cont++;
            pos2++;
        }
        stations[cont]=station;
        sizeArr++;

    }

    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException{
        boolean encont = false;
        int i = 0;
        String stat = null;
        while(!encont && i < sizeArr){
            if(stations[i].getIdStation().equals((idStation))){
                encont = true;
                stat = idStation;
            }else
                i++;
        }
        if(stat == null){
            log.error("This station not exist");
            throw new StationNotFoundException("Estacio no trobada");
        }else{
            if(stations[i].getMax() > stations[i].getBikeList().size()){
                this.stations[i].addBikeStation(idBike,description,kms,idStation);
            }else{
                log.error("Error! Station full");
                throw new StationFullException("L estacio esta plena");
            }
        }
    }

    //fi afegir coses

    //fem coses amb les bicis, usuaris i stations
    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException{
        log.info("Bicis ordenades per kms: ");
        boolean encont = false;
        int i = 0;
        String stat = null;
        while(!encont && i < sizeArr){
            if(stations[i].getIdStation().equals((idStation))){
                encont = true;
                stat = idStation;
            }else
                i++;
        }
        if (stat == null){
            log.error("This station not exist");
            throw new StationNotFoundException("Estacio no trobada");
        } else {
            List<Bike> bikesOrderedKms = new LinkedList<Bike>();
            for (Bike bike : stations[i].getBikeList()) {
                bikesOrderedKms.add(bike);
                log.info("Nom de la bici: " + bike.getBikeId());
            }
            Collections.sort(bikesOrderedKms, (o1, o2) -> (int) (o1.getKms() - o2.getKms()));
            return bikesOrderedKms;
        }
    }

    public Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException{
        log.info("Obtenir primera bici: ");
        Bike bike;
        boolean encont = false;
        int i = 0;
        String stat = null;
        while(!encont && i < sizeArr){
            if(stations[i].getIdStation().equals((stationId))){
                encont = true;
                stat = stationId;
            }else
                i++;
        }

        if (stat == null){
            log.error("This station not exist");
            throw new StationNotFoundException("Estacio no trobada");
        }else{
            User user = this.userMap.get(userId);
            if(user == null){
                log.error("Error! usuari no existeix");
                throw new UserNotFoundException("Usuari no trobat");
            } else {
                Bike bike2 = stations[i].getBikeOfStation();
                user.addBikeUser2(bike2);
                bike = bike2;
            }
        }

        return bike;
    }

    public List<Bike> bikesByUser(String userId) throws UserNotFoundException{
        log.info("list of a bikes of a User: ");
        User user = this.userMap.get(userId);
        List<Bike> bikesList;
        if(user == null){
            log.error("Error! usuari no existeix");
            throw new UserNotFoundException("Usuari no trobat");
        }else{
            bikesList = this.userMap.get(userId).getBikeList();
        }
        return bikesList;
    }

    public int numUsers(){
        log.info("Number of users: ");
        int numU = userMap.size();
        return  numU;
    }

    public int numStations(){
        log.info("number of stations: ");
        int cont=0;
        for (int i = 0; i< stations.length; i++){
            if(stations[i] != null){
                cont++;
            }
        }
        return cont;
    }

    public int numBikes(String idStation) throws StationNotFoundException{
        log.info("Number of bikes: ");
        int numBikes;
        boolean encont = false;
        int i = 0;
        String stat = null;
        while(!encont && i < sizeArr){
            if(idStation.equals(stations[i].getIdStation())){
                encont = true;
                stat = idStation;
            }else
                i++;
        }
        if (stat == null){
            log.error("This station not exist");
            throw new StationNotFoundException("Estacio no trobada");
        } else{
            numBikes = stations[i].getBikeList().size();
        }
        return numBikes;
    }

    public void clear(){

        instance = new MyBikeImpl();
    }
    //fi fer coses
}
