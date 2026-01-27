package br.com.professorisidro.reactiveapi.service;

import br.com.professorisidro.reactiveapi.model.DocFiscal;

public interface IDocFiscalService {

	public void realizarAutorizacaoAPIExterna(Long idCliente, Integer idServico, String protocolo);
	public DocFiscal consultarPorProtocolo(String protocolo);
}
