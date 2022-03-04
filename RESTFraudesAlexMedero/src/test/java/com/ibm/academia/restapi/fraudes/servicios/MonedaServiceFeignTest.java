package com.ibm.academia.restapi.fraudes.servicios;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.academia.restapi.fraudes.modelo.entidades.Moneda;

@SpringBootTest
public class MonedaServiceFeignTest {

	@Autowired
	private IMonedaService monedaService;
	
	@Test
	@DisplayName("Test: Buscar moneda por codigo del pais")
	void buscarMonedaPorCodigoISOPais() {
		
		String codigoISOPais = "US";
		
		Moneda moneda = monedaService.buscarMonedaPorCodigoISOPais(codigoISOPais);
		
		assertThat(moneda.getCodigoISO()).isEqualTo("USD");
		assertThat(moneda.getCotizacionEuros()).isNull();
	}

	@Test
	@DisplayName("Test: Buscar cotizacion por moneda")
	void buscarCotizacionPorMoneda() {

		Moneda moneda = new Moneda("USD", "United States dollar", "$");
		
		moneda = monedaService.buscarCotizacionPorMoneda(moneda);
		
		assertThat(moneda.getCotizacionEuros()).isGreaterThan(new BigDecimal(1));
		assertThat(moneda.getCotizacionEuros()).isNotNegative();
		
	}
	
}
