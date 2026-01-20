package com.kubo;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class ReadNessCheck implements HealthCheck {

    @RestClient
    DadosService dadosService;



    @Override
    public HealthCheckResponse call() {

        if(dadosService.getstarships().contains(DadosService.MSG_ERRO)){
            return HealthCheckResponse.down("Error");


        }

        else{
            return HealthCheckResponse.up("Ok");
        }


    }
}