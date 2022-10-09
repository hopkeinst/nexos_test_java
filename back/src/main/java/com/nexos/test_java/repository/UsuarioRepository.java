package com.nexos.test_java.repository;

import com.nexos.test_java.model.CargoModel;
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

}
