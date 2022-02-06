package com.sigabem.fretecalcapi.mapper;

import com.sigabem.fretecalcapi.dto.request.FreteDTO;
import com.sigabem.fretecalcapi.entity.Frete;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FreteMapperImpl implements FreteMapper{
    @Override
    public Frete toModel(FreteDTO freteDTO) {
        if (freteDTO == null){
            return null;
        }
        String hoje = String.valueOf(LocalDate.now());
        Frete frete = Frete
                .builder()
                .id(freteDTO.getId())
                .cepDestino(freteDTO.getCepDestino())
                .cepOrigem(freteDTO.getCepOrigem())
                .peso(Double.parseDouble(freteDTO.getPeso()))
                .nomeDestinatario(freteDTO.getNomeDestinatario())
                .dataConsulta((LocalDate.now()).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .dataPrevistaEntrega(freteDTO.getDataPrevistaEntrega().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .vlTotalFrete(freteDTO.getVlTotalFrete())
                .build();
        return frete;
    }

    @Override
    public FreteDTO toDTO(Frete frete) {
        if (frete == null){
            return null;
        }
        FreteDTO freteDTO = FreteDTO
                .builder()
                .id(frete.getId())
                .cepDestino(frete.getCepDestino())
                .cepOrigem(frete.getCepOrigem())
                .peso(String.valueOf(frete.getPeso()))
                .nomeDestinatario(frete.getNomeDestinatario())
                .dataPrevistaEntrega(LocalDate.parse(frete.getDataPrevistaEntrega()))
                .build();
        return freteDTO;
    }
}
