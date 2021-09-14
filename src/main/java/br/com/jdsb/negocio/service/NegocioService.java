package br.com.jdsb.negocio.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jdsb.negocio.AnoBase;
import br.com.jdsb.negocio.Configuracao;
import br.com.jdsb.negocio.Dia;
import br.com.jdsb.negocio.FluxoCaixaTo;
import br.com.jdsb.negocio.FluxoCaixaTotalTo;
import br.com.jdsb.negocio.Meta;
import br.com.jdsb.negocio.dao.AnoBaseDao;
import br.com.jdsb.negocio.dao.BancoDao;
import br.com.jdsb.negocio.dao.CentroCustoDao;
import br.com.jdsb.negocio.dao.ClienteFornecDao;
import br.com.jdsb.negocio.dao.ConfiguracaoDao;
import br.com.jdsb.negocio.dao.DesepesaDao;
import br.com.jdsb.negocio.dao.DiaDao;
import br.com.jdsb.negocio.dao.FluxoCaixa;
import br.com.jdsb.negocio.dao.MetaDao;
import br.com.jdsb.negocio.dao.MovimentoDao;
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

	@Autowired
	private MovimentoDao movimentoDao;

	@Autowired
	private AnoBaseDao anoBaseDao;

	@Autowired
	private MetaDao metaDao;

	@Autowired
	private DiaDao diaDao;

	@Autowired
	private ConfiguracaoDao configuracaoDao;

	public AnoBaseDao getAnoBaseDao() {
		return anoBaseDao;
	}

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

	public MovimentoDao getMovimentoDao() {
		return movimentoDao;
	}

	public MetaDao getMetaDao() {
		return metaDao;
	}

	public DiaDao getDiaDao() {
		return diaDao;
	}

	public ConfiguracaoDao getConfiguracaoDao() {
		return configuracaoDao;
	}

	public void cadastrarDias(){
		Configuracao configuracao = this.configuracaoDao.findByNmConfiguracao("SN_CADASTRO_DIAS");
		if(configuracao==null){
			for(int i = 1; i<=31;i++){
                  Dia dia = new Dia();
                  dia.setVlDia(i);
                  this.diaDao.save(dia);
			}
			configuracao = new Configuracao();
			configuracao.setNmConfiguracao("SN_CADASTRO_DIAS");
			configuracao.setDsConfiguracao("Cadastro de dias do mes, tabela apenas para não ter de fazer union");
			configuracao.setVlConfiguracao("S");
			this.configuracaoDao.save(configuracao);
		}
	}

	public void cadastrarAnoBase(Integer vlAnoBase){
       if(vlAnoBase!=null){
    	 AnoBase anoBasePesquisa = this.anoBaseDao.findByVlAnoBase(vlAnoBase);
    	 if(anoBasePesquisa==null){
    	   AnoBase anoBase = new AnoBase();
    	   anoBase.setVlAnoBase(vlAnoBase);
    	   anoBase.setSnAtivo("S");
    	   anoBase.setSnLancado("S");
    	   this.anoBaseDao.save(anoBase);

    	   List<Meta> metas = new ArrayList<Meta>();
    	   for(int i = 1; i <= 12;i++){
               LocalDateTime data = LocalDateTime.of(vlAnoBase, i, 1, 0, 0);
               Meta meta = new Meta();
               meta.setAnoBase(anoBase);
               meta.setDtCompetencia(data);
               meta.setVlMetaDespesa(BigDecimal.ZERO);
               meta.setVlMetaReceita(BigDecimal.ZERO);
               metas.add(meta);
    	   }
    	   this.metaDao.saveAll(metas);
    	 }
       }
	}

	public List<FluxoCaixaTo> obterFluxosDeCaixa(List<FluxoCaixa> fluxosDeCaixa, List<FluxoCaixaTotalTo> totalizadores){

		List<FluxoCaixaTo> fluxosDeRetorno = new ArrayList<FluxoCaixaTo>();
		BigDecimal vlSaldoAnterior = BigDecimal.ZERO;

		FluxoCaixaTotalTo caixaTotalReceita = new FluxoCaixaTotalTo();
		caixaTotalReceita.setTipoMovimentoCaixa("Receitas");

		FluxoCaixaTotalTo caixaTotalDespesa = new FluxoCaixaTotalTo();
		caixaTotalDespesa.setTipoMovimentoCaixa("Despesas");

		FluxoCaixaTotalTo caixaTotalLucroPrejuizo = new FluxoCaixaTotalTo();
		caixaTotalLucroPrejuizo.setTipoMovimentoCaixa("Lucro/Prejuízo");

		FluxoCaixaTotalTo caixaTotalLucratividade = new FluxoCaixaTotalTo();
		caixaTotalLucratividade.setTipoMovimentoCaixa("Lucratividade");

		BigDecimal totalMeta = BigDecimal.ZERO;
		BigDecimal totalMovimento = BigDecimal.ZERO;


		for(FluxoCaixa caixa:fluxosDeCaixa){
            FluxoCaixaTo to = new FluxoCaixaTo(caixa,vlSaldoAnterior);
            vlSaldoAnterior = to.getVlMovimentoAcumulado();
            fluxosDeRetorno.add(to);

            caixaTotalReceita.setVlTotalMovimento(caixaTotalReceita.getVlTotalMovimento().add(to.getVlMovimentoReceita()));
            caixaTotalReceita.setVlTotalMeta(caixaTotalReceita.getVlTotalMeta().add(to.getVlMetaReceita()));
            if(caixa.getDtCompetencia().isBefore(LocalDateTime.now())){
            	caixaTotalReceita.setVlParcialMeta(caixaTotalReceita.getVlParcialMeta().add(to.getVlMetaReceita()));
            	caixaTotalReceita.setVlParcialMovimento(caixaTotalReceita.getVlParcialMovimento().add(to.getVlMovimentoReceita()));
            }

            caixaTotalDespesa.setVlTotalMovimento(caixaTotalDespesa.getVlTotalMovimento().add(to.getVlMovimentoDespesa()));
            caixaTotalDespesa.setVlTotalMeta(caixaTotalDespesa.getVlTotalMeta().add(to.getVlMetaDespesa()));
            if(caixa.getDtCompetencia().isBefore(LocalDateTime.now())){
            	caixaTotalDespesa.setVlParcialMeta(caixaTotalDespesa.getVlParcialMeta().add(to.getVlMetaDespesa()));
            	caixaTotalDespesa.setVlParcialMovimento(caixaTotalDespesa.getVlParcialMovimento().add(to.getVlMovimentoDespesa()));
            }

            caixaTotalLucroPrejuizo.setVlTotalMovimento(caixaTotalLucroPrejuizo.getVlTotalMovimento().add(to.getVlResultadoReceita()));
            caixaTotalLucroPrejuizo.setVlTotalMeta(caixaTotalLucroPrejuizo.getVlTotalMeta().add(to.getVlResultadoMeta()));
            if(caixa.getDtCompetencia().isBefore(LocalDateTime.now())){
            	caixaTotalLucroPrejuizo.setVlParcialMeta(caixaTotalLucroPrejuizo.getVlParcialMeta().add(to.getVlResultadoMeta()));
            	caixaTotalLucroPrejuizo.setVlParcialMovimento(caixaTotalLucroPrejuizo.getVlParcialMovimento().add(to.getVlResultadoReceita()));
            }

		}

		totalizadores.add(caixaTotalReceita);
		totalizadores.add(caixaTotalDespesa);
		totalizadores.add(caixaTotalLucroPrejuizo);

		caixaTotalLucratividade.setVlTotalMeta(caixaTotalReceita.getVlTotalMeta());
		 try{
			 caixaTotalLucratividade.setVlTotalMeta((((caixaTotalReceita.getVlTotalMeta().subtract(caixaTotalLucroPrejuizo.getVlTotalMeta())).multiply(new BigDecimal(100))).divide(caixaTotalReceita.getVlTotalMeta(), 5, RoundingMode.HALF_UP)).subtract(new BigDecimal(100)).multiply(new BigDecimal(-1)));//.multiply(new BigDecimal(10));
			}catch (Exception e) {
				caixaTotalLucratividade.setVlTotalMeta(BigDecimal.ZERO);
		    }

		 try{
			 caixaTotalLucratividade.setVlTotalMovimento((((caixaTotalReceita.getVlTotalMovimento().subtract(caixaTotalLucroPrejuizo.getVlTotalMovimento())).multiply(new BigDecimal(100))).divide(caixaTotalReceita.getVlTotalMovimento(), 5, RoundingMode.HALF_UP)).subtract(new BigDecimal(100)).multiply(new BigDecimal(-1)));//.multiply(new BigDecimal(10));
			}catch (Exception e) {
				caixaTotalLucratividade.setVlTotalMeta(BigDecimal.ZERO);
		 }

		 try{
			 caixaTotalLucratividade.setVlParcialMeta((((caixaTotalReceita.getVlParcialMeta().subtract(caixaTotalLucroPrejuizo.getVlParcialMeta())).multiply(new BigDecimal(100))).divide(caixaTotalReceita.getVlParcialMeta(), 5, RoundingMode.HALF_UP)).subtract(new BigDecimal(100)).multiply(new BigDecimal(-1)));//.multiply(new BigDecimal(10));
			}catch (Exception e) {
				caixaTotalLucratividade.setVlParcialMeta(BigDecimal.ZERO);
		    }

		 try{
			 caixaTotalLucratividade.setVlParcialMovimento((((caixaTotalReceita.getVlParcialMovimento().subtract(caixaTotalLucroPrejuizo.getVlParcialMovimento())).multiply(new BigDecimal(100))).divide(caixaTotalReceita.getVlParcialMovimento(), 5, RoundingMode.HALF_UP)).subtract(new BigDecimal(100)).multiply(new BigDecimal(-1)));//.multiply(new BigDecimal(10));
			}catch (Exception e) {
				caixaTotalLucratividade.setVlParcialMeta(BigDecimal.ZERO);
		 }

		 totalizadores.add(caixaTotalLucratividade);

		return fluxosDeRetorno;
	}


}
