package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.LogWebOperation;

public interface LogService {
	
	public void registraLogElement(String url);
	public List<LogWebOperation> statisticheNelPeriodo(LocalDateTime ldt);
}
