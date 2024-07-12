package com.formacionbdi.app.oauth.security.event;

import com.formacionbdi.app.oauth.domain.usuario.models.entity.Usuario;
import com.formacionbdi.app.oauth.service.IUsuarioService;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);
    private final IUsuarioService usuarioService;

    public AuthenticationSuccessErrorHandler(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        if (authentication.getDetails() instanceof WebAuthenticationDetails) {
            return;
        }
        UserDetails user = (UserDetails) authentication.getPrincipal();
        log.info("Login exitoso de: {}", user.getUsername());
        Usuario usuario = this.usuarioService.findByUsername(authentication.getName());
        if (usuario.getTries() != null && usuario.getTries() > 0) {
            usuario.setTries(0);
            this.usuarioService.update(usuario, usuario.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        log.error("Error logueandose: {}", exception.getMessage());
        try {
            Usuario usuario = this.usuarioService.findByUsername(authentication.getName());
            if (usuario.getTries() == null) {
                usuario.setTries(0);
            }
            log.info("Intentos actual es de: {}", usuario.getTries());
            usuario.setTries(usuario.getTries() + 1);
            log.info("Intentos despues es de: {}", usuario.getTries());
            if (usuario.getTries() >= 3) {
                log.error("El usuario {} deshabilitado por superar 3 intentos", authentication.getName());
                usuario.setEnabled(false);
            }
            this.usuarioService.update(usuario, usuario.getId());
        } catch (FeignException e) {
            log.error("El usuario {} no existe en el sistema", authentication.getName());
        }
    }
}
