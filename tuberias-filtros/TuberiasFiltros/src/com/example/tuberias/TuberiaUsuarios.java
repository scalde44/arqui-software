package com.example.tuberias;

import com.example.filtro.FiltroUsuario;
import com.example.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class TuberiaUsuarios {
    private List<FiltroUsuario> filtros = new ArrayList<>();

    public void agregarFiltro(FiltroUsuario filtro) {
        filtros.add(filtro);
    }

    public void ejecutarTuberia(Usuario usuario) {
        for (FiltroUsuario filtro : filtros) {
            filtro.aplicar(usuario);
        }
    }
}