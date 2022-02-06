package com.sigabem.fretecalcapi.frete;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "frete", url = "https://viacep.com.br/")
public interface FreteClient {

    @GetMapping("/ws/{cep}/json")
    Cep listarCep(@PathVariable int cep);
}

