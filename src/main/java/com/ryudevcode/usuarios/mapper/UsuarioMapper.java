package com.ryudevcode.usuarios.mapper;

import com.ryudevcode.usuarios.dto.UsuarioRequestDTO;
import com.ryudevcode.usuarios.dto.UsuarioResponseDTO;
import com.ryudevcode.usuarios.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO request ){
        Usuario usuario = new Usuario();

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setEdad(request.getEdad());

        return  usuario;
    }

    public UsuarioResponseDTO toResponseDTO (Usuario usuario){
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
       response.setApellido(usuario.getApellido());
       response.setCorreo(usuario.getCorreo());
       response.setEdad(usuario.getEdad());
       response.setActivo(usuario.getActivo());
        response.setFechaCreacion(usuario.getFechaCreacion());
        return  response;
    }

}
