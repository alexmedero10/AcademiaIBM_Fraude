package com.ibm.academia.restapi.fraudes.modelo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MonedaDTO implements Serializable{

	private String codigoISO;
	private BigDecimal cotizacionEuros;
	
	private static final long serialVersionUID = 5755065965561366676L;
}
