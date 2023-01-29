package com.example.demo.dto;

import javax.persistence.Column;
import javax.persistence.Id;

public class AutoreDTO {
	
	
	private int idAutore;
	private String nome;
	private String cognome;
	private String NazioneResidenza;
	
	
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
	public AutoreDTO(int idAutore, String nome, String cognome, String nazioneResidenza) {
		super();
		this.idAutore = idAutore;
		this.nome = nome;
		this.cognome = cognome;
		NazioneResidenza = nazioneResidenza;
	}
	
	public AutoreDTO(){}
}
