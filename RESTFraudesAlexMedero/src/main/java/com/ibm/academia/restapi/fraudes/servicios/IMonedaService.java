package com.ibm.academia.restapi.fraudes.servicios;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Moneda;

public interface IMonedaService {

	Moneda buscarMonedaPorCodigoISOPais(String codigoISOPais);
	Moneda buscarCotizacionPorMoneda(Moneda moneda);
	
}
