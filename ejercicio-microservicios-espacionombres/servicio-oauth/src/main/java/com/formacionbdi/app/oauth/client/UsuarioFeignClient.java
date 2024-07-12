package com.formacionbdi.app.oauth.client;

import com.formacionbdi.app.oauth.domain.usuario.models.entity.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servicio-usuarios", path = "usuario")
public interface UsuarioFeignClient {
    @GetMapping("/username/{username}")
    public Usuario findByUsername(@PathVariable String username);

    @PutMapping("/{id}")
    public Usuario update(@RequestBody Usuario user, @PathVariable Long id);
}
