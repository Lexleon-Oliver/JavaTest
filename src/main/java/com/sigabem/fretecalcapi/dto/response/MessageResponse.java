package com.sigabem.fretecalcapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageResponse {
    private String cepOrigem;
    private String cepDestino;
    private String dataPrevistaEntrega;
    private String vlTotalFrete;
}
