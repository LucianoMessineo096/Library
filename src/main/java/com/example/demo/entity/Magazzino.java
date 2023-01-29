package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Magazzino {
	
	@Id
	@Column(name="id_magazzino")
	private int idMagazzino;
	private int quantita;
	private String statoStock;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "isbn")
	private Libro isbn;
	
	
	public int getIdMagazzino() {
		return idMagazzino;
	}
	public void setIdMagazzino(int idMagazzino) {
		this.idMagazzino = idMagazzino;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public String getStatoStock() {
		return statoStock;
	}
	public void setStatoStock(String statoStock) {
		this.statoStock = statoStock;
	}
	
	
	public Libro getIsbn() {
		return isbn;
	}
	public void setIsbn(Libro isbn) {
		this.isbn = isbn;
	}
	@Override
	public String toString() {
		return "Magazzino [idMagazzino=" + idMagazzino + ", quantita=" + quantita + ", statoStock=" + statoStock
				+ ", isbn=" + isbn + "]";
	}
	public Magazzino(int idMagazzino, int quantita, String statoStock, Libro isbn) {
		super();
		this.idMagazzino = idMagazzino;
		this.quantita = quantita;
		this.statoStock = statoStock;
		this.isbn = isbn;
	}
	
	public Magazzino() {}
	
}
