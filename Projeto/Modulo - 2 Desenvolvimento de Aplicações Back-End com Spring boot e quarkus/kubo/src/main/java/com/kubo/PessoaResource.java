package com.kubo;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class PessoaResource {

    @GET
    public List<Pessoa> getPessoa(){
        return Pessoa.listAll();
    }


    @POST
    @Transactional
    public Pessoa createPessoa(Pessoa pessoa){
        pessoa.id = null;
        pessoa.persist();
        return pessoa;

    }


    @DELETE
    @Transactional
    public void deletePessoa(int id){
        Pessoa.deleteById(id);

    }

    @PUT
    @Transactional
    public Pessoa updatePessoa(Pessoa pessoa){
        Pessoa p  = Pessoa.findById(pessoa.id);
        p.nome = pessoa.nome;
        p.anoNascimento = pessoa.anoNascimento;

        p.persist();

        return p;
    }

}
