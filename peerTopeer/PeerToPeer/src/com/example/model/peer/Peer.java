package com.example.model.peer;


import com.example.model.Usuario;

public interface Peer {
    void enviarMensaje(String mensaje, Usuario destino);
    void recibirMensaje(String mensaje, Usuario origen);
}