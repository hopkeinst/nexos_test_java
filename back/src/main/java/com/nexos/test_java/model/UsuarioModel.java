package com.nexos.test_java.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", schema = "nexos_inv_automotriz")
public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = 6716623312947074068L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "id_cargo", nullable = false)
    private Long idCargo;

    @Column(name = "fecha_ingreso", nullable = false)
    private Date fechaIngreso;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "id_user_crea", nullable = false)
    private Long idUserCrea;

    @Column(name = "fecha_edicion")
    private Date fechaEdicion;

    @Column(name = "id_user_edita")
    private Long idUserEdita;

    @Column(name = "fecha_eliminacion")
    private Date fechaEliminacion;

    @Column(name = "id_user_elimina")
    private Long idUserElimina;

    // Los datos de las llaves foraneas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cargo", insertable = false, updatable = false)
    private CargoModel cargo;

    public String getNombreCompleto(){
        return this.nombres + " " + this.apellidos;
    }

}
