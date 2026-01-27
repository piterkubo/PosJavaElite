package br.com.kubo.events.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_user") // informando o nome da tabela
public class User {




    @Id  // Informando o id de userId
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Declarando o geenerated para o tipo auto incremente
    @Column(name = "user_id")// data annotation da coluna name
    private Integer userId;     // Integer faz com que mostra se o valor é null no banco

    @Column(name = "user_name", length = 255, nullable = false)
    private String name;

    @Column(name = "user_email", length = 255, nullable = false, unique = true)
    private String email;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
