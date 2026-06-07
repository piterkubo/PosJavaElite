package br.com.unipds.unipdi.controller;

import br.com.unipds.unipdi.dto.MetaRequestDtoMongo;
import br.com.unipds.unipdi.dto.PdiRequestDtoMongo;
import br.com.unipds.unipdi.dto.pdiresponsedtoMongo;
import br.com.unipds.unipdi.service.PdiServiceMongo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pdis")
public class PdiControllerMongo {
    private final PdiServiceMongo pdiService;

    public PdiControllerMongo(PdiServiceMongo pdiService) {
        this.pdiService = pdiService;
    }

    @PostMapping
    public pdiresponsedtoMongo criarPdi(@RequestBody PdiRequestDtoMongo dto) {
        return pdiService.criarPdi(dto);
    }

    @GetMapping("/{matricula}")
    public List<pdiresponsedtoMongo> buscarPorMatricula(@PathVariable String matricula) {
        return pdiService.buscarPorMatricula(matricula);
    }

    @GetMapping
    public List<pdiresponsedtoMongo> buscarTodos() {
        return pdiService.buscarTodos();
    }

    @PostMapping("/{pdiId}/metas")
    public pdiresponsedtoMongo adicionarMeta(@PathVariable String pdiId, @RequestBody MetaRequestDtoMongo dto) {
        return pdiService.adicionarMeta(pdiId, dto);
    }
}
