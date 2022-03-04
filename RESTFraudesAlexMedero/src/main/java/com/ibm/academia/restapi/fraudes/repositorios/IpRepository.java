package com.ibm.academia.restapi.fraudes.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Ip;

@Repository
public interface IpRepository extends CrudRepository<Ip, String>{

}
