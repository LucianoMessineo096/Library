package com.example.demo.dao;

import com.example.demo.dto.LibroDTO;
import com.example.demo.entity.Libro;
import com.example.demo.entity.Magazzino;

public interface LibroDao {
	
	public Libro leggiDatiLibro(int isbn);
	public void approvvigiona(Magazzino op);
	public void cambiaStatoRichiesta();
	public void inserisciLibro(Libro l);
	public void modificaLibro(Libro l);
}
