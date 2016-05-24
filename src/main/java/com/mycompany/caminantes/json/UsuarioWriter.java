/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.json;

import com.mycompany.caminantes.entities.Usuarios;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Mario
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioWriter implements MessageBodyWriter<Usuarios>{

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Usuarios.class.isAssignableFrom(type);
    }

    @Override
    public long getSize(Usuarios t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(Usuarios u, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
       JsonGenerator gen = Json.createGenerator(entityStream);
        gen.writeStartObject()
            .write("idUsuario", u.getIdUsuario())
            .write("nombreUsuario", u.getNombreUsuario())
            .write("password", u.getPassword())
            .write("nombre",u.getNombre())
            .write("apellidos",u.getApellidos())
            .write("edad",u.getEdad())
            .write("provincia",u.getProvincia())
            .write("rol",u.getRol())
        .writeEnd();
        gen.flush();
    }
    
}
