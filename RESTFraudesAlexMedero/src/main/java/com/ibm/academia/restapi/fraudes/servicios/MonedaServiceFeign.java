package com.ibm.academia.restapi.fraudes.servicios;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.fraudes.clientes.CotizacionClienteRest;
import com.ibm.academia.restapi.fraudes.clientes.MonedaClienteRest;
import com.ibm.academia.restapi.fraudes.modelo.entidades.Moneda;

@Service
public class MonedaServiceFeign implements IMonedaService{

	@Autowired
	private MonedaClienteRest monedaCliente;
	
	@Autowired
	private CotizacionClienteRest cotizacionCliente;
	
	@Override
	public Moneda buscarMonedaPorCodigoISOPais(String codigoISOPais) {
		JSONObject monedaJSON = new JSONObject(monedaCliente.buscarMonedaPorCodigoISOPais(codigoISOPais));
		String codigoISO = (String) monedaJSON.getJSONArray("currencies").getJSONObject(0).get("code");
		String nombre = (String) monedaJSON.getJSONArray("currencies").getJSONObject(0).get("name");
		String simbolo = (String) monedaJSON.getJSONArray("currencies").getJSONObject(0).get("symbol");
		
		return new Moneda(codigoISO, nombre, simbolo);
	}

	@Override
	public Moneda buscarCotizacionPorMoneda(Moneda moneda) {
		JSONObject cotizacionJSON = new JSONObject(cotizacionCliente.buscarCotizacionPorMoneda(moneda.getCodigoISO()));	
		moneda.setCotizacionEuros(new BigDecimal(cotizacionJSON.getJSONObject("rates").get(moneda.getCodigoISO()).toString()));
		return moneda;
	}
	
}
