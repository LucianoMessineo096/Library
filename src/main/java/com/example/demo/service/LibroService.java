package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LibroDTO;
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Libro;
import com.example.demo.entity.Magazzino;

public interface LibroService {
	
	/*classe libro*/
	
	public boolean registraNuovoLibro(Libro l);
	public boolean modificaDatiLibro(Libro l);
	public boolean eliminaLibro(int isbn);
	public LibroDTO leggiDatiLibro(int isbn);
	public List<Libro> selezionaLibriPerAutore(int idAutore); 
	public List<Libro> selezionaTuttiLibri(); 
	public void statistichePerNazione(); 
	public void approvvigionaLibro(int isbn,int nCopie); 
	public void cambiaStatoRichiesta(int isbn , String stato); 
	public List<Magazzino> leggiInfoStock(int isbn); 
	public void chiudiRichiesta(int isbn);
	
	/*classe categoria*/
	public boolean aggiungiCategoria(Categoria c);
	public boolean eliminaCategoria(String c);
	
}
