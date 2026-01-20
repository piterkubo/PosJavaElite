package com.kubo;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/kubo") // representa um ponto de entrada da aplicação
@Produces(MediaType.APPLICATION_JSON) //stilo como o valor será lancado na API
@Consumes(MediaType.APPLICATION_JSON) //produz informações textplain e consome outros formatos

public class APIResource {

    private int num = 0;

    @GET    // associação metodo http

    public int getNum(){
        return num;

    }

    @POST
    public void addNum(){

        num++;
    }

    @DELETE
    public void removeNum(){

        num--;
    }

    @PUT
    public void alterarNum(int num){

        this.num = num;
    }


}



