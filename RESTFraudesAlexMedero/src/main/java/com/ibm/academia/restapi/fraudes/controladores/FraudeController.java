package com.ibm.academia.restapi.fraudes.controladores;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.fraudes.modelo.dto.FraudeDTO;
import com.ibm.academia.restapi.fraudes.modelo.entidades.Moneda;
import com.ibm.academia.restapi.fraudes.modelo.entidades.Pais;
import com.ibm.academia.restapi.fraudes.modelo.mapper.FraudeMapper;
import com.ibm.academia.restapi.fraudes.servicios.IMonedaService;
import com.ibm.academia.restapi.fraudes.servicios.IPaisService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/fraude")
public class FraudeController {

	private final static Logger logger = LoggerFactory.getLogger(FraudeController.class);
	
	@Autowired
	private IPaisService paisService;
	
	@Autowired
	private IMonedaService monedaService;
	
	@CircuitBreaker(name = "pais", fallbackMethod = "metodoAlternativo")
	@TimeLimiter(name = "pais")
	@GetMapping("/ip/{ip}")
	public CompletableFuture<ResponseEntity<?>> consultarPaisPorIp(@PathVariable String ip){
		Pais pais = paisService.buscarPaisPorIp(ip);
		Moneda moneda = monedaService.buscarMonedaPorCodigoISOPais(pais.getCodigoISO());
		moneda = monedaService.buscarCotizacionPorMoneda(moneda);	
		FraudeDTO fraudeDTO = FraudeMapper.mapFraude(pais, moneda);
		
		return CompletableFuture.supplyAsync(() -> new ResponseEntity<FraudeDTO>(fraudeDTO, HttpStatus.OK));
	}

	public CompletableFuture<ResponseEntity<?>> metodoAlternativo(String ip, Throwable e){
		logger.info("error: " + e.getMessage());
		return CompletableFuture.supplyAsync(() -> new ResponseEntity<String>(e.getMessage(), HttpStatus.OK));
	}
	
	
}
