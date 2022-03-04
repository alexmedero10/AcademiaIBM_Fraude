package com.ibm.academia.restapi.fraudes.servicios;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.fraudes.clientes.PaisClienteRest;
import com.ibm.academia.restapi.fraudes.excepciones.BadRequestException;
import com.ibm.academia.restapi.fraudes.excepciones.NotFoundException;
import com.ibm.academia.restapi.fraudes.modelo.entidades.Pais;

@Service
public class PaisServiceFeign implements IPaisService{

	@Autowired
	private PaisClienteRest paisCliente;
	
	@Override
	public Pais buscarPaisPorIp(String ip) {
		
		JSONObject paisJSON = new JSONObject(paisCliente.buscarPaisPorIp(ip));
		
		if(paisJSON.has("success"))
			throw new BadRequestException("Formato de ip incorrecto");
		
		if(paisJSON.get("country_code").equals(null))
			throw new NotFoundException(String.format("No se encontró información de la ip: %s", ip));
		
		String ipJSON = paisJSON.getString("ip");
		String codigoISOPais = paisJSON.getString("country_code");
		String nombrePais = paisJSON.getString("country_name");
		
		return new Pais(ipJSON, codigoISOPais, nombrePais, "AlexMedero");
	}
	
}
