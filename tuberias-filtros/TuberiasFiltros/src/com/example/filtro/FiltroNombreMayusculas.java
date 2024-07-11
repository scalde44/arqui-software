package com.example.filtro;

import com.example.model.Usuario;

public class FiltroNombreMayusculas implements FiltroUsuario {
    @Override
    public void aplicar(Usuario usuario) {
        String nombreMayusculas = usuario.getNombre().toUpperCase();
        usuario.setNombre(nombreMayusculas);
        System.out.println("Nombre en mayúsculas: " + nombreMayusculas);
    }



}
