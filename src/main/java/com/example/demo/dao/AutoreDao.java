package com.example.demo.dao;

import com.example.demo.entity.Autore;

public interface AutoreDao {
	
	public void modificaDatiAnagraficiAutore(Autore a);
	public Autore findAutore(int id);
}
