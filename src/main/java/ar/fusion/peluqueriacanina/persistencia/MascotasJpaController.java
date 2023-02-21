/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.fusion.peluqueriacanina.persistencia;

import ar.fusion.peluqueriacanina.logica.Mascotas;
import ar.fusion.peluqueriacanina.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author matia
 */
public class MascotasJpaController implements Serializable {

    public MascotasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public MascotasJpaController() {
        emf = Persistence.createEntityManagerFactory("PeluCaninaPU");
    }
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mascotas mascotas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mascotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mascotas mascotas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mascotas = em.merge(mascotas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = mascotas.getNumCliente();
                if (findMascotas(id) == null) {
                    throw new NonexistentEntityException("The mascotas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mascotas mascotas;
            try {
                mascotas = em.getReference(Mascotas.class, id);
                mascotas.getNumCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mascotas with id " + id + " no longer exists.", enfe);
            }
            em.remove(mascotas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mascotas> findMascotasEntities() {
        return findMascotasEntities(true, -1, -1);
    }

    public List<Mascotas> findMascotasEntities(int maxResults, int firstResult) {
        return findMascotasEntities(false, maxResults, firstResult);
    }

    private List<Mascotas> findMascotasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mascotas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mascotas findMascotas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mascotas.class, id);
        } finally {
            em.close();
        }
    }

    public int getMascotasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mascotas> rt = cq.from(Mascotas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
