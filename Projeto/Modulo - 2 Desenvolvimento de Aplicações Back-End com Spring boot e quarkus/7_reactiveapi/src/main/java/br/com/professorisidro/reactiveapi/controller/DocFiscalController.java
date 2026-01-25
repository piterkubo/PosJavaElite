package br.com.professorisidro.reactiveapi.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.professorisidro.reactiveapi.dto.ProtocoloDTO;
import br.com.professorisidro.reactiveapi.dto.RequisicaoDTO;
import br.com.professorisidro.reactiveapi.model.DocFiscal;
import br.com.professorisidro.reactiveapi.service.IDocFiscalService;
import reactor.core.publisher.Mono;

@RestController
public class DocFiscalController {
	
	private IDocFiscalService service;

	public DocFiscalController(IDocFiscalService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/consultar/{protocolo}")
	public ResponseEntity<DocFiscal> consultar(@PathVariable String protocolo){
		return ResponseEntity.ok(service.consultarPorProtocolo(protocolo));
	}
	
	@PostMapping("/solicitar")
	public Mono<ResponseEntity<ProtocoloDTO>> solicitar(@RequestBody RequisicaoDTO req){
	   String idProtocolo = UUID.randomUUID().toString();
	   service.realizarAutorizacaoAPIExterna(req.idCliente(), req.idServico(), idProtocolo);
	   return Mono.just(ResponseEntity.accepted().body(new ProtocoloDTO(idProtocolo)));
	}

}
