package com.example.agente;

import com.example.model.Usuario;

public class AgenteValidacion implements Agente {
    @Override
    public void procesar(Usuario usuario) {
        // Lógica de validación del usuario
        if (usuario.getEmail().contains("@")) {
            System.out.println("El usuario " + usuario.getNombre() + " tiene un email válido.");
        } else {
            System.out.println("El email del usuario " + usuario.getNombre() + " no es válido.");
        }
    }


}