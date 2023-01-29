package com.example.demo.dao;

import com.example.demo.entity.Categoria;

public interface CategoriaDao {
	
	public boolean insert(Categoria categoria);
	public boolean delete(String categoria);

}
