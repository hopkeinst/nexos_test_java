package com.nexos.test_java.mapper;

import com.nexos.test_java.dto.CargoDTO;
import com.nexos.test_java.dto.UsuarioDTO;
import com.nexos.test_java.dto.UsuarioSencilloDTO;
import com.nexos.test_java.model.CargoModel;
import com.nexos.test_java.model.UsuarioModel;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    CargoMapper INSTANCE = Mappers.getMapper(CargoMapper.class);

    // ###############################
    // ## Va para ENTIDAD desde DTO ##
    // ###############################
    //@Mapping(target = "usuarioCreaCargo", ignore = true)
    //@Mapping(target = "usuarioEditaCargo", ignore = true)
    //@Mapping(target = "usuarioEliminaCargo", ignore = true)


    // ###############################
    // ## Va para DTO desde ENTIDAD ##
    // ###############################

    @Mapping(target = "nombreCargo", source = "cargo.nombre")
    UsuarioSencilloDTO toUsuarioSencilloDTO(UsuarioModel usuarioModel);

    UsuarioDTO toUsuarioDTO(UsuarioModel usuarioModel);
}
