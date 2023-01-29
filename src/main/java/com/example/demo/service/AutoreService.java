package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.AutoreDTO;
import com.example.demo.entity.Autore;

public interface AutoreService {
	
	public boolean registraAutore(Autore a);
	public boolean modificaDatiAnagraficiAutore(int id,Autore a);
	public boolean eliminaAutore(int id);
	public AutoreDTO selezionaAutorePerLibro(int isbn);
	public AutoreDTO leggiDatiAutore(int id);
	public List<Autore> LeggiAutori();
}
