package com.formacionbdi.app.oauth.security;

import com.formacionbdi.app.oauth.domain.usuario.models.entity.Usuario;
import com.formacionbdi.app.oauth.service.IUsuarioService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAdicionalToken implements TokenEnhancer {
    private final IUsuarioService usuarioService;

    public InfoAdicionalToken(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = new HashMap<>();
        Usuario usuario = this.usuarioService.findByUsername(oAuth2Authentication.getName());
        info.put("nombre", usuario.getName());
        info.put("apellido", usuario.getLastName());
        info.put("correo", usuario.getEmail());
        DefaultOAuth2AccessToken accessToken = (DefaultOAuth2AccessToken) oAuth2AccessToken;
        accessToken.setAdditionalInformation(info);
        return accessToken;
    }
}
