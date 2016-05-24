/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.json;

import com.mycompany.caminantes.entities.Usuarios;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Mario
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioReader implements MessageBodyReader<Usuarios>{

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Usuarios.class.isAssignableFrom(type);
    }

    @Override
    public Usuarios readFrom(Class<Usuarios> type, 
            Type type1,
            Annotation[] antns, 
            MediaType mt, 
            MultivaluedMap<String, String> mm,
            InputStream in) throws IOException, WebApplicationException {
        
        Usuarios usuario = new Usuarios();
        JsonParser parser = Json.createParser(in);
        
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                        String key = parser.getString();
                        parser.next();
                        
                    switch (key) {
                        case "idUsuario":
                            usuario.setIdUsuario(parser.getInt());
                        break;
                        case "nombreUsuario":
                            usuario.setNombreUsuario(parser.getString());
                        break;
                        case "password":
                            usuario.setPassword(parser.getString());
                        break;
                        case "nombre":
                            usuario.setNombre(parser.getString());
                        break;
                        case "apellidos":
                            usuario.setNombre(parser.getString());
                        break;
                        case "edad":
                            usuario.setEdad(parser.getInt());
                        break;
                        case "provincia":
                            usuario.setProvincia(parser.getString());
                        break;
                        case "rol":
                            usuario.setRol(parser.getString());
                        break;
                        default:
                        break;
                    }
                break;
                default:
                break;
                }
        }       
        
    return usuario;
    } 
}
