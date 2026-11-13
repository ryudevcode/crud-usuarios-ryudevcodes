package com.ryudevcode.usuarios.service.impl;


import com.ryudevcode.usuarios.dao.UsuarioDAO;
import com.ryudevcode.usuarios.dto.UsuarioRequestDTO;
import com.ryudevcode.usuarios.dto.UsuarioResponseDTO;
import com.ryudevcode.usuarios.entity.Usuario;
import com.ryudevcode.usuarios.exception.DuplicateResourceException;
import com.ryudevcode.usuarios.exception.ResourceNotFoundException;
import com.ryudevcode.usuarios.mapper.UsuarioMapper;
import com.ryudevcode.usuarios.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceImpl(UsuarioDAO usuarioDAO, UsuarioMapper usuarioMapper){
        this.usuarioDAO  = usuarioDAO;
        this.usuarioMapper = usuarioMapper;

    }


    @Override
    public UsuarioResponseDTO guardar(UsuarioRequestDTO request){
        if(usuarioDAO.existsByCorreo(request.getCorreo())){
            throw  new DuplicateResourceException("El correo ya se encuentra registrado");
        }
        //no esta duplicado
        Usuario usuario = usuarioMapper.toEntity(request);

        Usuario usuarioGuardado = usuarioDAO.save(usuario);

        return usuarioMapper.toResponseDTO(usuarioGuardado);

    }

    @Override

    public List<UsuarioResponseDTO> listar(){
        return usuarioDAO.findAll().stream().map(usuarioMapper::toResponseDTO).toList();
    }

    @Override

    public UsuarioResponseDTO buscarPorID(Long id){

        Usuario usuario = usuarioDAO.findById(id).orElseThrow(() ->
                 new ResourceNotFoundException("No se encontro el usuario con el id"+id)
                );

        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO request){
        Usuario usuario = usuarioDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("No se encontro el usuario con el id"+id)
                );

        if(usuarioDAO.existsByCorreoAndIdNot(request.getCorreo(), id)){
            throw new DuplicateResourceException("El correo ya pertenece a otro usuario");
        }

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setCorreo(request.getCorreo());
        usuario.setEdad(request.getEdad());

        //
        Usuario usuarioActulizado = usuarioDAO.save(usuario);
        return usuarioMapper.toResponseDTO(usuarioActulizado);
    }

    @Override
    public void eliminar(Long id){
        Usuario usuario = usuarioDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("No se encontro el usuario id "+id)
                );

        usuarioDAO.delete(usuario);
    }


}
