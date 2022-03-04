package com.ibm.academia.restapi.fraudes.servicios;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Pais;

public interface IPaisService {

	Pais buscarPaisPorIp(String ip);
	
}
