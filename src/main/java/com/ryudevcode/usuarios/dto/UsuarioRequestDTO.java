package com.ryudevcode.usuarios.dto;

import jakarta.validation.constraints.*;

public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;


    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede superar los 100 caracteres")
    private String apellido;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "el correo no tiene un formato valido")
    @Size(max = 150, message = "El correo no puede superar los 100 caracteres")
    private String correo;

    @NotNull(message =  "la edad es obligatoria")
    @Min(value = 18, message = "la edad minima es de 18")
    @Max(value = 100, message = "la edad maxima es 100")
    private Integer edad;

    public UsuarioRequestDTO (){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
