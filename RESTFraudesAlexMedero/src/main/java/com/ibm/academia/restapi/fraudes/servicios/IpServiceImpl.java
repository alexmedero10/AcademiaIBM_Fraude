package com.ibm.academia.restapi.fraudes.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Ip;
import com.ibm.academia.restapi.fraudes.repositorios.IpRepository;

@Service
public class IpServiceImpl implements IIpService{

	@Autowired
	private IpRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Ip> buscarIp(String ip) {
		return repository.findById(ip);
	}

	@Override
	@Transactional
	public Ip banearIp(Ip entidad) {
		return repository.save(entidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Ip> buscarTodas() {
		return repository.findAll();
	}

	@Override
	@Transactional()
	public void desbanearIp(String ip) {
		repository.deleteById(ip);
	}

}
