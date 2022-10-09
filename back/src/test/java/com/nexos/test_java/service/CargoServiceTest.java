package com.nexos.test_java.service;

import com.nexos.test_java.dto.CargoDTO;
import com.nexos.test_java.model.CargoModel;
import com.nexos.test_java.repository.CargoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@SpringBootTest
class CargoServiceTest {

    @Autowired
    private CargoService cargoService;

    @Test
    void listarCargos() {
        List<CargoDTO> lstCargoDTO = cargoService.listarCargos();
        assertNotNull(lstCargoDTO, "La tabla de datos está vacía o se realizó mal la consulta");
    }

    @Test
    void obtenerCargo() {
        CargoDTO cargo = cargoService.obtenerCargo((long) 1);
        assertNotNull(cargo, "El cargo 1 pudo ser eliminado o se realizó mal la consulta");
    }

    @Test
    void obtenerCargoAdministrador() {
        CargoDTO cargo = cargoService.obtenerCargo((long) 1);
        CargoDTO cargoTest = new CargoDTO();
        cargoTest.setId((long) 1);
        cargoTest.setNombre("Administrador");
        cargoTest.setIdUsuarioCrea((long) 1);
        cargoTest.setNombreUsuarioCrea("Jorge Saul Castillo Jaimes");
        cargoTest.setCargoUsuarioCrea("Administrador");
        assertEquals(cargo, cargoTest, "Lo obtenido y lo esperado no son iguales, revise los datos o la consulta");
    }

    @Test
    void crearCargoRepetido() {
        CargoDTO cargoTest = new CargoDTO();
        cargoTest.setNombre("SOPORTE");
        cargoTest.setIdUsuarioCrea((long) 1);
        CargoDTO response = cargoService.crearCargo(cargoTest);
        assertEquals(-1, response.getId(), "Permitió crear un cargo repetido, revisar la lógica");
    }

    @Test
    void crearCargoSinIdUserCrea() {
        CargoDTO cargoTest = new CargoDTO();
        cargoTest.setNombre("Nuevo Cargo");
        CargoDTO response = cargoService.crearCargo(cargoTest);
        assertEquals(-1, response.getId(), "Permitió crear un cargo sin el ID del usuario que lo crea, revisar la lógica");
    }

    @Test
    void crearCargo(){
        CargoDTO cargoTest = new CargoDTO();
        Date dateNow = new Date();
        cargoTest.setNombre("Nuevo Cargo " + dateNow.toString());
        cargoTest.setIdUsuarioEdita((long) 1);
        cargoTest.setNombreUsuarioCrea("Jorge Saul Castillo Jaimes");
        cargoTest.setCargoUsuarioCrea("Administrador");
        CargoDTO response = cargoService.crearCargo(cargoTest);
        assertEquals(cargoTest, response, "El DTO esperado es diferente al obtenido, revise los datos");
    }

    @Test
    void actualizaCargoNombreRepetido() {
        CargoDTO cargo = cargoService.obtenerCargo((long) 6);
        cargo.setNombre("SOPORTE");
        cargo.setIdUsuarioEdita((long) 1);
        CargoDTO response = cargoService.crearCargo(cargo);
        assertEquals(-1, response.getId(), "Se esperaba un error y no se obtuvo, se actualizó cuando no debería, nombre de cargo repetido, revise la lógica");
    }

    @Test
    void actualizaCargoSinUsuario() {
        CargoDTO cargo = cargoService.obtenerCargo((long) 6);
        cargo.setIdUsuarioEdita(null);
        CargoDTO response = cargoService.crearCargo(cargo);
        assertEquals(-1, response.getId(), "Se esperaba un error y no se obtuvo, se actualizó cuando no debería, se intenta actualizar sin ID de usuario que actualza, revise la lógica");
    }

    @Test
    void actualizaCargo() {
        CargoDTO cargo = cargoService.obtenerCargo((long) 6);
        cargo.setNombre("Cargo De PRUEBA");
        cargo.setIdUsuarioEdita((long) 1);
        CargoDTO response = cargoService.crearCargo(cargo);
        assertEquals(cargo, response, "No coincide el DTOs esperado y obtenido");
    }

    @Test
    void eliminarCargoIdInvalido() {
        CargoDTO response = cargoService.eliminarCargo((long) 10000, (long) 1);
        assertEquals(-1, response.getId(), "Se esperaba un error y no se obtuvo, el ID del cargo a eliminar no existe, revise la lógica");
    }

    @Test
    void eliminarCargoIdDiferente() {
        CargoDTO response = cargoService.eliminarCargo((long) 3, (long) 3);
        assertEquals(-1, response.getId(), "Se esperaba un error y no se obtuvo, el ID del usuario que elimina es diferente al que crea, revise la lógica");
    }
    
}