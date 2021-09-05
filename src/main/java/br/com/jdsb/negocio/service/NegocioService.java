package br.com.jdsb.negocio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdsb.negocio.dao.BancoDao;
import br.com.jdsb.negocio.dao.ClienteFornecDao;

@Service
public class NegocioService {

	@Autowired
	private BancoDao bancoDao;

	@Autowired
	private ClienteFornecDao clientFornecDao;

	public BancoDao getBancoDao() {
		return bancoDao;
	}

	public ClienteFornecDao getClientFornecDao() {
		return clientFornecDao;
	}



}
