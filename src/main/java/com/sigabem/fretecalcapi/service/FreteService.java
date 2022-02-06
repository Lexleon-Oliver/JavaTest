package com.sigabem.fretecalcapi.service;

import com.sigabem.fretecalcapi.dto.request.FreteDTO;
import com.sigabem.fretecalcapi.dto.response.MessageResponse;

public interface FreteService {
    MessageResponse calcularFrete(FreteDTO freteDTO);
}
