package com.example.demo.dto;

public class CategoriaDTO {
	
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

	public void Categoria(String categoria) {
		
		this.categoria = categoria;
	}

}
