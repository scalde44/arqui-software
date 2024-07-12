package com.formacionbdi.app.oauth.service;

import com.formacionbdi.app.oauth.domain.usuario.models.entity.Usuario;

public interface IUsuarioService {
    Usuario findByUsername(String username);

    Usuario update(Usuario usuario, Long id);
}
