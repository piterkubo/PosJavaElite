package br.com.unipds.unipdi.service;

import br.com.unipds.unipdi.repository.PdiRepositoryMongo;
import br.com.unipds.unipdi.repository.PessoaRepositoryMongo;
import br.com.unipds.unipdi.dto.MetaRequestDtoMongo;
import br.com.unipds.unipdi.dto.MetaResponseDtoMongo;
import br.com.unipds.unipdi.dto.PdiRequestDtoMongo;
import br.com.unipds.unipdi.dto.pdiresponsedtoMongo;
import br.com.unipds.unipdi.model.MetaMongo;
import br.com.unipds.unipdi.model.PdiMongo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdiServiceMongo {
    private final PdiRepositoryMongo pdiRepository;
    private final PessoaRepositoryMongo pessoaRepository;

    public PdiServiceMongo(PdiRepositoryMongo pdiRepository, PessoaRepositoryMongo pessoaRepository) {
        this.pdiRepository = pdiRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public pdiresponsedtoMongo criarPdi(PdiRequestDtoMongo dto) {
        pessoaRepository.findByMatricula(dto.matricula())
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada para matrícula " + dto.matricula()));

        PdiMongo pdi = new PdiMongo(dto.matricula(), dto.dataInicio(), dto.dataFim(), dto.descricao());
        pdiRepository.save(pdi);

        return toResponse(pdi);
    }

    public List<pdiresponsedtoMongo> buscarPorMatricula(String matricula) {
        return pdiRepository.findByPessoaMatricula(matricula).stream()
                .map(this::toResponse)
                .toList();
    }

    public pdiresponsedtoMongo adicionarMeta(String pdiId, MetaRequestDtoMongo metaDto) {
        PdiMongo pdi = pdiRepository.findById(pdiId)
                .orElseThrow(() -> new RuntimeException("PDI não encontrado"));

        MetaMongo meta = new MetaMongo(metaDto.descricao(), metaDto.concluida());
        pdi.addMeta(meta);
        pdiRepository.save(pdi);

        return toResponse(pdi);
    }

    private pdiresponsedtoMongo toResponse(PdiMongo pdi) {
        return new pdiresponsedtoMongo(
                pdi.getId(),
                pdi.getPessoaMatricula(),
                pdi.getDataInicio(),
                pdi.getDataFim(),
                pdi.getDescricao(),
                pdi.getMetas().stream()
                        .map(m -> new MetaResponseDtoMongo(m.getId(), m.getDescricao(), m.isConcluida()))
                        .toList()
        );
    }

    public List<pdiresponsedtoMongo> buscarTodos() {
        return pdiRepository.findAll().stream()
                .map(p -> toResponse(p))
                .toList();

    }
}
