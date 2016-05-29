/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.json;

import com.mycompany.caminantes.entities.Roles;
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
public class RolesReader implements MessageBodyReader<Roles>{
    @Override
    public boolean isReadable(Class<?> type, 
            Type genericType, 
            Annotation[] annotations, 
            MediaType mediaType) {
        return Roles.class.isAssignableFrom(type);
    }



    @Override
    public Roles readFrom(Class<Roles> type, 
            Type genericType, 
            Annotation[] annotations, 
            MediaType mediaType, 
            MultivaluedMap<String, String> httpHeaders, 
            InputStream entityStream) 
            throws IOException, WebApplicationException {
        Roles rol = new Roles();
        JsonParser parser = Json.createParser(entityStream);
        
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                        String key = parser.getString();
                        parser.next();
                        
                    switch (key) {
                      
                        case "nombreUsuario":
                            rol.setNombreUsuario(parser.getString());
                            System.out.println();
                            break;
                        case "rol":
                            rol.setRol(parser.getString());
                            break;
                        
                        default:
                            break;
                    }
                    
                break;
                default:
                break;
                }
        }       
        
    return rol;
    }
}
