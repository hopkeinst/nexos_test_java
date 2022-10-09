package com.nexos.test_java.service;

import com.nexos.test_java.mapper.UsuarioMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nexos.test_java.dto.UsuarioDTO;
import com.nexos.test_java.dto.UsuarioSencilloDTO;
import com.nexos.test_java.model.UsuarioModel;
import com.nexos.test_java.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private static final Logger LOGGER = LogManager.getLogger(CargoService.class);
    private UsuarioRepository iUsuarioRepository;
/*
    public List<UsuarioDTO> listarUsuarios() {

    }

    public List<UsuarioSencilloDTO> listarUsuariosB() {

    }

    public Usu
*/
    @Autowired
    public void setiUsuarioRepository(UsuarioRepository ur) {
        this.iUsuarioRepository = ur;
    }
}
