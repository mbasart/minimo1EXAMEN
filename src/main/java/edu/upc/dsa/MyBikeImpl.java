package edu.upc.dsa;

import com.sun.org.apache.xml.internal.utils.StringComparable;
import org.apache.log4j.Logger;

import java.util.*;


public class MyBikeImpl implements MyBike {
    private static MyBike instance;

    private Map<String, User> userMap;
    Station[] stations;

    final Logger log = Logger.getLogger(MyBikeImpl.class);

    private MyBikeImpl() {
        this.userMap = new HashMap<String, User>();
        this.stations = new Station[10];
    }

    public static MyBike getInstance() {
        if (instance==null) instance = new MyBikeImpl();
        return instance;
    }

    //afegir coses
    public void addUser (String idUser, String name, String surname){
        User user = new User(idUser,name,surname);
        userMap.put(user.getName(), user);
    }

    public void addStation(String idStation, String description, int max, double lat, double lon){
        Station station = new Station(idStation,description,max,lat,lon);
        for(int i1 = 0; i1 < stations.length; i1++){
            stations[i1] = station;
        }
    }

    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException{
        boolean encont = false;
        int i = 0;
        String stat = null;
        while(!encont && i < stations.length){
            if(idStation.equals(stations[i].getIdStation())){
                encont = true;
                stat = idStation;
            }else
                i++;
        }
        if(stat == null){
            log.error("This station not exist");
            throw new StationNotFoundException();
        }else{
            if(stations[i].getMax() <= 10){
                this.stations[i].addBikeStation(idBike,description,kms,idStation);
            }else{
                log.error("Error! Station full");
                throw new StationFullException();
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
        while(!encont && i < stations.length){
            if(idStation.equals(stations[i].getIdStation())){
                encont = true;
                stat = idStation;
            }else
                i++;
        }
        if (stat == null){
            log.error("This station not exist");
            throw new StationNotFoundException();
        } else {
            List<Bike> bikesOrderedKms = new LinkedList<Bike>();
            for (Bike bike : stations[i].getBikeList()) {
                bikesOrderedKms.add(bike);
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
        while(!encont && i < stations.length){
            if(stationId.equals(stations[i].getIdStation())){
                encont = true;
                stat = stationId;
            }else
                i++;
        }

        if (stat == null){
            log.error("This station not exist");
            throw new StationNotFoundException();
        }else{
            User user = this.userMap.get(userId);
            if(user == null){
                log.error("Error! usuari no existeix");
                throw new UserNotFoundException();
            } else {
                bike = stations[i].getBikeList().get(0);
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
            throw new UserNotFoundException();
        }else{
            bikesList = this.userMap.get(userId).getBikeList();
        }
        return bikesList;
    }

    public int numUsers(){
        log.info("Number of users: ");
        int numU = this.userMap.size();
        return  numU;
    }

    public int numStations(){
        log.info("number of stations: ");
        int numStations = this.stations.length;
        return numStations;
    }

    public int numBikes(String idStation) throws StationNotFoundException{
        log.info("Number of bikes: ");
        int numBikes;
        boolean encont = false;
        int i = 0;
        String stat = null;
        while(!encont && i < stations.length){
            if(idStation.equals(stations[i].getIdStation())){
                encont = true;
                stat = idStation;
            }else
                i++;
        }
        if (stat == null){
            log.error("This station not exist");
            throw new StationNotFoundException();
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
