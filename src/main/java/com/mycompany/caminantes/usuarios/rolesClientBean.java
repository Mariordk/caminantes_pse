/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.usuarios;

import com.mycompany.caminantes.entities.Roles;
import com.mycompany.caminantes.json.RolesReader;
import com.mycompany.caminantes.json.RolesWriter;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
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
 * @author Mario
 */
@Named
@RequestScoped
public class rolesClientBean {
    
    Client client;
    WebTarget target;
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/caminantes/webresources/com.mycompany.caminantes.entities.roles");
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }
    
    public Roles[] getRoles() {
        return target
                .request()
                .get(Roles[].class);
    }

    public Roles getRol(String usuario) {
        return target
                .register(RolesReader.class)
                .path("{nombreUsuario}")
                .resolveTemplate("nombreUsuario", usuario)
                .request(MediaType.APPLICATION_JSON)
                .get(Roles.class);
    }

    public void borrarRol(String usuario) {
        target.path("{nombreUsuario}")
                .resolveTemplate("nombreUsuario", usuario)
                .request()
                .delete();
    }

    public void addRol(String usuario, String rol) {
        Roles m = new Roles();
        m.setNombreUsuario(usuario);
        m.setRol(rol);
        
        target.register(RolesWriter.class).request()
                .post(Entity.entity(m, MediaType.APPLICATION_JSON));
    }
}
