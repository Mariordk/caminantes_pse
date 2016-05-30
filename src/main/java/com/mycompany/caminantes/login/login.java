/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.login;

import com.mycompany.caminantes.entities.Roles;
import com.mycompany.caminantes.entities.Usuarios;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Mario
 */
@Named
@SessionScoped
public class login implements Serializable{
    
    
    private String nombreUsuario;
    

    @PersistenceContext
    EntityManager em;
    
    public String getNombreUsuario() {
        if (nombreUsuario == null) {
            login();
        }
        return nombreUsuario == null ? "" : nombreUsuario;
        
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
   
    public void login() 
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Object objPeticion = context.getRequest();
        if (!(objPeticion instanceof HttpServletRequest)) {
            System.out.println("Error objeto es: "
                    + objPeticion.getClass().toString());
            return;
        }
        HttpServletRequest peticion = (HttpServletRequest) objPeticion;
        nombreUsuario = peticion.getRemoteUser();
        if (nombreUsuario == null) {
            logout();
        }
    }
    
    public void logout()
    {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath());
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRol() {
        try {
            System.out.println(em.createNamedQuery("Roles.findByNombreUsuario", Roles.class)
                .setParameter("nombreUsuario", nombreUsuario)
                .getSingleResult()
                .getRol());
                return em.createNamedQuery("Roles.findByNombreUsuario", Roles.class)
                .setParameter("nombreUsuario", nombreUsuario)
                .getSingleResult()
                .getRol();
                
                
            } catch (NoResultException e) 
            {
                return "";
            }
    }  
}
