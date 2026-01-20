package com.kubo;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity // anotation
public class Pessoa extends PanacheEntity {
    
    public String nome;
    public int anoNascimento;

    public static List<Pessoa> findByAno(int anoNascimento){

        return find("anoNascimento", anoNascimento).list();
    }




}
