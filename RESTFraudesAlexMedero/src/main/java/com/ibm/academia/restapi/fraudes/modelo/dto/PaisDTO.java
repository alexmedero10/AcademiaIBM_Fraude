package com.ibm.academia.restapi.fraudes.modelo.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaisDTO implements Serializable{
	
	private String codigoISO;
	private String nombre;
	
	private static final long serialVersionUID = 1499334807562498629L;
}
