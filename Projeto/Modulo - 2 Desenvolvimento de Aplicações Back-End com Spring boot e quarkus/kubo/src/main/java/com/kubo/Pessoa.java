package com.kubo;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity // anotation
public class Pessoa extends PanacheEntity {
    public String nome;
    public int anoNascimento;
}
