package com.ibm.academia.restapi.fraudes.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Getter
@Setter
@ToString
public class FraudeDTO implements Serializable{

	private String codigoISOPais;
	private String nombrePais;
	private String codigoISOMoneda;
	private BigDecimal cotizacionEuros;
	
	private static final long serialVersionUID = -3336944642519549167L;
}
