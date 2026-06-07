package br.com.unipds.unipdi.dto;

import jakarta.validation.constraints.NotBlank;

public record PessoaRequestDtoMongo(
        @NotBlank String matricula,
        @NotBlank String nome
) {
}
