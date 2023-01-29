package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categoria {
	
	@Id
	@Column(name="categoria")
	private String categoria;
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Categoria [categoria=" + categoria + "]";
	}
	public Categoria(int idCategoria, String categoria) {
		super();
		this.categoria = categoria;
	}
	
	public Categoria() {}
	
	
	
}
