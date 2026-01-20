package br.com.kubo.helloworld.controller;

import br.com.kubo.helloworld.model.Produto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // declaração para requisição web

public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
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
        IO.println("Produto Recebido");
        IO.println(p.getId() + "-" + p.getNome() + "-" + p.getPreco());
        return "OK";

    }


}
