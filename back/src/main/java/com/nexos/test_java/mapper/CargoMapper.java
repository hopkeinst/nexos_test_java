package com.nexos.test_java.mapper;

import com.nexos.test_java.dto.CargoDTO;
import com.nexos.test_java.model.CargoModel;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    // ###############################
    // ## Va para ENTIDAD desde DTO ##
    // ###############################
    @Mapping(target = "usuarioCreaCargo", ignore = true)
    @Mapping(target = "usuarioEditaCargo", ignore = true)
    @Mapping(target = "usuarioEliminaCargo", ignore = true)
    CargoModel toCargoModel(CargoDTO cargoDTO);

    // ###############################
    // ## Va para DTO desde ENTIDAD ##
    // ###############################
    @Mapping(target = "nombreUsuarioCrea", source = "usuarioCreaCargo.nombreCompleto")
    @Mapping(target = "cargoUsuarioCrea", source = "usuarioCreaCargo.cargo.nombre")
    @Mapping(target = "nombreUsuarioEdita", source = "usuarioEditaCargo.nombreCompleto")
    @Mapping(target = "cargoUsuarioEdita", source = "usuarioEditaCargo.cargo.nombre")
    CargoDTO toCargoDTO(CargoModel cargoModel);

}
