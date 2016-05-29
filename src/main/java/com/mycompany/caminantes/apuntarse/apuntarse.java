/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.caminantes.apuntarse;

import com.mycompany.caminantes.entities.Rutas;
import java.io.Serializable;
import java.util.Date;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mario
 */
@Named
@FlowScoped("apuntarse")
public class apuntarse implements Serializable{
    
    private int id_ruta;
    private String nombre;
    private String descripcion;
    private String hora_inicio;
    private String hora_fin;
    private double cantidad;
    private String tarjeta = "";
    private Date fecha = new Date();
    
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    @PersistenceContext
    EntityManager em;
    
    public int getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(int id_ruta) {
        this.id_ruta = id_ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
    
    public String getNombreDeRuta() {
        try {
                return em.createNamedQuery("Rutas.findByIdRuta", Rutas.class)
                .setParameter("idRuta", id_ruta)
                .getSingleResult()
                .getNombre();
            } catch (NoResultException e) 
            {
                return "";
            }
    }  
    
    public String getDescripcionDeRuta() {
        try {
                return em.createNamedQuery("Rutas.findByIdRuta", Rutas.class)
                .setParameter("idRuta", id_ruta)
                .getSingleResult()
                .getDescripcion();
            } catch (NoResultException e){
                return "";
            }
    
    
    }

    public String getHoradeInicio(){
        
         try {
                return em.createNamedQuery("Rutas.findByIdRuta", Rutas.class)
                .setParameter("idRuta", id_ruta)
                .getSingleResult()
                .getHoraInicio();
            } catch (NoResultException e){
                return "";
            }
    }
    public String getHoradeFin(){
        
         try {
                return em.createNamedQuery("Rutas.findByIdRuta", Rutas.class)
                .setParameter("idRuta", id_ruta)
                .getSingleResult()
                .getHoraFin();
            } catch (NoResultException e){
                return "";
            }
    }
}