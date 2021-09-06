package br.com.jdsb.negocio.menu;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.io.Resource;

import br.com.jdsb.negocio.service.NegocioService;

public interface NavegarAte {

	public void navegarAte( Map<String, Resource> mapa,NegocioService service,String formulario,DataSource dataSource,String nmRelatorio);

}
