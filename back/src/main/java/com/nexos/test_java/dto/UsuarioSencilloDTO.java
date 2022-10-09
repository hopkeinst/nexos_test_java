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
public class UsuarioSencilloDTO implements Serializable {

    private static final long serialVersionUID = -881939464320894081L;

    private Long id;

    private String nombreCompleto;

    private String nombreCargo;

}
