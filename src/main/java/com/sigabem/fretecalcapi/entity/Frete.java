package com.sigabem.fretecalcapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double peso;
    @Column(nullable = false)
    private String cepOrigem;
    @Column(nullable = false)
    private String cepDestino;
    @Column(nullable = false)
    private String nomeDestinatario;
    @Column(nullable = false)
    private double vlTotalFrete;
    @Column(nullable = false)
    private String dataPrevistaEntrega;
    @Column(nullable = false)
    private String dataConsulta;

}
