package com.ibm.academia.restapi.fraudes.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pais implements Serializable{

	private String ip;
	private String codigoISO;
	private String nombre;
	
	
	public Pais(String ip, String codigoISO, String nombre) {
		this.ip = ip;
		this.codigoISO = codigoISO;
		this.nombre = nombre;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(ip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pais))
			return false;
		Pais other = (Pais) obj;
		return Objects.equals(ip, other.ip);
	}
	

	private static final long serialVersionUID = -2080674050489609991L;
}
