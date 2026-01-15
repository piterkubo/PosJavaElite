package com.kubo;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

// Fazendo o registro do rest client com endpoint
@RegisterRestClient(baseUri = "https://swapi.info/api/")

public interface DadosService {

    @GET // metodo
    @Produces(MediaType.APPLICATION_JSON) // tipo de retorno
    @Path("starships") // declarando o path
    public String getstarships();


}
