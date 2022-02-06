package com.sigabem.fretecalcapi.mapper;

import com.sigabem.fretecalcapi.dto.request.FreteDTO;
import com.sigabem.fretecalcapi.entity.Frete;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FreteMapper {
    FreteMapper INSTANCE = Mappers.getMapper(FreteMapper.class);

    Frete toModel(FreteDTO freteDTO);
    FreteDTO toDTO(Frete frete);
}
