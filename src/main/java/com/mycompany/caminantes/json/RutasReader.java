/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.json;

import com.mycompany.caminantes.entities.Rutas;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.json.Json;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Ángel
 */
@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class RutasReader implements MessageBodyReader<Rutas>{

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return Rutas.class.isAssignableFrom(type);
    }

    @Override
    public Rutas readFrom(Class<Rutas> type,
            Type genericType, 
            Annotation[] annotations, 
            MediaType mediaType, 
            MultivaluedMap<String, String> httpHeaders, 
            InputStream in) 
            throws IOException, WebApplicationException {
        
        Rutas ruta= new Rutas();
        JsonParser parser = Json.createParser(in);
        
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    String key= parser.getString();
                    parser.next();
                    
                    switch (key){
                        case "idRuta":
                            ruta.setIdRuta(parser.getInt());
                            break;
                            
                        case "nombre":
                            ruta.setNombre(parser.getString());
                            break;
                            
                        case "descripcion":
                            ruta.setDescripcion(parser.getString());
                            break;
                            
                        case "horaInicio":
                            ruta.setHoraInicio(parser.getString());
                            break;
                            
                        case "horaFin":
                            ruta.setHoraFin(parser.getString());
                            break;
                            
                        
                    } //cierre segundo switch
                    break;
                default:
                    break;
            }//cierre primer switch
        } //cierre while
        return ruta;   
    } //cierra readFrom
} //cierra clase