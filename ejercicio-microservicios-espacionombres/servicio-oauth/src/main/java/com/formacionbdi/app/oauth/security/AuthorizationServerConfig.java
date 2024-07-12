package com.formacionbdi.app.oauth.security;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final Environment environment;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final InfoAdicionalToken infoAdicionalToken;

    public AuthorizationServerConfig(Environment environment, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, InfoAdicionalToken infoAdicionalToken) {
        this.environment = environment;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.infoAdicionalToken = infoAdicionalToken;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(environment.getProperty("config.security.oauth.client.id"))
                .secret(passwordEncoder.encode(environment.getProperty("config.security.oauth.client.secret")))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600);
//                .and()
//                .withClient("androidapp")
//                .secret(passwordEncoder.encode("67891"))
//                .scopes("read", "write")
//                .authorizedGrantTypes("password", "refresh_token")
//                .accessTokenValiditySeconds(3600)
//                .refreshTokenValiditySeconds(3600);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(this.infoAdicionalToken, accessTokenConverter()));
        endpoints.authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .tokenStore(tokenStore(accessTokenConverter()))
                .tokenEnhancer(tokenEnhancerChain);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(environment.getProperty("config.security.oauth.jwt.key"));
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore(JwtAccessTokenConverter accessTokenConverter) {
        return new JwtTokenStore(accessTokenConverter);
    }
}
