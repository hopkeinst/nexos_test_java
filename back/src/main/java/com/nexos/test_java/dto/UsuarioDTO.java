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
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 2761044553997362483L;

    private Long id;

    @NotNull(message = "Debe ingresar el nombre del usuario")
    @Length(max = 100, message = "La longitud máxima del nombre es de 100 caracteres")
    private String nombres;

    @NotNull(message = "Debe ingresar el apelldio del usuario")
    @Length(max = 100, message = "La longitud máxima del apellido es de 100 caracteres")
    private String apellidos;

}
