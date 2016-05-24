/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.usuarios;

import com.mycompany.caminantes.entities.Usuarios;
import com.mycompany.caminantes.json.UsuarioReader;
import com.mycompany.caminantes.json.UsuarioWriter;
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
 * @author Mario
 */

@Named
@RequestScoped
public class usuariosClientBean {
    @Inject
    usuariosBackingBean bean;
    
    Client client;
    WebTarget target;
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void init() {
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/caminantes/webresources/com.mycompany.caminantes.entities.usuarios");
    }

    @PreDestroy
    public void destroy(){
        client.close();
    }

     public Usuarios[] getUsuarios() {
            return target
                    .request()
                    .get(Usuarios[].class);
    }
     
     public Usuarios getUsuario()
     {
        /* Usuarios u = target
                .path("{idUsuario}")
                .resolveTemplate("idUsuario", bean.getIdUsuario())
                .request(MediaType.APPLICATION_JSON)
                .get(Usuarios.class);
        return u;*/
         return target
            .register(UsuarioReader.class)
            .path("{idUsuario}")
            .resolveTemplate("idUsuario", bean.getIdUsuario())
            .request(MediaType.APPLICATION_JSON)
            .get(Usuarios.class);
     }

     public void deleteUsuario() {
        target.path("{idUsuario}")
                .resolveTemplate("idUsuario", bean.getIdUsuario())
                .request()
                .delete();
    }
     
     public void addUsuario() {
        Usuarios u = new Usuarios();
        u.setIdUsuario(1);
        u.setNombreUsuario(bean.getNombreUsuario());
        u.setPassword(bean.getPassword());
        u.setNombre(bean.getNombre());
        u.setApellidos(bean.getApellidos());
        u.setEdad(bean.getEdad());
        u.setProvincia(bean.getProvincia());
        u.setRol(bean.getRol());
        target.register(UsuarioWriter.class)
                .request()
                .post(Entity.entity(u, MediaType.APPLICATION_JSON));
        
        
    }
}
