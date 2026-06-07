package br.com.unipds.unipdi.service;

import br.com.unipds.unipdi.dto.PessoaRequestDtoMongo;
import br.com.unipds.unipdi.dto.PessoaResponseDtoMongo;
import br.com.unipds.unipdi.model.PessoaMongo;
import br.com.unipds.unipdi.repository.PessoaRepositoryMongo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceMongo {
    private final PessoaRepositoryMongo pessoaRepository;

    public PessoaServiceMongo(PessoaRepositoryMongo pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaResponseDtoMongo cadastrarPessoa(PessoaRequestDtoMongo dto) {
        if (pessoaRepository.existsByMatricula(dto.matricula())) {
            throw new IllegalArgumentException("Matrícula já cadastrada: " + dto.matricula());
        }

        PessoaMongo pessoa = new PessoaMongo(dto.matricula(), dto.nome());
        PessoaMongo salva = pessoaRepository.save(pessoa);

        return new PessoaResponseDtoMongo(salva.getId(), salva.getMatricula(), salva.getNome(), salva.getCurriculoUrl());
    }

    public PessoaResponseDtoMongo buscarPorMatricula(String matricula) {
        PessoaMongo pessoa = pessoaRepository.findByMatricula(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com matrícula " + matricula));

        return new PessoaResponseDtoMongo(pessoa.getId(), pessoa.getMatricula(), pessoa.getNome(), pessoa.getCurriculoUrl());
    }

    public List<PessoaResponseDtoMongo> buscarTodos() {
        List<PessoaMongo> pessoas = pessoaRepository.findAll();
        return pessoas.stream().map(p ->
                new PessoaResponseDtoMongo(p.getId(),p.getMatricula(), p.getNome(), p.getCurriculoUrl()))
                .toList();
    }
}
