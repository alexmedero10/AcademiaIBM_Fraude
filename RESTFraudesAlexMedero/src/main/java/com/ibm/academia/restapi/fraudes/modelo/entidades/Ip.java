package com.ibm.academia.restapi.fraudes.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ipes")
public class Ip implements Serializable {

	@Id
    @Column(name = "ip", unique = true)
    private String ip;

    @NotNull
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    public Ip(String ip, String usuarioCreacion) {
        this.ip = ip;
        this.usuarioCreacion = usuarioCreacion;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ip{");
        sb.append("ip=").append(ip);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ip that = (Ip) o;
        return ip.equals(that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip);
    }

    @PrePersist
    private void antesPersistir(){
        this.fechaCreacion = new Date();
    }

    @PreUpdate
    private void antesActualizar(){
        this.fechaModificacion = new Date();
    }
    
    private static final long serialVersionUID = -1218304622155827956L;
}