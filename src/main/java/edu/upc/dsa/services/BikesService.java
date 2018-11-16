package edu.upc.dsa.services;

import edu.upc.dsa.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/bikes", description = "Endpoint to Bikes Service")
@Path("/bikes")
public class BikesService {

    //  http://localhost:8080/dsaApp/swagger.json
    //  http://localhost:8080/swagger/

    private MyBike tm;


    public BikesService() throws Exception {
        this.tm = MyBikeImpl.getInstance();
        this.tm.addUser("user1", "Juan", "Lopex");
        this.tm.addStation("Station1","description:: station1", 10, 3, 3);
        this.tm.addStation("Station2","description:: station2", 10, 3, 3);
        this.tm.addBike("bike101", "descripton", 25.45, "Station1");
        this.tm.addBike("bike102", "descripton", 70.3, "Station1");
        this.tm.addBike("bike103", "descripton", 10.2, "Station1");
        this.tm.addBike("bike201", "descripton", 1325.45, "Station2");
        this.tm.addBike("bike202", "descripton", 74430.3, "Station2");
        this.tm.addBike("bike203", "descripton", 1320.2, "Station2");
    }

    //coses per les bicis
    @POST
    @ApiOperation(value = "create a new user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= User.class),
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(String idUser, String name, String surname) {
        this.tm.addUser(idUser,name,surname);
        return Response.status(201).entity(idUser).build();
    }

    @POST
    @ApiOperation(value = "create a new station", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Station.class),
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newStation(String idStation, String description, int max, double lat, double lon) {
        this.tm.addStation(idStation,description,max,lat,lon);
        return Response.status(201).entity(idStation).build();
    }

    @POST
    @ApiOperation(value = "create a new bike in station", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Bike.class),
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newBike(String idBike, String description, double kms, String idStation) throws Exception{
        this.tm.addBike(idBike,description,kms,idStation);
        return Response.status(201).entity(idStation).build();
    }

    @GET
    @ApiOperation(value = "get all bikes ordered by kms", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class, responseContainer="List"),
    })
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikesKms(@PathParam("name") String name) throws Exception {

        List<Bike> bikes = this.tm.bikesByStationOrderByKms(name);
        GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikes) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get the first bike of a station", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class, responseContainer="List"),
    })
    @Path("/{stationId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFirstBike(@PathParam("stationId") String stationId,@PathParam("userId") String userId) throws Exception {

        Bike bike = this.tm.getBike(stationId,userId);
        return Response.status(201).entity(bike).build()  ;

    }
}