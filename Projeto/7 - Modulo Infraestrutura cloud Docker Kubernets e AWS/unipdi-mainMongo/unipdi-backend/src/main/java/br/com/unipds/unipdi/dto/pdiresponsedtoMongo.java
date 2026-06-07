package br.com.unipds.unipdi.dto;

import java.time.LocalDate;
import java.util.List;

public record pdiresponsedtoMongo(
        String id,
        String pessoaMatricula,
        LocalDate dataInicio,
        LocalDate dataFim,
        String descricao,
        List<MetaResponseDtoMongo> metas
) {
}
