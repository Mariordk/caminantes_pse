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
    
    private int idRuta;
    private String nombre;
    private String descripcion;
    private String horaInicio;
    private String horaFin;
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
    
    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
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

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
    
    public String getNombreDeRuta() {
        try {
                return em.createNamedQuery("Rutas.findByIdRuta", Rutas.class)
                .setParameter("idRuta", idRuta)
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
                .setParameter("idRuta", idRuta)
                .getSingleResult()
                .getDescripcion();
            } catch (NoResultException e){
                return "";
            }
    
    
    }

    public String getHoradeInicio(){
        
         try {
                return em.createNamedQuery("Rutas.findByIdRuta", Rutas.class)
                .setParameter("idRuta", idRuta)
                .getSingleResult()
                .getHoraInicio();
            } catch (NoResultException e){
                return "";
            }
    }
    public String getHoradeFin(){
        
         try {
                return em.createNamedQuery("Rutas.findByIdRuta", Rutas.class)
                .setParameter("idRuta", idRuta)
                .getSingleResult()
                .getHoraFin();
            } catch (NoResultException e){
                return "";
            }
    }
}