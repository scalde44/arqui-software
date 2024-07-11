package com.example.coordinador;

import com.example.agente.Agente;
import com.example.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AgenteCentral {
    private List<Agente> agentes = new ArrayList<>();

    public void registrarAgente(Agente agente) {
        agentes.add(agente);
    }

    public void procesarUsuario(Usuario usuario) {
        for (Agente agente : agentes) {
            agente.procesar(usuario);
        }
    }
}