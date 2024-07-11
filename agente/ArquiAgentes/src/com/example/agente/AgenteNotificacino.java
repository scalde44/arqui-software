package com.example.agente;

import com.example.model.Usuario;

public class AgenteNotificacino implements Agente {
    @Override
    public void procesar(Usuario usuario) {
        // Lógica de notificación del usuario
        System.out.println("Notificando al usuario " + usuario.getNombre() + " al email " + usuario.getEmail());
    }


}