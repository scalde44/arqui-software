package com.example.filtro;

import com.example.model.Usuario;

public class FiltroLongitudEmail implements FiltroUsuario {
    @Override
    public void aplicar(Usuario usuario) {
        int longitudEmail = usuario.getEmail().length();
        System.out.println("Longitud del email: " + longitudEmail);
    }


}