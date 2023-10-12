package com.metalsa.config;

import com.metalsa.core.api.service.TokenApiController;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
//<TOKEN>
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.ArrayList;
//</TOKEN>
import java.util.Date;
import java.util.List;
import javax.servlet.http.Cookie;
import net.minidev.json.JSONObject;

/**
 * Filtro para chequear el header de autorizacion
 */
@Component
@Log4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpxAuthorizationTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String TOKEN_PORTAL = "AuthorizationPortal";
    private static final String ALGORITHM = "RSA";

    @Value("${PUBLIC_KEY}")
    private String keyString;
    @Value("${issuer}")
    private String issuer;

    @Value("${spx.enable-debug:false}")
    private Boolean debug;

    //<TOKEN>
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getServletPath().indexOf("monitor.html") == 1 || request.getServletPath().contains("motor/getImage")
                || allowUrlSinToken(request.getServletPath())) {
            filterChain.doFilter(request, response);
        } else {
            if (allowUrlPortal(request.getServletPath())) {
                TokenApiController token = new TokenApiController();
                String tokenPortal = request.getHeader(TOKEN_PORTAL);
                JSONObject respuesta = token.validarTokenPortal(tokenPortal);
                if (respuesta.get("status").equals(HttpStatus.ACCEPTED.value())) {
                    response.addHeader("vendorId", respuesta.get("vendorId").toString());
                    Cookie cookie = new Cookie("vendorId", respuesta.get("vendorId").toString());
                    cookie.setHttpOnly(true);
                    cookie.setSecure(false);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    filterChain.doFilter(request, response);
                } else {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                }
            } else {
                String xAuth = request.getHeader(TOKEN_HEADER);
                if (xAuth == null || xAuth.trim().length() < 7) {
                    log.debug("token invalido desde el inicio->" + xAuth);
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                } else {
                    xAuth = xAuth.substring(7);
                    if ("{init}".equalsIgnoreCase(xAuth)) {
                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    } else if (checkToken(xAuth)) {
                        filterChain.doFilter(request, response);
                    } else {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                    }
                }
            }
        }
    }

    private boolean checkToken(String token) {

        try {
            JWT jwt = JWTParser.parse(token);
            SignedJWT sjwt = (SignedJWT) jwt;
            // Base64URL signature = sjwt.getSignature();
            Base64 decode = new Base64(keyString);
            byte[] publicBytes = decode.decode();
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) pubKey);
            boolean validSignature = sjwt.verify(verifier);
            Date now = new Date();
            boolean validDate = jwt.getJWTClaimsSet().getExpirationTime().after(now);
            boolean validIssuer = issuer.equals(jwt.getJWTClaimsSet().getIssuer());

            return validSignature && validDate && validIssuer;
        } catch (JOSEException | NoSuchAlgorithmException | InvalidKeySpecException | ParseException e) {
            log.error("checkToken: ", e);
            log.debug("token invalido ->" + token, e);
            return false;
        }
    }
    //</TOKEN>

    /**
     * En esta metodo se agregan al list las URL que deseamos que no tengan
     * seguridad, puede ser un modulo completo o una URL en particular
     *
     * @param url
     * @return true si la URL contiene algun elemento de la lista
     * @date 10/Jun/2020
     */
    public boolean allowUrlPortal(String url) {
        List<String> allowUrl = new ArrayList();
        allowUrl.add("/api/v1/proveedor/");
        allowUrl.add("/api/v1/token/validarTokenPortal");
        return allowUrl.stream().anyMatch(t -> url.contains(t));
    }

    public boolean allowUrlSinToken(String url) {
        List<String> allowUrl = new ArrayList();
        allowUrl.add("/getTokenPortal/");
        allowUrl.add("/utilerias/getReclasificacion");
        allowUrl.add("/utilerias/status");
        allowUrl.add("/sendEmails/");
        allowUrl.add("/guardarCalificacion");
        return allowUrl.stream().anyMatch(t -> url.contains(t));
    }

}
