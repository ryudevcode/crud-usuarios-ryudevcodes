package com.ryudevcode.usuarios.service;

import com.ryudevcode.usuarios.dto.UsuarioRequestDTO;
import com.ryudevcode.usuarios.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDTO guardar(UsuarioRequestDTO resquest);

    List<UsuarioResponseDTO> listar();

    UsuarioResponseDTO buscarPorID(Long id);

    UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO resquest);

    void eliminar(Long id);


}
