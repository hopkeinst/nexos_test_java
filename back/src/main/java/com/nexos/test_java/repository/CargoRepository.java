package com.nexos.test_java.repository;

import com.nexos.test_java.model.CargoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<CargoModel, Long> {

    @Query(value = "SELECT cm FROM CargoModel cm " +
                   "WHERE cm.fechaEliminacion IS null " +
                   "ORDER BY cm.id")
    List<CargoModel> findAllEnabled();

    @Query(value = "SELECT cm FROM CargoModel cm " +
            "WHERE cm.fechaEliminacion IS null AND cm.id = :inId")
    CargoModel findIdEnabled(@Param("inId") Long inId);

    @Query(value = "SELECT cm.nombre FROM CargoModel cm " +
            "WHERE cm.fechaEliminacion IS null AND " +
            "UPPER(TRIM(TRANSLATE(:inName, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) = " +
            "UPPER(TRIM(TRANSLATE(cm.nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')))" +
            "ORDER BY cm.id")
    List<String> findNameEnabled(@Param("inName") String inName);
}
