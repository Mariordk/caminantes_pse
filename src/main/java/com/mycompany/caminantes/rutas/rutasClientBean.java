/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.rutas;

import com.mycompany.caminantes.entities.Rutas;
import com.mycompany.caminantes.json.RutasReader;
import com.mycompany.caminantes.json.RutasWriter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ángel
 */
@Named
@RequestScoped
public class rutasClientBean {
    @Inject
    rutasBackingBean bean;
    
    Client client;
    WebTarget target;
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/caminantes/webresources/com.mycompany.caminantes.entities.rutas");

    }
    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public Rutas[] getRutas() {
            return target
                    .request()
                    .get(Rutas[].class);
    }
    
    public Rutas getRuta()
     {
        
         return target
            .register(RutasReader.class)
            .path("{idRuta}")
            .resolveTemplate("idRuta", bean.getIdRuta())
            .request(MediaType.APPLICATION_JSON)
            .get(Rutas.class);
     }
    
    public void addRuta() {
        Rutas r = new Rutas();
        r.setIdRuta(1);
        r.setNombre(bean.getNombre());
        r.setDescripcion(bean.getDescripcion());
        r.setHoraInicio(bean.getHoraInicio());
        r.setHoraFin(bean.getHoraFin());
        target.register(RutasWriter.class)
                .request()
                .post(Entity.entity(r, MediaType.APPLICATION_JSON));
 
    }
    
    public void deleteRuta() {
        target.path("{idRuta}")
        .resolveTemplate("idRuta", bean.getIdRuta())
        .request()
        .delete();
}
}
