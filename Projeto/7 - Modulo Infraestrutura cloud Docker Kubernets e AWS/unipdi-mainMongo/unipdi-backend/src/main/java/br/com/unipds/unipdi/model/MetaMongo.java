package br.com.unipds.unipdi.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class MetaMongo {
    @Id
    private String id;
    private String descricao;
    private boolean concluida;

    public MetaMongo() {}

    public MetaMongo(String descricao, boolean concluida) {
        this.id = new ObjectId().toString();
        this.descricao = descricao;
        this.concluida = concluida;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }
}
