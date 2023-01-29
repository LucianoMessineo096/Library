package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Autore;

@Service
@Transactional
public class AutoreDaoImpl implements AutoreDao{
	
	@PersistenceContext
	private EntityManager manager;
	@Override
	public void modificaDatiAnagraficiAutore(Autore a) {
		// TODO Auto-generated method stub
		
			manager.merge(a);
		
	}
	@Override
	public Autore findAutore(int id) {
		// TODO Auto-generated method stub
		Autore a = manager.find(Autore.class, id);
		
		return a;
	}

}
