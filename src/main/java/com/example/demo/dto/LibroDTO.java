package com.example.demo.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.demo.entity.Autore;
import com.example.demo.entity.Categoria;

public class LibroDTO {
	
	
	private int isbn;
	private String titolo;
	private String descrizione;
	private Categoria categoria;
	private double prezzo;
	private int nCopie;
	private Autore idAutore;
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getnCopie() {
		return nCopie;
	}
	public void setnCopie(int nCopie) {
		this.nCopie = nCopie;
	}
	public Autore getIdAutore() {
		return idAutore;
	}
	public void setIdAutore(Autore idAutore) {
		this.idAutore = idAutore;
	}
	public LibroDTO(int isbn, String titolo, String descrizione, Categoria categoria, double prezzo, int nCopie,
			Autore idAutore) {
		super();
		this.isbn = isbn;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.prezzo = prezzo;
		this.nCopie = nCopie;
		this.idAutore = idAutore;
	}
	@Override
	public String toString() {
		return "LibroDTO [isbn=" + isbn + ", titolo=" + titolo + ", descrizione=" + descrizione + ", categoria="
				+ categoria + ", prezzo=" + prezzo + ", nCopie=" + nCopie + ", idAutore=" + idAutore + "]";
	}
	
	public LibroDTO(){}
	
	
	
	
}
