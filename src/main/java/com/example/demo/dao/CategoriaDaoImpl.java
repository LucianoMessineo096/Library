package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Categoria;



@Primary
@Repository
@Transactional
public class CategoriaDaoImpl implements CategoriaDao{
	
	@PersistenceContext //injection come autowired
	private EntityManager manager;
	
	@Override
	public boolean insert(Categoria categoria) {
		// TODO Auto-generated method stub
		manager.persist(categoria);
		
		return true;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		boolean flag;
		Categoria c = manager.find(Categoria.class,id);
		if(c.getCategoria().equalsIgnoreCase(id)){
			manager.remove(c);
			flag=true;
		}else {
			flag=false;
		}
		
		return flag;
	}
}
