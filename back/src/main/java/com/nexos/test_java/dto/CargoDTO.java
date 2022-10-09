package com.nexos.test_java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDTO implements Serializable {

    private static final long serialVersionUID = 7504417559997755903L;

    private Long id;

    @NotNull(message = "El nombre del cargo no puede estar vacío")
    @Length(max = 20, message = "La longitud máxima del nombre del cargo es 20 caracteres")
    private String nombre;

    private Long idUsuarioCrea;

    private Long idUsuarioEdita;

    private Long idUsuarioElimina;

    // Atributos que se traen de las foráneas
    private String nombreUsuarioCrea;
    private String cargoUsuarioCrea;

    private String nombreUsuarioEdita;
    private String cargoUsuarioEdita;

}
