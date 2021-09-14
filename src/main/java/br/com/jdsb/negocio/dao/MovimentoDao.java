package br.com.jdsb.negocio.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.jdsb.negocio.AnoBase;
import br.com.jdsb.negocio.Banco;
import br.com.jdsb.negocio.CentroCusto;
import br.com.jdsb.negocio.ClienteFornecedor;
import br.com.jdsb.negocio.Despesa;
import br.com.jdsb.negocio.Movimento;
import br.com.jdsb.negocio.Receita;

public interface MovimentoDao extends CrudRepository<Movimento, Long> {
     String consultaSaida =          " select sum(coalesce(vlMovimento,0)) from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
									+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
									+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
									+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
									+ "   and  ( DATE(dtMovimento) between me.dtCompetencia and LAST_DAY(me.dtCompetencia) ) and ( receita in (select r from Receita r where tpReceita = ?9 ) or ?9 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?10 ) or ?10 is null ) and tpMovimento in ('Lançamento de Despesa') ";
     String consultaEntrada =          " select sum(coalesce(vlMovimento,0)) from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
									+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
									+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
									+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
									+ "   and  ( DATE(dtMovimento) between me.dtCompetencia and LAST_DAY(me.dtCompetencia) ) and ( receita in (select r from Receita r where tpReceita = ?9 ) or ?9 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?10 ) or ?10 is null ) and tpMovimento in ('Inventário','Lançamento de Receita') ";

     String consultaSaidaDiaria =          " select sum(coalesce(vlMovimento,0)) from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
				+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
				+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
				+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
				+ "   and  ( DATE(dtMovimento) = STR_TO_DATE(CONCAT(DATE_FORMAT(?11, '%Y-%m-'), CONVERT(dia.vlDia, CHARACTER)), '%Y-%m-%d') ) and ( receita in (select r from Receita r where tpReceita = ?9 ) or ?9 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?10 ) or ?10 is null ) and tpMovimento in ('Lançamento de Despesa') ";
     String consultaEntradaDiaria =          " select sum(coalesce(vlMovimento,0)) from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
				+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
				+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
				+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
				+ "   and  ( DATE(dtMovimento) = STR_TO_DATE(CONCAT(DATE_FORMAT(?11, '%Y-%m-'), CONVERT(dia.vlDia, CHARACTER)), '%Y-%m-%d')) and ( receita in (select r from Receita r where tpReceita = ?9 ) or ?9 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?10 ) or ?10 is null ) and tpMovimento in ('Inventário','Lançamento de Receita') ";

	List<Movimento> findAll();

	List<Movimento> findByTpMovimento(String tpMovimento);

	@Query(value="from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
			+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
			+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
			+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
			+ "   and  ( (dtMovimento > ?9 and ?10 is null or ?9 is null) or (dtMovimento between ?9 and ?10 or ?9 is null)) and ( receita in (select r from Receita r where tpReceita = ?11 ) or ?11 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?12 ) or ?12 is null ) ")
	List<Movimento> consultaPersonalidada(ClienteFornecedor cliente,CentroCusto centroCusto,Banco banco,Receita receita,Despesa despesa,String dsFormaPagamento, BigDecimal vlMovimento,String tpOperacao,LocalDateTime dtIncial,LocalDateTime dtFinal,String tpReceita,String tpDespesa);

	@Query(value=" select min(dtMovimento) from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
			+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
			+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
			+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
			+ "   and  ( (dtMovimento > ?9 and ?10 is null or ?9 is null) or (dtMovimento between ?9 and ?10 or ?9 is null)) and ( receita in (select r from Receita r where tpReceita = ?11 ) or ?11 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?12 ) or ?12 is null ) ")
	LocalDateTime consultaMenorData(ClienteFornecedor cliente,CentroCusto centroCusto,Banco banco,Receita receita,Despesa despesa,String dsFormaPagamento, BigDecimal vlMovimento,String tpOperacao,LocalDateTime dtIncial,LocalDateTime dtFinal,String tpReceita,String tpDespesa);

	@Query(value=" select sum(coalesce(vlMovimento,0)) from Movimento where dtMovimento < ?1 and tpMovimento in ('Inventário','Lançamento de Receita') ")
	BigDecimal consultaSaldoAtenterior(LocalDateTime dtMovimento);

	@Query(value=" select sum(coalesce(vlMovimento,0)) from Movimento where dtMovimento < ?1 and tpMovimento in ('Lançamento de Despesa') ")
	BigDecimal consultaSaldoAtenteriorSaida(LocalDateTime dtMovimento);

	@Query(value=" select sum(coalesce(vlMovimento,0)) from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
			+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
			+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
			+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
			+ "   and  ( (dtMovimento > ?9 and ?10 is null or ?9 is null) or (dtMovimento between ?9 and ?10 or ?9 is null)) and ( receita in (select r from Receita r where tpReceita = ?11 ) or ?11 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?12 ) or ?12 is null ) and tpMovimento in ('Inventário','Lançamento de Receita') ")
	BigDecimal consultaSaldoEntrada(ClienteFornecedor cliente,CentroCusto centroCusto,Banco banco,Receita receita,Despesa despesa,String dsFormaPagamento, BigDecimal vlMovimento,String tpOperacao,LocalDateTime dtIncial,LocalDateTime dtFinal,String tpReceita,String tpDespesa);

	@Query(value=" select sum(coalesce(vlMovimento,0)) from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
			+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
			+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
			+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
			+ "   and  ( (dtMovimento > ?9 and ?10 is null or ?9 is null) or (dtMovimento between ?9 and ?10 or ?9 is null)) and ( receita in (select r from Receita r where tpReceita = ?11 ) or ?11 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?12 ) or ?12 is null ) and tpMovimento in ('Lançamento de Despesa') ")
	BigDecimal consultaSaldoSaida(ClienteFornecedor cliente,CentroCusto centroCusto,Banco banco,Receita receita,Despesa despesa,String dsFormaPagamento, BigDecimal vlMovimento,String tpOperacao,LocalDateTime dtIncial,LocalDateTime dtFinal,String tpReceita,String tpDespesa);

	@Query(value=" select me.vlMetaReceita as vlMetaReceita, me.vlMetaDespesa as vlMetaDespesa, me.dtCompetencia as dtCompetencia, coalesce(("+consultaSaida+"),0) as vlMovimentoDespesa, coalesce(("+consultaEntrada+"),0) as vlMovimentoReceita from Meta me where me.anoBase = ?11 ")
	List<FluxoCaixa> consultaValorTotal(ClienteFornecedor cliente,CentroCusto centroCusto,Banco banco,Receita receita,Despesa despesa,String dsFormaPagamento, BigDecimal vlMovimento,String tpOperacao,String tpReceita,String tpDespesa,AnoBase anoBase);

	@Query(value=" select me.vlMetaReceita as vlMetaReceita, me.vlMetaDespesa as vlMetaDespesa, me.dtCompetencia as dtCompetencia, coalesce(("+consultaSaida+"),0) as vlMovimentoDespesa, coalesce(("+consultaEntrada+"),0) as vlMovimentoReceita from Meta me where me.anoBase = ?11 and me.dtCompetencia = ?12 ")
	List<FluxoCaixa> consultaValorTotalMes(ClienteFornecedor cliente,CentroCusto centroCusto,Banco banco,Receita receita,Despesa despesa,String dsFormaPagamento, BigDecimal vlMovimento,String tpOperacao,String tpReceita,String tpDespesa,AnoBase anoBase,LocalDateTime dtCompetencia);

	@Query(value=" select dia.vlDia as vlDia, coalesce(("+consultaSaidaDiaria+"),0) as vlMovimentoDespesa, coalesce(("+consultaEntradaDiaria+"),0) as vlMovimentoReceita from Dia dia WHERE dia.vlDia BETWEEN 1 and Day(last_day(?11)) ")
	List<FluxoCaixaDiario> consultaValorTotalDia(ClienteFornecedor cliente,CentroCusto centroCusto,Banco banco,Receita receita,Despesa despesa,String dsFormaPagamento, BigDecimal vlMovimento,String tpOperacao,String tpReceita,String tpDespesa,LocalDateTime dtCompetencia);


	@Query(value=" select sum(case when tpMovimento in('Lançamento de Despesa') then (vlMovimento * -1) else vlMovimento end) as vlMovimento, centroCusto.dsCentroCusto as dsCentroCusto from Movimento where ( clienteFornecedor = ?1 or ?1 is null) and ( centroCusto = ?2 or ?2 is null ) "
			+ "   and (banco = ?3 or ?3 is null) and (receita = ?4 or ?4 is null) and (despesa = ?5 or ?5 is null) "
			+ "   and (dsFormaPagamento = ?6 or ?6 is null) "
			+ "   and ( (vlMovimento = ?7 and ?8 = 'A' or ?7 is null ) or (vlMovimento > ?7 and ?8 = 'B' or ?7 is null  ) or (vlMovimento < ?7 and ?8 = 'C'  or ?7 is null )) "
			+ "   and  ( dtMovimento between ?9 and ?10 ) and ( receita in (select r from Receita r where tpReceita = ?11 ) or ?11 is null ) and ( despesa in (select r from Despesa r where tpDespesa = ?12 ) or ?12 is null ) group by centroCusto.dsCentroCusto ")
	List<FluxoCaixaCentroCusto> consultaSaldoCentroCusto(ClienteFornecedor cliente,CentroCusto centroCusto,Banco banco,Receita receita,Despesa despesa,String dsFormaPagamento, BigDecimal vlMovimento,String tpOperacao,LocalDateTime dtIncial,LocalDateTime dtFinal,String tpReceita,String tpDespesa);

}
