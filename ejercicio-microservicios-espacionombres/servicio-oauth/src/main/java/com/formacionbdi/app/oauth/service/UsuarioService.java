package com.formacionbdi.app.oauth.service;

import com.formacionbdi.app.oauth.client.UsuarioFeignClient;
import com.formacionbdi.app.oauth.domain.usuario.models.entity.Usuario;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private static final String USUARIO_NO_ENCONTRADO = "El usuario con el username %s no fue encontrado";
    private static final String LOG_USUARIO_NO_ENCONTRADO = "El usuario con el username {} no fue encontrado";
    private final UsuarioFeignClient usuarioFeignClient;

    public UsuarioService(UsuarioFeignClient usuarioFeignClient) {
        this.usuarioFeignClient = usuarioFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario;
        try {
            usuario = findByUsername(username);
        } catch (FeignException e) {
            log.error(LOG_USUARIO_NO_ENCONTRADO, username);
            throw new UsernameNotFoundException(String.format(USUARIO_NO_ENCONTRADO, username));
        }
        log.info("Usuario autenticado: ", username);
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .peek(authority -> log.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(),
                true, true, true, authorities);
    }

    @Override
    public Usuario findByUsername(String username) {
        return this.usuarioFeignClient.findByUsername(username);
    }

    @Override
    public Usuario update(Usuario usuario, Long id) {
        return this.usuarioFeignClient.update(usuario, id);
    }
}
