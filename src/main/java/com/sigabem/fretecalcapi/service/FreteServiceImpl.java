package com.sigabem.fretecalcapi.service;

import com.sigabem.fretecalcapi.dto.request.FreteDTO;
import com.sigabem.fretecalcapi.dto.response.MessageResponse;
import com.sigabem.fretecalcapi.entity.Frete;
import com.sigabem.fretecalcapi.exception.InputInvalidException;
import com.sigabem.fretecalcapi.frete.Cep;
import com.sigabem.fretecalcapi.frete.FreteClient;
import com.sigabem.fretecalcapi.mapper.FreteMapper;
import com.sigabem.fretecalcapi.repository.FreteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FreteServiceImpl implements FreteService {
    private final FreteRepository repository;
    private final FreteClient freteClient;
    private final FreteMapper mapper = FreteMapper.INSTANCE;

    @Override
    public MessageResponse calcularFrete(FreteDTO freteDTO) {
        validarCep(freteDTO.getCepOrigem(), freteDTO.getCepDestino());
        int cep = Integer.parseInt(freteDTO.getCepOrigem().replace("-",""));
        Cep cepRemetente = freteClient.listarCep(cep);
        if (verificarCep(cepRemetente)) {
            freteDTO.setCepOrigem(cepRemetente.getCep());
            cep = Integer.parseInt(freteDTO.getCepDestino().replace("-",""));
            Cep cepDestinatario = freteClient.listarCep(cep);
            if (verificarCep(cepDestinatario)) {
                freteDTO.setCepDestino(cepDestinatario.getCep());
                freteDTO.setDataPrevistaEntrega(calcularEntrega(cepRemetente, cepDestinatario));
                try {
                    double peso = Double.parseDouble(freteDTO.getPeso().replace(",","."));
                }catch (Exception erro){
                    throw new InputInvalidException("Valor informado para peso é inválido");
                }
                freteDTO.setVlTotalFrete(calcularValorFrete(freteDTO.getDataPrevistaEntrega(), freteDTO.getPeso().replace(",", ".")));
                Frete freteParaSalvar = mapper.toModel(freteDTO);
                Frete freteSalvo = repository.save(freteParaSalvar);
                String valorFrete = String.format("%.2f", freteSalvo.getVlTotalFrete()).replace(".", ",");
                return MessageResponse
                        .builder()
                        .cepDestino(freteSalvo.getCepDestino())
                        .cepOrigem(freteSalvo.getCepOrigem())
                        .dataPrevistaEntrega(freteSalvo.getDataPrevistaEntrega())
                        .vlTotalFrete("R$ " + valorFrete)
                        .build();
            } else {
                throw new InputInvalidException("Cep do destinatário digitado é inválido");
            }
        } else {
            throw new InputInvalidException("Cep do remetente digitado é inválido");
        }
    }

    private void validarCep(String cepOrigem, String cepDestino) {
        try {
            Integer.parseInt(cepOrigem.replace("-",""));
        }catch (Exception erro){
            throw new InputInvalidException("Valor informado para cep de origem é inválido");
        }
        try {
            Integer.parseInt(cepDestino.replace("-",""));
        }catch (Exception erro){
            throw new InputInvalidException("Valor informado para cep de destino é inválido");
        }
    }

    private double calcularValorFrete(LocalDate dataPrevistaEntrega, String peso) {
        double valorDesconto;
        double pesoCarga = Double.parseDouble(peso);
        LocalDate hoje = LocalDate.now();
        switch ((int) DAYS.between(hoje, dataPrevistaEntrega)) {
            case 1:
                valorDesconto = pesoCarga * 0.5;
                break;
            case 3:
                valorDesconto = pesoCarga * 0.75;
                break;
            default:
                valorDesconto = 0;
        }
        return pesoCarga - valorDesconto;
    }

    private LocalDate calcularEntrega(Cep cepRemetente, Cep cepDestinatario) {
        LocalDate diaEntrega;
        if (cepRemetente.getDdd().equals(cepDestinatario.getDdd())) {
            diaEntrega = LocalDate.now().plusDays(1);
        } else if (cepRemetente.getUf().equals(cepDestinatario.getUf())) {
            diaEntrega = LocalDate.now().plusDays(3);
        } else {
            diaEntrega = LocalDate.now().plusDays(10);
        }
        return diaEntrega;
    }

    private boolean verificarCep(Cep cep) {
        boolean retorno = false;
        if (cep.getCep() != null) {
            retorno = true;
        }
        return retorno;
    }
}