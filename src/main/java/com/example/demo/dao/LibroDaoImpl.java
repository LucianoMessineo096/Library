package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.LibroDTO;
import com.example.demo.entity.Libro;
import com.example.demo.entity.Magazzino;

@Primary
@Repository
@Transactional
public class LibroDaoImpl implements LibroDao{
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public Libro leggiDatiLibro(int isbn) {
		// TODO Auto-generated method stub
		Libro l = manager.find(Libro.class, isbn);
		
		return l;
	}

	@Override
	public void approvvigiona(Magazzino op) {
		// TODO Auto-generated method stub
		
		switch(op.getStatoStock()) {
			
			case "richiesto" :
			
				manager.merge(op);
				
				break;
			
			case "disponibile": 
				
				manager.persist(op);
		}
	}

	@Override
	public void cambiaStatoRichiesta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciLibro(Libro l) {
		// TODO Auto-generated method stub
		manager.persist(l);
	}

	@Override
	public void modificaLibro(Libro l) {
		// TODO Auto-generated method stub
		manager.merge(l);
	}

	
	
	
	

}
