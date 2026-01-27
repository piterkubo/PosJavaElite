package com.kubo;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("StarWars")
@Produces(MediaType.APPLICATION_JSON)
public class StarWarsResource {

    //Injetando a dependencia do DadosService
    @RestClient
    DadosService dadosService;


    @GET
    public String getstarships(){

        return dadosService.getstarships();

    }
}
