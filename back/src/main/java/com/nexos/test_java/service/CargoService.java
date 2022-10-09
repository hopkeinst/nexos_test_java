package com.nexos.test_java.service;

import com.nexos.test_java.mapper.CargoMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nexos.test_java.dto.CargoDTO;
import com.nexos.test_java.model.CargoModel;
import com.nexos.test_java.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoService {

    private static final Logger LOGGER = LogManager.getLogger(CargoService.class);
    @Autowired
    private CargoRepository iCargoRepository;

    public List<CargoDTO> listarCargos() {
        List<CargoModel> cargosQuery = null;
        List<CargoDTO> lstCargoDTO = null;
        cargosQuery = this.iCargoRepository.findAllEnabled();
        lstCargoDTO = cargosQuery.stream().map(CargoMapper.INSTANCE::toCargoDTO)
                .collect(Collectors.toList());
        return lstCargoDTO;
    }

    public CargoDTO obtenerCargo(Long idCargo) {
        CargoModel cargoModel = this.iCargoRepository.findIdEnabled(idCargo);
        CargoDTO cargoDTO = CargoMapper.INSTANCE.toCargoDTO(cargoModel);
        return cargoDTO;
    }

    public CargoDTO crearCargo(CargoDTO inputCargoDTO) {
        if(inputCargoDTO.getIdUsuarioCrea() == null) {
            inputCargoDTO.setId((long) -1);
            inputCargoDTO.setNombre("No se ingresó Usuario que crea el cargo");
            return inputCargoDTO;
        }
        if (existeNombre(inputCargoDTO.getNombre(), (long) -1)) {
            inputCargoDTO.setId((long) -1);
            inputCargoDTO.setNombre("No se puede crear cargo porque existe otro con igual nombre");
            return inputCargoDTO;
        }
        CargoModel cargoModel = CargoMapper.INSTANCE.toCargoModel(inputCargoDTO);
        Date dateNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cargoModel.setFechaCreacion(dateNow);
        CargoModel newCargoModel = iCargoRepository.save(cargoModel);
        inputCargoDTO = CargoMapper.INSTANCE.toCargoDTO(newCargoModel);
        return inputCargoDTO;
    }

    public CargoDTO actualizaCargo(CargoDTO inputCargoDTO) {
        if (inputCargoDTO.getIdUsuarioEdita() == null) {
            inputCargoDTO.setId((long) -1);
            inputCargoDTO.setNombre("No se ingresó Usuario que edita el cargo");
            return inputCargoDTO;
        }
        if (existeNombre(inputCargoDTO.getNombre(), inputCargoDTO.getId())) {
            inputCargoDTO.setId((long) -1);
            inputCargoDTO.setNombre("No se puede actualizar cargo porque existe otro con igual nombre");
            return inputCargoDTO;
        }
        System.out.println("Input DTO " + inputCargoDTO);
        CargoModel cargoModel = CargoMapper.INSTANCE.toCargoModel(inputCargoDTO);
        CargoModel oldCargoModel = iCargoRepository.findIdEnabled(inputCargoDTO.getId());
        Date dateNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cargoModel.setFechaCreacion(oldCargoModel.getFechaCreacion());
        cargoModel.setIdUsuarioCrea(oldCargoModel.getIdUsuarioCrea());
        cargoModel.setFechaEdicion(dateNow);
        CargoModel newCargoModel = iCargoRepository.save(cargoModel);
        inputCargoDTO = CargoMapper.INSTANCE.toCargoDTO(newCargoModel);
        return inputCargoDTO;
    }

    public CargoDTO eliminarCargo(Long idCargo, Long idUsuario) {
        CargoModel cargoModel = iCargoRepository.findIdEnabled(idCargo);
        CargoDTO cargoDTO = new CargoDTO();
        if(cargoModel == null) {
            cargoDTO.setId((long) -1);
            cargoDTO.setNombre("Ingresaste un ID / cargo no correcto. Revise e intente de nuevo.");
            return cargoDTO;
        }
        if(cargoModel.getIdUsuarioCrea() != idUsuario) {
            cargoDTO.setId((long) -1);
            cargoDTO.setNombre("El usuario que intenta eliminar el cargo es diferente al que lo creó. " +
                    "Revise e intente de nuevo");
            return cargoDTO;
        }
        Date dateNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        cargoModel.setFechaEliminacion(dateNow);
        cargoModel.setIdUsuarioElimina(idUsuario);
        CargoModel newCargoModel = iCargoRepository.save(cargoModel);
        cargoDTO = CargoMapper.INSTANCE.toCargoDTO(newCargoModel);
        return cargoDTO;
    }

    // Métodos privados
    private Boolean existeNombre(String nombre, Long id) {
        List<String> nombres = iCargoRepository.findNameEnabled(nombre, id);
        if((nombres.isEmpty()) || (nombres == null)) {
            return false;
        }
        return true;
    }

    /*
    @Autowired
    public void setiCargoRepository(CargoRepository cr) {
        this.iCargoRepository = cr;
    }
     */

}
