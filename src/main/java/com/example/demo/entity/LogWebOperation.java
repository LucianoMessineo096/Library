package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LogWebOperation {
	
	@Id
	@Column(name="id_operazione")
	private int idOperazione;
	private Date data;
	private String URLRequest;
	
	public int getIdOperazione() {
		return idOperazione;
	}
	public void setIdOperazione(int idOperazione) {
		this.idOperazione = idOperazione;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getURLRequest() {
		return URLRequest;
	}
	public void setURLRequest(String uRLRequest) {
		URLRequest = uRLRequest;
	}
	@Override
	public String toString() {
		return "LogWebOperation [idOperazione=" + idOperazione + ", data=" + data + ", URLRequest=" + URLRequest + "]";
	}
	public LogWebOperation(int idOperazione, Date data, String uRLRequest) {
		super();
		this.idOperazione = idOperazione;
		this.data = data;
		URLRequest = uRLRequest;
	}
	
	public LogWebOperation() {
		
	}
	
	

}
