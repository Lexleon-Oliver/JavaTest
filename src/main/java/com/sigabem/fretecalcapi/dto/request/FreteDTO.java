package com.sigabem.fretecalcapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreteDTO {

    private Long id;
    @NotNull(message = "O peso é obrigatório e não pode ser nulo")
    private String peso;
    @NotNull(message = "O cep de origem é obrigatório e não pode ser nulo")
    @Size(min = 8,max = 9,message = "O cep deve conter 8 numeros. O traço '-' é opcional")
    private String cepOrigem;
    @Size(min = 8,max = 9,message = "O cep deve conter 8 numeros. O traço '-' é opcional")
    @NotNull(message = "O cep de destino é obrigatório e não pode ser nulo")
    private String cepDestino;
    @NotNull(message = "O nome do destinatário é obrigatório e não pode ser nulo")
    private String nomeDestinatario;
    private double vlTotalFrete;
    private LocalDate dataPrevistaEntrega;


}
