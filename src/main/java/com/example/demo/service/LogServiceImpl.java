package com.example.demo.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.LogServiceInstantRepository;
import com.example.demo.entity.LogWebOperation;

@Service
@Transactional
public class LogServiceImpl implements LogService{
	
	@Autowired 
	private LogServiceInstantRepository dao_log;
	
	@Override
	public void registraLogElement(String url) {
		// TODO Auto-generated method stub
		List<LogWebOperation> operazioni = dao_log.findAll();
		LogWebOperation log = new LogWebOperation();
		int newIdOperazione;
		try {
			newIdOperazione = operazioni.get(operazioni.size()-1).getIdOperazione()+1;
			
		}catch(IndexOutOfBoundsException e) {
			newIdOperazione=0;
		}
		
		log.setIdOperazione(newIdOperazione);
		Date data = new Date();
		log.setData(data);
		log.setURLRequest(url);
		System.out.println(log);
		dao_log.save(log);
		
		
	}

	@Override
	public List<LogWebOperation> statisticheNelPeriodo(LocalDateTime ldt) {
		// TODO Auto-generated method stub
		/*
		List<LogWebOperation> statistiche = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = ldt.format(formatter);
		//stringDate.equalsIgnoreCase(formatDateTime)
        
		SimpleDateFormat DateFor = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
		List<LogWebOperation> operazioni = dao_log.findAll();
		
		for(LogWebOperation log: operazioni) {
			String stringDate= DateFor.format(log.getData());
			
			if(??) {
				
				//inserisco log nella lista 
				
				statistiche.add(log);
			}
		}
		return statistiche;
		*/
		return null;
	}

}
