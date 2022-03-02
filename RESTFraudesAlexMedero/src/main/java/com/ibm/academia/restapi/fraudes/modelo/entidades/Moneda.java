package com.ibm.academia.restapi.fraudes.modelo.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class Moneda implements Serializable{

	private String codigoISO;
	private String nombre;
	private String symbol;
	private BigDecimal cotizacionEuros;
	
	
	public Moneda(String codigoISO, String nombre, String symbol) {
		this.codigoISO = codigoISO;
		this.nombre = nombre;
		this.symbol = symbol;
	}



	private static final long serialVersionUID = -8775009792973289183L;
}
