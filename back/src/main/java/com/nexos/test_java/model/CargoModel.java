package com.nexos.test_java.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_cargos", schema = "nexos_inv_automotriz")
public class CargoModel implements Serializable {



    private static final long serialVersionUID = -3009547603991192791L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @Column(name = "id_user_crea", nullable = false)
    private Long idUsuarioCrea;

    @Column(name = "fecha_edicion")
    private Date fechaEdicion;

    @Column(name = "id_user_edita")
    private Long idUsuarioEdita;

    @Column(name = "fecha_eliminacion")
    private Date fechaEliminacion;

    @Column(name = "id_user_elimina")
    private Long idUsuarioElimina;

    // Los datos de las llaves foráneas
    @ManyToOne()
    @JoinColumn(name = "id_user_crea", insertable = false, updatable = false)
    private UsuarioModel usuarioCreaCargo;

    @ManyToOne()
    @JoinColumn(name = "id_user_edita", insertable = false, updatable = false)
    private UsuarioModel usuarioEditaCargo;

    @ManyToOne()
    @JoinColumn(name = "id_user_elimina", insertable = false, updatable = false)
    private UsuarioModel usuarioEliminaCargo;

    @Override
    public String toString() {
        return "Entidad Cargo: {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", idUserCrea=" + idUsuarioCrea +
                ", fechaEdicion=" + fechaEdicion +
                ", idUserEdita=" + idUsuarioEdita +
                ", fecha_eliminacion=" + fechaEliminacion +
                ", id_user_elimina=" + idUsuarioElimina +
                //", user_crea=" + usuarioCreaCargo +
                '}';
    }
}
