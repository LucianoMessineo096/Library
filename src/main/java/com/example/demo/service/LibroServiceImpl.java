package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CategoriaDao;
import com.example.demo.dao.LibroDao;
import com.example.demo.dao.LibroInstantRepository;
import com.example.demo.dao.MagazzinoInstantRepository;
import com.example.demo.dto.LibroDTO;
import com.example.demo.entity.Autore;
import com.example.demo.entity.Categoria;
import com.example.demo.entity.Libro;
import com.example.demo.entity.Magazzino;

@Service
@Transactional
public class LibroServiceImpl implements LibroService{
	
	@Autowired
	private CategoriaDao dao;
	@Autowired 
	private LibroInstantRepository dao_l;
	@Autowired
	private LibroDao daoLibro;
	@Autowired 
	private MagazzinoInstantRepository dao_m;
	@Autowired
	private LogService dao_log;
	
	@Override
	public boolean registraNuovoLibro(Libro l) {
		// TODO Auto-generated method stub
		List<Libro> libri = dao_l.findAll();
		List<Magazzino> magazzino = dao_m.findAll();
		
		if(!libri.contains(l)) {
			//salvo il libro
			dao_l.save(l);
			
			//inserisco la richiesta in magazzino
			
			Magazzino m=new Magazzino();
			m.setStatoStock("richiesto");
			m.setIsbn(l);
			m.setQuantita(50);
			int idMagazzino = magazzino.get(magazzino.size()-1).getIdMagazzino()+1;
			m.setIdMagazzino(idMagazzino);
			
			dao_m.save(m);
			
			//inserisco il log nel db
			
			dao_log.registraLogElement("POST "+"http://localhost:8080/example/libreria/libri");
			return true;
		}else {
			return false;
		}
		
		
	}

	@Override
	public boolean modificaDatiLibro(Libro l) {
		// TODO Auto-generated method stub
		List<Libro> libri = dao_l.findAll();
		boolean flag=false;
		for(Libro libro : libri) {
			if(libro.getIsbn()==l.getIsbn()) {
				daoLibro.modificaLibro(l);
				dao_log.registraLogElement("PUT "+"http://localhost:8080/example/libreria/libri");
				flag=true;
			}
		}
			
		return flag;
		
	}

	@Override
	public boolean eliminaLibro(int isbn) {
		// TODO Auto-generated method stub
		Libro l  =dao_l.findById(isbn).get();
		
		dao_l.delete(l);
		dao_log.registraLogElement("DELETE "+"http://localhost:8080/example/libreria/libri/"+isbn);
		return true;
		
	}
	
	@Override
	public List<Libro> selezionaLibriPerAutore(int idAutore) {
		// TODO Auto-generated method stub
		List<Libro> libri = dao_l.findAll();
		List<Libro> libriAutore = new ArrayList<>();
		
		for(Libro libro : libri) {
			if(libro.getIdAutore().getIdAutore()==idAutore) {
				libriAutore.add(libro);
				
			}
		}
		
		dao_log.registraLogElement("http://localhost:8080/example/libreria/libri");
		return libriAutore;
	}
	


	@Override
	public LibroDTO leggiDatiLibro(int isbn) {
		// TODO Auto-generated method stub
		List<Libro> libri = dao_l.findAll();
		LibroDTO libroSelected = new LibroDTO();
		for(Libro l: libri) {
			if(l.getIsbn()==isbn) {
				
				libroSelected.setIsbn(l.getIsbn());
				libroSelected.setTitolo(l.getTitolo());
				libroSelected.setDescrizione(l.getDescrizione());
				libroSelected.setCategoria(l.getCategoria());
				libroSelected.setPrezzo(l.getPrezzo());
				libroSelected.setnCopie(l.getnCopie());
				libroSelected.setIdAutore(l.getIdAutore());
			}
		}
		
		dao_log.registraLogElement("GET "+"http://localhost:8080/example/libreria/libri/"+isbn);
		return libroSelected;
	}

	@Override
	public List<Libro> selezionaTuttiLibri() {
		// TODO Auto-generated method stub
		List<Libro> listaLibri = dao_l.findAll();
		dao_log.registraLogElement("GET "+"http://localhost:8080/example/libreria/libri");
		return listaLibri;
	}

	@Override
	public void statistichePerNazione() {
		// TODO Auto-generated method stub
		/*per ogni nazione, recupera il numero di libri in vendita. Per nazione si 
			intende la nazione di appartenenza dell’autore
			*/
		
		
	} 

	@Override
	public void approvvigionaLibro(int isbn , int nCopie) {
		// TODO Auto-generated method stub
		
		List<Magazzino> magazzino = dao_m.findAll();
		Magazzino newOp = new Magazzino();
		int newId=(magazzino.get(magazzino.size()-1).getIdMagazzino())+1;
		
		for(Magazzino m : magazzino) {
			if((m.getIsbn().getIsbn()==isbn) && (m.getStatoStock().equalsIgnoreCase("richiesto"))) {
				int disponibile=m.getQuantita();
				
				//effettuare controllo sul numero di copie
				m.setQuantita(disponibile+(nCopie-disponibile));
				
				daoLibro.approvvigiona(m);
				
			}else if((m.getIsbn().getIsbn()==isbn) && (m.getStatoStock().equalsIgnoreCase("disponibile"))){
				newOp.setIdMagazzino(newId);
				newOp.setIsbn(m.getIsbn());
				newOp.setQuantita(nCopie);
				newOp.setStatoStock("richiesto");
				
				daoLibro.approvvigiona(newOp);
			}else {
				//caso processato : non faccio nulla
			}
		}
		
		dao_log.registraLogElement("PUT "+"http://localhost:8080/example/libreria/magazzino/"+isbn+"/"+nCopie);
	}

	@Override
	public void cambiaStatoRichiesta(int isbn , String stato) {
		// TODO Auto-generated method stub
		List<Magazzino> magazzino = dao_m.findAll();
		Magazzino newStock = new Magazzino();
		int nCopie=0;
		
		//recupero il libro con quell'isbn
		Libro libroSelezionato = dao_l.findById(isbn).get();
		
		for(Magazzino stock1 : magazzino) {
			if(stock1.getIsbn().getIsbn()==isbn) {
				
				if(stock1.getStatoStock().equalsIgnoreCase("richiesto")) {
					
					if(stato.equalsIgnoreCase("processato")) {
						stock1.setStatoStock(stato);
					}
					
				}else if(stock1.getStatoStock().equalsIgnoreCase("processato")) {
					
					if(stato.equalsIgnoreCase("disponibile")) {
						
						//ricavo la lista di tutte le operazioni di stock con l'isbn specificato e con stato disponibile
						List<Magazzino> statoStockLibro = new ArrayList<>();
						
						for(Magazzino stock2 : magazzino) {
							
							if(stock2.getIsbn().getIsbn()==isbn && stock2.getStatoStock().equalsIgnoreCase(stato)) {
								statoStockLibro.add(stock2);
								
							}
						}
						
						//una volta ricavati tutti gli stati stock per quell' isbn sommo in un unica variabile il numero di copie
						for(Magazzino stock3: statoStockLibro) {
							nCopie+=stock3.getQuantita();
							
							//a questo punto elimino dal db tutti gli stati disponibili per quel libro e le accorpo in unica tupla successivamente
							
							dao_m.delete(stock3);
						}
						
						//devo eliminare la tupla corrispondente allo stato stock processato , in quanto una volta creata la nuova essa conterra le nuove info
						//prima salvo la quantita del processato
						int quantitaProcessato = stock1.getQuantita();
						//adesso elimino la tupla processato
						dao_m.delete(stock1);
						
						// aggiorno la tupla nel db 
						magazzino = dao_m.findAll();
						int newIdMagazzino = magazzino.get(magazzino.size()-1).getIdMagazzino()+1;
						
						newStock.setIsbn(libroSelezionato);
						//alla somma di tutte le quantita dello stock disponibile aggiungo anche quella dello stato processato del libro specificato
						newStock.setQuantita(nCopie+quantitaProcessato);
						newStock.setStatoStock(stato);
						newStock.setIdMagazzino(newIdMagazzino);
						
						dao_m.save(newStock);
						
						//aggiorno il numero di copie nella tabella libri per l'isbn specificato
						
						libroSelezionato.setnCopie(nCopie+quantitaProcessato);
						dao_l.save(libroSelezionato);
					}
				}
			}
		}
		
		dao_log.registraLogElement("PUT "+"http://localhost:8080/example/libreria/magazzino/"+isbn+"/"+stato);
		
	}

	@Override
	public List<Magazzino> leggiInfoStock(int isbn) {
		// TODO Auto-generated method stub
		List<Magazzino> magazzino = dao_m.findAll();
		List<Magazzino> infoStock = new ArrayList<>();
		for(Magazzino m : magazzino) {
			if(m.getIsbn().getIsbn()==isbn) {
				infoStock.add(m);
			}
		}
		dao_log.registraLogElement("GET "+"http://localhost:8080/example/libreria/magazzino/"+isbn);
		return infoStock;
	}

	@Override
	public void chiudiRichiesta(int isbn) {
		// TODO Auto-generated method stub
		List<Magazzino> magazzino = dao_m.findAll();
		for(Magazzino m : magazzino) {
			if((m.getIsbn().getIsbn()==isbn) && (m.getStatoStock().equals("disponibile"))){
				
				dao_m.delete(m);
				
			}
		}
		
		dao_log.registraLogElement("DELETE "+"http://localhost:8080/example/libreria/magazzino"+isbn);
		
	}

	
	@Override
	public boolean aggiungiCategoria(Categoria c) {
		// TODO Auto-generated method stub
		dao.insert(c);
		dao_log.registraLogElement("POST "+"http://localhost:8080/example/libreria/categorie");
		return false;
	}

	@Override
	public boolean eliminaCategoria(String id) {
		// TODO Auto-generated method stub
		dao.delete(id);
		dao_log.registraLogElement("DELETE"+"http://localhost:8080/example/libreria/categorie/"+id);
		return false;
	}

	

}
