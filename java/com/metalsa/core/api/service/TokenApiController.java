/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metalsa.core.api.service;

import com.metalsa.core.pojo.Token;
import com.nimbusds.jose.util.Base64;
import io.swagger.annotations.Api;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

/**
 *
 * @author edgar.leal
 */
@RestController
@Api(value = "token", tags = {"Token Service API"})
@RequestMapping("/api/v1/token")
@Slf4j
public class TokenApiController {

    private final String keyString = "m3t4ls4%$P99r#";

    @RequestMapping(value = "/getData/{stoken}", method = RequestMethod.GET)
    public @ResponseBody
    Token getData(@PathVariable("stoken") String stoken) {
        return getToken(stoken);
    }

    //<PERFIL>
    public static Token getToken(String stoken) {
        String nombre = "", userId = "", idioma = "", idRol="", idIdioma, vistaSeleccion, vistaAprobacion, localidad="";
        Token token = new Token();
        
        try {
            Base64 decode = new Base64(stoken.split("\\.")[1]);
            String strJson = decode.decodeToString();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(strJson);
            JSONObject jsonObject = (JSONObject) obj;
            nombre = jsonObject.get("name").toString();
            userId = jsonObject.get("userId").toString();
            idioma = jsonObject.get("idioma").toString();
            idRol = jsonObject.get("idRol").toString();
			idIdioma = jsonObject.get("idIdioma").toString();
            vistaSeleccion = jsonObject.get("vistaSeleccion").toString();
            vistaAprobacion = jsonObject.get("vistaAprobacion").toString();
            localidad = jsonObject.get("localidad").toString();
            token.setIdUsuario(userId);
            token.setNombreUsuario(nombre);
            token.setIdioma(idioma);
            token.setIdRol(Integer.parseInt(idRol));
            token.setIdIdioma(idIdioma);
            token.setVistaSeleccion(vistaSeleccion);
            token.setVistaAprobacion(vistaAprobacion);
            token.setLocalidad(localidad);
        } catch (ParseException e) {
            System.out.println("aqui debemos loguear algo");
            //LOG.error("getSubjectToken: ", e);
        }
        return token;
    }

    //</PERFIL>
    @RequestMapping(value = "getTokenPortal/{idVendor}", method = RequestMethod.GET)
    @ResponseBody
    public String getTokenPortal(@PathVariable("idVendor") String idVendor) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(keyString);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId("1")
                .setIssuedAt(now)
                .setSubject("Metalsa")
                .claim("vendorId", idVendor)
                .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 720))
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public JSONObject validarTokenPortal(String token) {
        JSONObject respuesta = new JSONObject();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(keyString))
                    .parseClaimsJws(token).getBody();
            respuesta.put("status", HttpStatus.ACCEPTED.value());
            respuesta.put("vendorId", claims.get("vendorId", String.class));
            return respuesta;
        } catch (Exception e) {
            log.error("Error token");
            respuesta.put("status", HttpStatus.FORBIDDEN.value());
            return respuesta;
        }
    }

    @RequestMapping(value = "validarTokenPortal", method = RequestMethod.GET)
    public Integer validarTokenPortal() {
        return HttpStatus.ACCEPTED.value();
    }

}
