package com.nexos.test_java.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nexos.test_java.dto.CargoDTO;
import com.nexos.test_java.service.CargoService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    private static final Logger LOGGER = LogManager.getLogger(CargoController.class);

    private CargoService objCargoService;

    @GetMapping("")
    public ResponseEntity<List<CargoDTO>> listarCargos() {
        List<CargoDTO> lstCargoDTO = this.objCargoService.listarCargos();
        if ((lstCargoDTO == null) || (lstCargoDTO.isEmpty())){
            return new ResponseEntity<>(lstCargoDTO, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lstCargoDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> mostrarCargo(@PathVariable(value = "id", required = false) Long cargoID) {
        CargoDTO cargoDTO = this.objCargoService.obtenerCargo(cargoID);
        System.out.println("CargoDTO " + cargoDTO);
        if(cargoDTO == null) {
            cargoDTO = new CargoDTO();
            cargoDTO.setId((long) -1);
            cargoDTO.setNombre("Ingresaste un ID no válido");
        }
        return new ResponseEntity<>(cargoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CargoDTO> crearCargo(@Valid @RequestBody CargoDTO inputCargoDTO){
        CargoDTO cargoDTO = objCargoService.crearCargo(inputCargoDTO);
        if(cargoDTO.getId() == -1) {
            return new ResponseEntity<>(cargoDTO, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cargoDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CargoDTO> actualizaCargo(@Valid @RequestBody CargoDTO inputCargoDTO) {
        CargoDTO cargoDTO = objCargoService.actualizaCargo(inputCargoDTO);
        if(cargoDTO.getId() == -1) {
            return new ResponseEntity<>(cargoDTO, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cargoDTO, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<CargoDTO> eliminarCargo(@RequestParam(name = "cargo") Long idCargo,
                                                  @RequestParam(name = "usuario") Long idUsuario) {
        CargoDTO cargoDTO = objCargoService.eliminarCargo(idCargo, idUsuario);
        if(cargoDTO.getId() == -1) {
            return new ResponseEntity<>(cargoDTO, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cargoDTO, HttpStatus.OK);
    }

    // Excepciones
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> diferenteTipoDato(){
        LOGGER.error("Se esperaba un tipo de dato diferente al obtenido");
        return new ResponseEntity<>("Se envía un tipo de dato en la URL (parámetro) diferente al " +
                "esperado, revise por favor.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> foraneaErronea() {
        LOGGER.error("Se ingresó un dato para una llave foránea que no es válido");
        return new ResponseEntity<>("Ingresaste un dato como Usuario o Cargo erróneo, que no " +
                "está relacionado en la base de datos. Revise e intente de nuevo.", HttpStatus.BAD_REQUEST);
    }

    // Inyecciones
    @Autowired
    public void setCargosService(CargoService cs){
        this.objCargoService = cs;
    }

}
