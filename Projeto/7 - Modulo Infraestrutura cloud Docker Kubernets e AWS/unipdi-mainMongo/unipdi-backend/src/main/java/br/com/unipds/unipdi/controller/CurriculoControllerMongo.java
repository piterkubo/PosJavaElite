package br.com.unipds.unipdi.controller;

import br.com.unipds.unipdi.model.PessoaMongo;
import br.com.unipds.unipdi.repository.PessoaRepositoryMongo;
import br.com.unipds.unipdi.service.CurriculoServiceMongo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/curriculos")
public class CurriculoControllerMongo {

    private final CurriculoServiceMongo curriculoService;
    private final PessoaRepositoryMongo pessoaRepository;

    public CurriculoControllerMongo(CurriculoServiceMongo curriculoService, PessoaRepositoryMongo pessoaRepository) {
        this.curriculoService = curriculoService;
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping("/{matricula}/upload")
    public ResponseEntity<String> uploadCurriculo(
            @PathVariable String matricula,
            @RequestParam("file") MultipartFile file) {
        try {
            PessoaMongo pessoa = pessoaRepository.findByMatricula(matricula)
                    .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com matrícula " + matricula));

            curriculoService.uploadCurriculo(matricula, file);
            String url = curriculoService.gerarPresignedUrl(matricula, 5); // válido por 15 min
            pessoa.setCurriculoUrl(url);
            pessoaRepository.save(pessoa);

            return ResponseEntity.ok("Curriculo enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao enviar currículo: " + e.getMessage());
        }
    }

    @GetMapping("/{matricula}/url")
    public ResponseEntity<String> getCurriculoUrl(@PathVariable String matricula) {
        PessoaMongo pessoa = pessoaRepository.findByMatricula(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com matrícula " + matricula));

        if (pessoa.getCurriculoUrl() == null) {
            return null;
        }

        var url = gravarUrl(pessoa);
        return ResponseEntity.ok(url);
    }

    private String gravarUrl(PessoaMongo pessoa){
        String url = curriculoService.gerarPresignedUrl(pessoa.getMatricula(), 5); // válido por 15 min
        pessoa.setCurriculoUrl(url);
        pessoaRepository.save(pessoa);
        return url;
    }
}
