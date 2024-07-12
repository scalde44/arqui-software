package com.example.model.peer;

import com.example.model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class PeerUsuario implements Peer {
    private Usuario usuario;
    private Map<Long, Usuario> peers = new HashMap<>();

    public PeerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void agregarPeer(Usuario peer) {
        peers.put(peer.getId(), peer);
    }

    @Override
    public void enviarMensaje(String mensaje, Usuario destino) {
        // Simular envío de mensaje
        System.out.println(usuario.getNombre() + " envía: " + mensaje + " a " + destino.getNombre());
        // Aquí podrías implementar lógica de red para enviar el mensaje
    }

    @Override
    public void recibirMensaje(String mensaje, Usuario origen) {
        // Simular recepción de mensaje
        System.out.println(usuario.getNombre() + " recibe: " + mensaje + " de " + origen.getNombre());
    }


}