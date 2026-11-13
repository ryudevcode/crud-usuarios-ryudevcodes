package com.ryudevcode.usuarios.controller;

import com.ryudevcode.usuarios.dto.UsuarioRequestDTO;
import com.ryudevcode.usuarios.dto.UsuarioResponseDTO;
import com.ryudevcode.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    //REgistrar???????
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> guardar( @Valid @RequestBody UsuarioRequestDTO request)
    {

         UsuarioResponseDTO respuesta = usuarioService.guardar(request);
         return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar(){
        List<UsuarioResponseDTO> usuarios = usuarioService.listar();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id){
        UsuarioResponseDTO usuario = usuarioService.buscarPorID(id);
        return  ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actulizar(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO resquest){

        UsuarioResponseDTO respuesta = usuarioService.actualizar(id,resquest);
        return ResponseEntity.ok(respuesta);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
