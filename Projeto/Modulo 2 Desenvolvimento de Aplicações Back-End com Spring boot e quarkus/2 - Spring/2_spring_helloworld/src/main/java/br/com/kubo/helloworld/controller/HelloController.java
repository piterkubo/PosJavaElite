package br.com.kubo.helloworld.controller;

import br.com.kubo.helloworld.model.Produto;
import br.com.kubo.helloworld.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // declaração para requisição web

public class HelloController {

    @Autowired
    @Qualifier("V2")
    private IMessageService service;


    @GetMapping("/hello")
    public String sayHello(){

        return service.sayCustomMessage("Hello World");
    }


    @GetMapping("/produto")
    public Produto getProduto()
    {
        Produto p =  new Produto();
        p.setId(12345);
        p.setNome("Computador");
        p.setPreco(1500.0);

        return p;
    }


    @PostMapping("/produto")
    public String addNewProdutc(@RequestBody Produto p){
       System.out.println("Produto Recebido");
        System.out.println(p.getId() + "-" + p.getNome() + "-" + p.getPreco());
        return "OK";

    }


}
