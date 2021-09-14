package br.com.jdsb.cfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.com.jdsb.cfc.Main.StageReadlyEvent;
import br.com.jdsb.negocio.FluxoCaixaTo;
import br.com.jdsb.negocio.FluxoCaixaTotalTo;
import br.com.jdsb.negocio.dao.FluxoCaixa;
import br.com.jdsb.negocio.service.NegocioService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
@Component
@EnableAutoConfiguration
public class StageInitializer implements ApplicationListener<StageReadlyEvent> {

	@Value("classpath:/m_cadastro_banco.fxml")
	private Resource resource;

	@Value("classpath:/m_cadastro_metas.fxml")
	private Resource MMeta;


	@Value("classpath:/m_cadastro_cliente_fornec.fxml")
	private Resource mCadastroClienteFornec;

	@Value("classpath:/m_cadastro_receita.fxml")
	private Resource mCadastroReceita;

	@Value("classpath:/m_cadastro_despesa.fxml")
	private Resource mCadastroDespesa;

	@Value("classpath:/m_cadastro_centro_custo.fxml")
	private Resource mCadastroCentroCusto;

	@Value("classpath:/m_inventario.fxml")
	private Resource mInventario;

	@Value("classpath:/m_lancamento_receita.fxml")
	private Resource mLancamentoReceita;

	@Value("classpath:/m_lancamento_despesa.fxml")
	private Resource mLancamentoDespesa;

	@Value("classpath:/m_analise_movimento.fxml")
	private Resource mAnaliseMovimento;

	@Value("classpath:/m_principal.fxml")
	private Resource mPrincipal;

	@Value("classpath:/m_analise_metas.fxml")
	private Resource mAnaliseMetas;

	@Value("classpath:/m_analise_fluxo_caixa.fxml")
	private Resource mAnaliseFluxoCaixa;

	@Value("classpath:/R_RECEITA.jasper")
	private Resource RReceita;

	@Value("classpath:/R_BANCO.jasper")
	private Resource RBanco;

	@Value("classpath:/R_DESPESA.jasper")
	private Resource RDespesa;

	@Value("classpath:/R_CENTRO_CUSTO.jasper")
	private Resource RCentroCusto;

	@Value("classpath:/R_CLIENTE_FORNECEDOR.jasper")
	private Resource RClicenteFornecedor;

	@Value("classpath:/icone.jpg")
	private Resource icone;

	@Autowired
	private DataSource dataSource;

	private Map<String, Resource> mapa = new HashMap<>();

	@Autowired
	private NegocioService service;

	@Override
	public void onApplicationEvent(StageReadlyEvent event) {
		mapa.put("m_cadastro_banco", resource);
		mapa.put("m_cadastro_metas", MMeta);
		mapa.put("m_cadastro_cliente_fornec", mCadastroClienteFornec);
		mapa.put("m_cadastro_receita", mCadastroReceita);
		mapa.put("m_cadastro_despesa", mCadastroDespesa);
		mapa.put("m_cadastro_centro_custo", mCadastroCentroCusto);
		mapa.put("m_inventario", mInventario);
		mapa.put("m_lancamento_receita", mLancamentoReceita);
		mapa.put("m_lancamento_despesa", mLancamentoDespesa);
		mapa.put("m_analise_movimento", mAnaliseMovimento);
		mapa.put("m_analise_metas", mAnaliseMetas);
		mapa.put("m_analise_fluxo_caixa", mAnaliseFluxoCaixa);
		mapa.put("m_principal", mPrincipal);

		mapa.put("R_RECEITA", RReceita);
		mapa.put("R_BANCO", RBanco);
		mapa.put("R_DESPESA", RDespesa);
		mapa.put("R_CENTRO_CUSTO", RCentroCusto);
		mapa.put("R_CLIENTE_FORNECEDOR", RClicenteFornecedor);
		mapa.put("icone", icone);

		Stage stage = event.getStage();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(mapa.get("m_principal").getURL());
			Parent parent = fxmlLoader.load();
			this.service.cadastrarAnoBase(2020);
			this.service.cadastrarAnoBase(2021);
			this.service.cadastrarAnoBase(2022);
			this.service.cadastrarAnoBase(2023);
			this.service.cadastrarAnoBase(2024);
			this.service.cadastrarAnoBase(2025);

			this.service.cadastrarDias();

			AbstractController.anoBaseAtual = this.service.getAnoBaseDao().findByVlAnoBase(2021);

			List<FluxoCaixa> caixas = service.getMovimentoDao().consultaValorTotal(null, null, null, null, null, null, null, null, null, null, AbstractController.anoBaseAtual);
			List<FluxoCaixaTotalTo> totalizadores= new ArrayList<FluxoCaixaTotalTo>();
			List<FluxoCaixaTo> fluxos = service.obterFluxosDeCaixa(caixas, totalizadores);

			AbstractController.listaEstaticaFluxoCaixa.addAll(fluxos);
			AbstractController.listaEstaticaFluxoCaixaTotal.addAll(totalizadores);


			AbstractController.listaEstaticaBancos.addAll(service.getBancoDao().findAll());
			AbstractController.listaEstaticaBancos.add(null);
			AbstractController.listaEstaticaCliente.addAll(service.getClientFornecDao().findByTpClienteFornec("Cliente"));
			AbstractController.listaEstaticaCliente.add(null);
			AbstractController.listaEstaticaFornecedor.addAll(service.getClientFornecDao().findByTpClienteFornec("Fornecedor"));
			AbstractController.listaEstaticaFornecedor.add(null);
			AbstractController.listaEstaticaDespesa.addAll(service.getDesepesaDao().findAll());
			AbstractController.listaEstaticaDespesa.add(null);
			AbstractController.listaEstaticaReceita.addAll(service.getReceitaDao().findAll());
			AbstractController.listaEstaticaReceita.add(null);
			AbstractController.listaEstaticaCentroCusto.addAll(service.getCentroCustoDao().findAll());
			AbstractController.listaEstaticaCentroCusto.add(null);
			AbstractController.listaEstaticaAnoBase.addAll(service.getAnoBaseDao().findBySnLancadoOrderByVlAnoBase("S"));

//          ClienteFornecedor clienteFornecedor = service.getClientFornecDao().findById(1L).get();
//          LocalDateTime data = LocalDateTime.of(2021, 8, 1,8, 1);
//          LocalDateTime dataFim = LocalDateTime.of(2021, 8, 1,9, 1);
//			List<Movimento> lista = service.getMovimentoDao().consultaPersonalidada(null, null, null, null, null, null, null,1,null,null,null,null);
//			System.out.println(lista.size());
			stage.close();
			AbstractController abstractController = fxmlLoader.getController();
			abstractController.carregar(service,mapa,null,dataSource,"R_BANCO");
			Scene scene = stage.getScene();
			stage.getIcons().add(new Image(icone.getInputStream()));
			scene = new Scene(parent,1024, 700);
			stage.setTitle("Sistema para Acompanhamento Financeiro V.1.0");
			stage.setResizable(false);
			stage.setScene(scene);

			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
