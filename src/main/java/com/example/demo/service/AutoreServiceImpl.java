package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.AutoreDao;
import com.example.demo.dao.AutoreInstantRepository;
import com.example.demo.dao.LibroInstantRepository;
import com.example.demo.dao.LogServiceInstantRepository;
import com.example.demo.dto.AutoreDTO;
import com.example.demo.entity.Autore;
import com.example.demo.entity.Libro;
import com.example.demo.entity.LogWebOperation;

@Service
@Transactional
public class AutoreServiceImpl implements AutoreService{
	
	@Autowired
	private AutoreInstantRepository dao_a;
	@Autowired
	private AutoreDao daoAutore;
	@Autowired
	private LibroInstantRepository dao_l;
	@Autowired
	private LogService dao_log;
	
	@Override
	public boolean registraAutore(Autore a) {
		// TODO Auto-generated method stub
		List<Autore> autori =  dao_a.findAll();
		
		//registro nei log il nuovo autore che si vuole iscrivere
		dao_log.registraLogElement("POST "+"http://localhost:8080/example/libreria/autori");
		
		if(autori.contains(a)==false) {
			dao_a.save(a);
			
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean modificaDatiAnagraficiAutore(int id,Autore a) {
		// TODO Auto-generated method stub
		
		List<Autore> autori = dao_a.findAll();
		boolean flag=false;
		
		for(Autore autore: autori) {
			if(autore.getIdAutore()==a.getIdAutore()) {
				daoAutore.modificaDatiAnagraficiAutore(a);
				flag=true;	
			}
		}
		
		dao_log.registraLogElement("PUT "+"http://localhost:8080/example/libreria/autori/"+id);
		return flag;
		
	}

	@Override
	public boolean eliminaAutore(int id) {
		// TODO Auto-generated method stub
		Autore a  =dao_a.findById(id).get();
		
		dao_a.delete(a);
		dao_log.registraLogElement("DELETE "+"http://localhost:8080/example/libreria/autori/"+id);
		return true;
		
	}

	@Override
	public AutoreDTO selezionaAutorePerLibro(int isbn) {
		// TODO Auto-generated method stub
		List<Libro> libri = dao_l.findAll();
		
		AutoreDTO dto = new AutoreDTO();
		Autore autore= new Autore();
		
		for(Libro l : libri) {
			if(l.getIsbn()==isbn) {
				autore= l.getIdAutore();
			}
		}
			
		dto.setIdAutore(autore.getIdAutore());
		dto.setNome(autore.getNome());
		dto.setCognome(autore.getCognome());
		dto.setNazioneResidenza(autore.getNazioneResidenza());
		
		dao_log.registraLogElement("GET "+"http://localhost:8080/example/libreria/autori/"+isbn);
		return dto;
	}

	@Override
	public AutoreDTO leggiDatiAutore(int id) {
		// TODO Auto-generated method stub
		List<Autore> autori = dao_a.findAll();
		AutoreDTO dto = new AutoreDTO();
		
		for(Autore a : autori) {
			if(a.getIdAutore()==id) {
				dto.setIdAutore(a.getIdAutore());
				dto.setNome(a.getNome());
				dto.setCognome(a.getCognome());
				dto.setNazioneResidenza(a.getNazioneResidenza());
			}
		}
		dao_log.registraLogElement("GET "+"http://localhost:8080/example/libreria/autori/"+id);
		return dto;
	}

	@Override
	public List<Autore> LeggiAutori() {
		// TODO Auto-generated method stub
		List<Autore> listaAutori = dao_a.findAll();
		
		dao_log.registraLogElement("GET "+"http://localhost:8080/example/libreria/autori");
		return listaAutori;
	}
	
}
