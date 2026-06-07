package br.com.unipds.unipdi.dto;

public record PessoaResponseDtoMongo(
        String id,
        String matricula,
        String nome,
        String curriculoUrl
) {
}
