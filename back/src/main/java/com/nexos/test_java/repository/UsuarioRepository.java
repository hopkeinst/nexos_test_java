package com.nexos.test_java.repository;

import com.nexos.test_java.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    @Query(value =  "SELECT um FROM UsuarioModel um " +
                    "WHERE um.fechaEliminacion IS null " +
                    "ORDER BY um.id")
    List<UsuarioModel> findAllEnabled();

    @Query(value = "SELECT um FROM UsuarioModel um " +
            "WHERE um.fechaEliminacion IS null AND um.id = :inId")
    UsuarioModel findIdEnabled(@Param("inId") Long inId);

    @Query(value = "SELECT um.nombres FROM UsuarioModel um " +
            "WHERE um.fechaEliminacion IS null AND " +
            "UPPER(TRIM(TRANSLATE(:inName, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) = " +
            "UPPER(TRIM(TRANSLATE(um.nombres, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) AND " +
            "UPPER(TRIM(TRANSLATE(:inLast, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) = " +
            "UPPER(TRIM(TRANSLATE(um.apellidos, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "ORDER BY um.id")
    List<String> findNameEnabled(@Param("inName") String inName, @Param("inLast") String inLast);

}
