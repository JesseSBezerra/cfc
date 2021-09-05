package br.com.jdsb.negocio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdsb.negocio.dao.BancoDao;
import br.com.jdsb.negocio.dao.CentroCustoDao;
import br.com.jdsb.negocio.dao.ClienteFornecDao;
import br.com.jdsb.negocio.dao.DesepesaDao;
import br.com.jdsb.negocio.dao.ReceitaDao;

@Service
public class NegocioService {

	@Autowired
	private BancoDao bancoDao;

	@Autowired
	private ClienteFornecDao clientFornecDao;

	@Autowired
	private ReceitaDao receitaDao;

	@Autowired
	private DesepesaDao desepesaDao;

	@Autowired
	private CentroCustoDao centroCustoDao;

	public ReceitaDao getReceitaDao() {
		return receitaDao;
	}

	public DesepesaDao getDesepesaDao() {
		return desepesaDao;
	}

	public CentroCustoDao getCentroCustoDao() {
		return centroCustoDao;
	}

	public BancoDao getBancoDao() {
		return bancoDao;
	}

	public ClienteFornecDao getClientFornecDao() {
		return clientFornecDao;
	}



}
