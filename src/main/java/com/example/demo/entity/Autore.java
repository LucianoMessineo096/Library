package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Autore {
	
	@Id
	@Column(name="id_autore")
	private int idAutore;
	private String nome;
	private String cognome;
	private String NazioneResidenza;
	
	/*
	 
	 {
	 	"idAutore":2,
	 	"nome":"ciccio",
	 	"cognome":"pasticcio",
	 	"NazioneResidenza":"italia"
	 }
	 
	 */
	
	public int getIdAutore() {
		return idAutore;
	}
	public void setIdAutore(int idAutore) {
		this.idAutore = idAutore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNazioneResidenza() {
		return NazioneResidenza;
	}
	public void setNazioneResidenza(String nazioneResidenza) {
		NazioneResidenza = nazioneResidenza;
	}
	@Override
	public String toString() {
		return "Autore [idAutore=" + idAutore + ", nome=" + nome + ", cognome=" + cognome + ", NazioneResidenza="
				+ NazioneResidenza + "]";
	}
	public Autore(int idAutore, String nome, String cognome, String nazioneResidenza) {
		super();
		this.idAutore = idAutore;
		this.nome = nome;
		this.cognome = cognome;
		NazioneResidenza = nazioneResidenza;
	}
	
	public Autore(){}
	
	

}
