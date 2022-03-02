package com.ibm.academia.restapi.fraudes.controladores;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.fraudes.clientes.CotizacionClienteRest;
import com.ibm.academia.restapi.fraudes.clientes.MonedaClienteRest;
import com.ibm.academia.restapi.fraudes.clientes.PaisClienteRest;
import com.ibm.academia.restapi.fraudes.modelo.dto.FraudeDTO;
import com.ibm.academia.restapi.fraudes.modelo.entidades.Moneda;
import com.ibm.academia.restapi.fraudes.modelo.entidades.Pais;
import com.ibm.academia.restapi.fraudes.modelo.mapper.FraudeMapper;

@RestController
@RequestMapping("/fraude")
public class FraudeController {

	private final static Logger logger = LoggerFactory.getLogger(FraudeController.class);
	
	@Autowired
	private PaisClienteRest paisCliente;
	
	@Autowired
	private MonedaClienteRest monedaCliente;
	
	@Autowired
	private CotizacionClienteRest cotizacionCliente;
	
	
	@GetMapping("/ip/{ip}")
	public ResponseEntity<?> consultarPaisPorIp(@PathVariable String ip){
		JSONObject paisJSON = new JSONObject(paisCliente.buscarPaisPorIp(ip));
		String ipJSON = paisJSON.getString("ip");
		String codigoISOPais = paisJSON.getString("country_code");
		String nombrePais = paisJSON.getString("country_name");
		
		Pais pais = new Pais(ipJSON, codigoISOPais, nombrePais);

		JSONObject monedaJSON = new JSONObject(monedaCliente.buscarMonedaPorCodigoISOPais(pais.getCodigoISO()));
		String codigoISO = (String) monedaJSON.getJSONArray("currencies").getJSONObject(0).get("code");
		String nombre = (String) monedaJSON.getJSONArray("currencies").getJSONObject(0).get("name");
		String simbolo = (String) monedaJSON.getJSONArray("currencies").getJSONObject(0).get("symbol");
		
		Moneda moneda = new Moneda(codigoISO, nombre, simbolo);		
		
		JSONObject cotizacionJSON = new JSONObject(cotizacionCliente.buscarCotizacionPorMoneda(moneda.getCodigoISO()));	
		moneda.setCotizacionEuros(cotizacionJSON.getJSONObject("rates").getBigDecimal(moneda.getCodigoISO()));
		
		FraudeDTO fraudeDTO = new FraudeDTO();
		fraudeDTO = FraudeMapper.mapFraude(pais, moneda);
		
		return new ResponseEntity<FraudeDTO>(fraudeDTO, HttpStatus.OK);
	}

	
}
