package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AutoreDTO;
import com.example.demo.dto.LibroDTO;
import com.example.demo.entity.Autore;
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Libro;
import com.example.demo.entity.LogWebOperation;
import com.example.demo.entity.Magazzino;
import com.example.demo.service.AutoreService;
import com.example.demo.service.LibroService;
import com.example.demo.service.LogService;

@RestController
@RequestMapping(path="/libreria")
@CrossOrigin("*")
public class LibroServiceController {
	
	@Autowired
	private LibroService service;
	@Autowired 
	private AutoreService service_a;
	@Autowired 
	private LogService serviceLog;
	
	// ******************************************* servizi categoria ************************************************* //
	
	@PostMapping(path="/categorie",consumes= {"application/json","application/xml"})
	public ResponseEntity<Boolean> inserisciCategoria(@RequestBody Categoria c) {
			
			try{
				System.out.println("oggetto in arrivo dal client");
				System.out.println(c);
				boolean b = service.aggiungiCategoria(c);
				ResponseEntity response = new ResponseEntity(b,HttpStatus.OK);
				return response;
			}catch(Exception e) {
				ResponseEntity response = new ResponseEntity("errore inserimento",HttpStatus.METHOD_NOT_ALLOWED);
				return response;
			}
	}
	
	
	@DeleteMapping(path="/categorie/{id}")
	public ResponseEntity<Boolean> eliminaCategoria(@PathVariable String id) {
		try{
			boolean b = service.eliminaCategoria(id);
			ResponseEntity response = new ResponseEntity("200 : categoria cancellata",HttpStatus.OK);
			return response;
		}catch(DataAccessException e) { // nel primo catch devo inserire l'eccezione specifica - trovare il nome dell'eccezione ????
			ResponseEntity response = new ResponseEntity("Errore 400 : id inserito non valido",HttpStatus.BAD_REQUEST);
			return response;
		}catch(Exception e) {
			ResponseEntity response = new ResponseEntity("Errore 404 : risorsa sul server non trovata ",HttpStatus.NOT_FOUND);
			return response;
		}	
			
	}
	
	// ************************************ servizi libri ******************************************* //
	@PostMapping(path="/libri",consumes= {"application/json","application/xml"})
	public ResponseEntity<Boolean> inserisciLibro(@RequestBody Libro l) {
			
			
				boolean b = service.registraNuovoLibro(l);
				ResponseEntity response = new ResponseEntity(b,HttpStatus.OK);
				return response;
			
	}
	
	@DeleteMapping(path="/libri/{isbn}")
	public ResponseEntity<Boolean> eliminaLibro(@PathVariable int isbn) {
		try{
			boolean b = service.eliminaLibro(isbn);
			ResponseEntity response = new ResponseEntity("200 : libro cancellato",HttpStatus.OK);
			return response;
		}catch(DataAccessException e) { // nel primo catch devo inserire l'eccezione specifica - trovare il nome dell'eccezione ????
			ResponseEntity response = new ResponseEntity("Errore 400 : id inserito non valido",HttpStatus.BAD_REQUEST);
			return response;
		}catch(Exception e) {
			ResponseEntity response = new ResponseEntity("Errore 404 : risorsa sul server non trovata ",HttpStatus.NOT_FOUND);
			return response;
		}	
			
	}
	
	
	@GetMapping(path="/libriAutori/{idAutore}",produces={"application/json","application/xml"})
	public List<Libro> libriPerAutore(@PathVariable int idAutore){
		List<Libro> libriAutore= service.selezionaLibriPerAutore(idAutore);
		
		return libriAutore;
	}
	
	

	@GetMapping(path="/libri/{isbn}",produces={"application/json","application/xml"})
	public ResponseEntity<LibroDTO>selezionaLibro(@PathVariable int isbn){
		
		try{
			LibroDTO dto = service.leggiDatiLibro(isbn);
			ResponseEntity response = new ResponseEntity(dto,HttpStatus.OK);
			return response;
			/*
		}catch(HttpClientErrorException e) { // nel primo catch devo inserire l'eccezione specifica - trovare il nome dell'eccezione ????
			ResponseEntity response = new ResponseEntity("Errore 400 : id inserito non valido",HttpStatus.BAD_REQUEST);
			return response;
			*/
		}catch(Exception e) {
			
			ResponseEntity response = new ResponseEntity("Errore 404 : risorsa sul server non trovata ",HttpStatus.NOT_FOUND);
			return response;
		}
		
	}
	
	
	@GetMapping(path="/libri")
	public List<Libro> selezionaTuttiLibri(){
		List<Libro> listaLibri = service.selezionaTuttiLibri();
		
		return listaLibri;
	}
	
	@PutMapping(path="/libri")
	public void modificaLibro(@RequestBody Libro l) {
		
		service.modificaDatiLibro(l);
	}
	
	
	@PostMapping(path="/magazzino/{isbn}/{ncopie}")
	public void approvvigionaLibro(@PathVariable int isbn , @PathVariable int ncopie) {
		service.approvvigionaLibro(isbn, ncopie);
	}
	
	
	@GetMapping(path="/magazzino/{isbn}")
	public List<Magazzino> leggiInfoStock(@PathVariable int isbn){
		
		List<Magazzino> stock = service.leggiInfoStock(isbn);
		
		return stock;
	}
	
	//da rivedere 
	@DeleteMapping(path="/magazzino/{isbn}")
	public void chiudiRichiesta(@PathVariable int isbn){
		
		service.chiudiRichiesta(isbn);
			
	}
	
	@PutMapping(path="/magazzino/{isbn}/{status}")
	public void cambiaStatoStock(@PathVariable int isbn , @PathVariable String status) {
		service.cambiaStatoRichiesta(isbn, status);
	}
	
	
	// ***************************************** servizi autore ********************************************************* //
	
	@PostMapping(path="/autori")
	public ResponseEntity<Boolean> inserisciAutore(@RequestBody Autore a) {
		try{
			System.out.println("oggetto in arrivo dal client");
			System.out.println(a);
			boolean b = service_a.registraAutore(a);
			ResponseEntity response = new ResponseEntity(b,HttpStatus.OK);
			return response;
		}catch(Exception e) {
			ResponseEntity response = new ResponseEntity("errore inserimento",HttpStatus.METHOD_NOT_ALLOWED);
			return response;
		}
	}
	
	@DeleteMapping(path="/autori/{id}")
	public ResponseEntity<Boolean> eliminaAutore(@PathVariable int id) {
		try{
			boolean b = service_a.eliminaAutore(id);
			ResponseEntity response = new ResponseEntity("200 : autore cancellato",HttpStatus.OK);
			return response;
		}catch(DataAccessException e) { // nel primo catch devo inserire l'eccezione specifica - trovare il nome dell'eccezione ????
			ResponseEntity response = new ResponseEntity("Errore 400 : id inserito non valido",HttpStatus.BAD_REQUEST);
			return response;
		}catch(Exception e) {
			ResponseEntity response = new ResponseEntity("Errore 404 : risorsa sul server non trovata ",HttpStatus.NOT_FOUND);
			return response;
		}	
			
	}
	
	@PutMapping(path="/autori/{id}")
	public ResponseEntity<Boolean> modificaDatiAutore(@PathVariable int id , @RequestBody Autore a) {
		
		try{
			boolean b = service_a.modificaDatiAnagraficiAutore(id,a);
			ResponseEntity response = new ResponseEntity("200 : autore modificato",HttpStatus.OK);
			return response;
		}catch(DataAccessException e) { // nel primo catch devo inserire l'eccezione specifica - trovare il nome dell'eccezione ????
			ResponseEntity response = new ResponseEntity("Errore 400 : id inserito non valido",HttpStatus.BAD_REQUEST);
			return response;
		}catch(Exception e) {
			ResponseEntity response = new ResponseEntity("Errore 404 : risorsa sul server non trovata ",HttpStatus.NOT_FOUND);
			return response;
		}		
	}
	
	
	@GetMapping(path="/autori")
	public List<Autore> selezionaTuttiAutori(){
		List<Autore> listaAutori = service_a.LeggiAutori();
		
		return listaAutori;
	}
	
	@GetMapping(path="/autori/{id}",produces={"application/json","application/xml"})
	public ResponseEntity<AutoreDTO>selezionaAutore(@PathVariable int id){
		
		try{
			AutoreDTO dto = service_a.leggiDatiAutore(id);
			ResponseEntity response = new ResponseEntity(dto,HttpStatus.OK);
			return response;
			/*
		}catch(HttpClientErrorException e) { // nel primo catch devo inserire l'eccezione specifica - trovare il nome dell'eccezione ????
			ResponseEntity response = new ResponseEntity("Errore 400 : id inserito non valido",HttpStatus.BAD_REQUEST);
			return response;
			*/
		}catch(Exception e) {
			
			ResponseEntity response = new ResponseEntity("Errore 404 : risorsa sul server non trovata ",HttpStatus.NOT_FOUND);
			return response;
		}
		
	}
	
	@GetMapping(path="/autoriLibri/{isbn}",produces={"application/json","application/xml"})
	public ResponseEntity<AutoreDTO>selezionaAutoreLibro(@PathVariable int isbn){
		
		try{
			AutoreDTO dto = service_a.selezionaAutorePerLibro(isbn);
			ResponseEntity response = new ResponseEntity(dto,HttpStatus.OK);
			return response;
			
		}catch(Exception e) {
			
			ResponseEntity response = new ResponseEntity("Errore 404 : risorsa sul server non trovata ",HttpStatus.NOT_FOUND);
			return response;
		}
		
	}
	
	// ********************************* servizi LogWebOperation******************************************** //
	
	/*
	@GetMapping(path="/operazioni/{ldt}")
	public ResponseEntity<List> statistiche(@PathVariable LocalDateTime ldt){
		
		List<LogWebOperation> statistiche = serviceLog.statisticheNelPeriodo(ldt);
		ResponseEntity response = new ResponseEntity(statistiche,HttpStatus.OK);
		
		return response;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
