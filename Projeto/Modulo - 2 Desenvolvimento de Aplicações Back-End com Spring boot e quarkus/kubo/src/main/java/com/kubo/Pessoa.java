package com.kubo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;


@Entity // anotation
public class Pessoa extends PanacheEntity {
    public String nome;
    public int anoNascimento;

import java.util.List;

@Entity // anotation

public class Pessoa extends PanacheEntity {
    public String nome;
    public int anoNascimento;



    public static List<Pessoa> findByAno(int anoNascimento){

        return find("anoNascimento", anoNascimento).list();
    }




}
