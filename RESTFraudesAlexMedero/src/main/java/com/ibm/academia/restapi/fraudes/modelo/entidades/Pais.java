package com.ibm.academia.restapi.fraudes.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pais implements Serializable{

	@Id
	@NotEmpty
	@Column(name = "ip", unique = true)
	private String ip;
	
	@Column(name = "codigo_iso")
	private String codigoISO;
	
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@NotEmpty
	@Column(name = "usuario_creacion", nullable = false)
	private String usuarioCreacion;
	
	@Column(name = "fecha_creacion", nullable = false)
	private Date fechaCreacion;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	
	public Pais(String ip, String codigoISO, String nombre, String usuarioCreacion) {
		this.ip = ip;
		this.codigoISO = codigoISO;
		this.nombre = nombre;
		this.usuarioCreacion = usuarioCreacion;
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
	
	
	@PrePersist
	public void antesPersistir() {
		this.fechaCreacion = new Date();
	}
	
	@PreUpdate
	public void antesActualizar() {
		this.fechaModificacion = new Date();
	}
	

	private static final long serialVersionUID = -2080674050489609991L;
}
