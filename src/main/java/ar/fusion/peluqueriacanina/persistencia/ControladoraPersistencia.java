/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.fusion.peluqueriacanina.persistencia;

import ar.fusion.peluqueriacanina.logica.Duenio;
import ar.fusion.peluqueriacanina.logica.Mascotas;
import ar.fusion.peluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matia
 */
public class ControladoraPersistencia {
    
    DuenioJpaController duenioJpa = new DuenioJpaController();
    MascotasJpaController mascoJpa = new MascotasJpaController();

    public void guardar(Duenio duenio, Mascotas masco) {
        
        //crear en la BD el Dueño
        duenioJpa.create(duenio);
        
        //crear en la BD la mascota
        mascoJpa.create(masco);
    }

    public List<Mascotas> traerMascotas() {
        
        return mascoJpa.findMascotasEntities();
    }

    public void borrarMascota(int num_cliente) {
        try {
            mascoJpa.destroy(num_cliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Mascotas traerMascota(int num_cliente) {
        return mascoJpa.findMascotas(num_cliente);
        
    }

    public void modificarMascota(Mascotas masco) {
        try {
            mascoJpa.edit(masco);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Duenio traerDuenio(int id_duenio) {
        return duenioJpa.findDuenio(id_duenio);
    }

    public void modificarDuenio(Duenio dueno) {
        try {
            duenioJpa.edit(dueno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
