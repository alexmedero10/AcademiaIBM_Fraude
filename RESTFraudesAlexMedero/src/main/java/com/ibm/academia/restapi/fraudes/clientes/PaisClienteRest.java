package com.ibm.academia.restapi.fraudes.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "api-pais", url = "${url.ipapi}")
public interface PaisClienteRest {
	
	@GetMapping("/{ip}?access_key=${accesskey.ipapi}&language=es")
	String buscarPaisPorIp(@PathVariable String ip);

}
