package com.ibm.academia.restapi.fraudes.servicios;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Pais;

public interface IPaisService {

	/**
	 * Método para buscar país por ip
	 * @param ip - Parámetro de la ip
	 * @return Objeto tipo Pais
	 * @author AML - 28/02/2022
	 */
	Pais buscarPaisPorIp(String ip);
	
}
