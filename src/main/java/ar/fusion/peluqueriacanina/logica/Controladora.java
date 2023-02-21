/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.fusion.peluqueriacanina.logica;

import ar.fusion.peluqueriacanina.persistencia.ControladoraPersistencia;
import java.util.List;

/**
 *
 * @author matia
 */
public class Controladora {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public void guardar(String nombreMasco, String raza, String color, String observaciones, String alergico, String atenEsp, String nombreDuenio, String celDuenio) {
        
        //creamos el dueño y asignamos sus valores
        Duenio duenio=new Duenio();
        duenio.setNombre(nombreDuenio);
        duenio.setCelDuenio(celDuenio);
        
        
        //creamos la mascota y asignamos sus valores
        Mascotas masco = new Mascotas();
        masco.setNombre(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setAlergico(alergico);
        masco.setAtencion_especial(atenEsp);
        masco.setObservaciones(observaciones);
        masco.setUnDuenio(duenio);
        
        controlPersis.guardar(duenio,masco);
    }

    public List<Mascotas> traerMascotas() {
       return controlPersis.traerMascotas();
    }

    public void borrarMascota(int num_cliente) {
        controlPersis.borrarMascota(num_cliente);
    }

    public Mascotas traerMascota(int num_cliente) {
        return controlPersis.traerMascota(num_cliente);
        
    }

    public void modificarMascota(Mascotas masco, String nombreMasco, String raza, String color, String observaciones, String alergico, String atenEsp, String nombreDuenio, String celDuenio) {
        
        masco.setNombre(nombreMasco);
        masco.setRaza(raza);
        masco.setColor(color);
        masco.setObservaciones(observaciones);
        masco.setAlergico(alergico);
        masco.setAtencion_especial(atenEsp);
        
        //modifico la mascota
        controlPersis.modificarMascota(masco);
        
        //seteo nuevos valores del duenio
        Duenio dueno = this.buscarDuenio(masco.getUnDuenio().getId_duenio());
        dueno.setCelDuenio(celDuenio);
        dueno.setNombre(nombreDuenio);
        
        //llamar al modificar Dueño
        this.modificarDuenio(dueno);
    }

    private Duenio buscarDuenio(int id_duenio) {
        return controlPersis.traerDuenio(id_duenio);
    }

    private void modificarDuenio(Duenio dueno) {
        controlPersis.modificarDuenio(dueno);
    }

    
    
}
