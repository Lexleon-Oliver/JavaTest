package com.sigabem.fretecalcapi.resource;

import com.sigabem.fretecalcapi.dto.request.FreteDTO;
import com.sigabem.fretecalcapi.dto.response.MessageResponse;
import com.sigabem.fretecalcapi.service.FreteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/v1/fretes")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FreteResource {

    private FreteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse calcularFrete(@RequestBody @Valid FreteDTO freteDTO){
        return service.calcularFrete(freteDTO);

    }
}
