package com.ibm.academia.restapi.fraudes.servicios;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Moneda;

public interface IMonedaService {

	/**
	 * Método para buscar moneda por codigo iso de país
	 * @param codigoISOPais - Parámetro del codigo iso del país
	 * @return Objeto tipo Moneda
	 * @author AML - 28/02/2022
	 */
	Moneda buscarMonedaPorCodigoISOPais(String codigoISOPais);
	
	/**
	 * Método para buscar cotizacion en euros por codigo iso de moneda
	 * @param moneda - Objeto tipo moneda con atributo no nulo de codigo iso
	 * @return Objeto Moneda con datos existentes en el parámetro y cotizacion en euros
	 * @author AML - 28/02/2022
	 */
	Moneda buscarCotizacionPorMoneda(Moneda moneda);
	
}
