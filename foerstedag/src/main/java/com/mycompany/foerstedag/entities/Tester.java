/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.foerstedag.entities;

import DTO.PersonStyleDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.mycompany.foerstedag.entities.Person;
import com.mycompany.foerstedag.entities.Address;
import java.util.List;
import javax.persistence.Query;
/**
 *
 * @author Benjamin
 */
public class Tester {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Person p1 = new Person("Jens",1940);
        Person p2 = new Person("Erik", 1930);
        Address a1 = new Address("Vej 1", 2730,"Herlev");
        Address a2 = new Address("Vej 55", 8880,"Aalborg");
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(250);
        
        p1.addFees(f1);
        p2.addFees(f2);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("Buttefly");
        
        p1.addSwimStyle(s1);
        p1.addSwimStyle(s2);
        p2.addSwimStyle(s2);
        p2.addSwimStyle(s1);
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        
        em.getTransaction().commit();
    
        em.getTransaction().begin();
        p1.RemoveSwimStyle(s2);
        em.getTransaction().commit();
    
        
        System.out.println("p1 id: " + p1.getP_id());
        System.out.println("p2 navn:" + p2.getName());
        System.out.println("tovejs" + a1.getPerson().getName());
    
        Query q3 = em.createQuery("SELECT new DTO.PersonStyleDTO( p.name, p.year, s.styleName) from Person p JOIN p.styles s");
        List<PersonStyleDTO> personDetails = q3.getResultList();
        for(PersonStyleDTO ps: personDetails){
            System.out.println("navn: " + ps.getName() + " year: " + ps.getYear() + " style: " + ps.getSwimStyle());
        }
        
    }
  
   
}
